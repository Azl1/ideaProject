package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.Client;
import com.abdullaevaziz.util.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements AutoCloseable {

    private Connection conn;

    public ClientRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(Constants.DB_URL,
                    Constants.USERNAME, Constants.PASSWORD);
        } catch (Exception ignored) {
        }
    }

    public boolean add(Client client) {
        String sql = "insert into clients(fio, numberTel, age, preferences) values(?,?,?,?) ";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, client.getFio());
            preparedStatement.setInt(2, client.getNumberTel());
            preparedStatement.setInt(3, client.getAge());
            preparedStatement.setString(4, client.getPreferences());

            int row = preparedStatement.executeUpdate();
            if (row <= 0)
                return false;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    client.setId(generatedKeys.getInt(1));
            }
            return true;
        } catch (SQLException ignored) {
           // ignored.printStackTrace();
            System.out.println(ignored.getMessage());
        }
        return false;
    }

    public List<Client> getClients() {
        String sql = "select * from clients";
        ArrayList<Client> clientList = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt(1));
                client.setFio(resultSet.getString(2));
                client.setNumberTel(resultSet.getInt(3));
                client.setAge(resultSet.getInt(4));
                client.setPreferences(resultSet.getString(5));
                clientList.add(client);
            }
        } catch (SQLException ignored) {
        }
        return clientList;
    }

    public Client getId(int id) {
        String sql = "select * from clients where clients.id=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return null;
            Client trainer = new Client();
            trainer.setId(resultSet.getInt(1));
            trainer.setFio(resultSet.getString(2));
            trainer.setNumberTel(resultSet.getInt(3));
            trainer.setAge(resultSet.getInt(4));
            trainer.setPreferences(resultSet.getString(5));
            return trainer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(Client client){
        String sql = "delete from clients where clients.id = ?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, client.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ignored) {
        }
        return false;
    }

    public boolean update(Client client){
        String sql = "update clients set clients.fio=?, " +
                "clients.numberTel=?, clients.age=?, clients.preferences=? where clients.id=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setString(1, client.getFio());
            preparedStatement.setInt(2, client.getNumberTel());
            preparedStatement.setInt(3, client.getAge());
            preparedStatement.setString(4, client.getPreferences());
            preparedStatement.setInt(5, client.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ignored) {
        }
        return false;
    }


    @Override
    public void close() {
        if (this.conn != null)
            try {
                this.conn.close();
            } catch (SQLException ignored) {
            }
    }
}

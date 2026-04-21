package com.abdullaevaziz.repository;

import com.abdullaevaziz.model.ProstituteIndividual;
import com.abdullaevaziz.util.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProstituteRepository implements AutoCloseable{

    private Connection conn;

    public ProstituteRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(Constants.DB_URL,
                    Constants.USERNAME, Constants.PASSWORD);
        } catch (Exception ignored) {
        }
    }

    public boolean add(ProstituteIndividual prostituteIndividual) {
        String sql = "insert into prostitutes(fio, age, weight, specialization," +
                " pricePerHour, id_cl) values(?,?,?,?,?,?) ";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, prostituteIndividual.getFio());
            preparedStatement.setInt(2, prostituteIndividual.getAge());
            preparedStatement.setInt(3, prostituteIndividual.getWeight());
            preparedStatement.setString(4, prostituteIndividual.getSpecialization());
            preparedStatement.setDouble(5, prostituteIndividual.getPricePerHour());
            preparedStatement.setInt(6, prostituteIndividual.getId_cl());

            int row = preparedStatement.executeUpdate();
            if (row <= 0)
                return false;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    prostituteIndividual.setId(generatedKeys.getInt(1));
            }
            return true;
        } catch (SQLException ignored) {
            // ignored.printStackTrace();
            System.out.println(ignored.getMessage());
        }
        return false;
    }

    public List<ProstituteIndividual> getProstitutes() {
        String sql = "select * from prostitutes";
        ArrayList<ProstituteIndividual> prostituteList = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProstituteIndividual prostituteIndividual = new ProstituteIndividual();
                prostituteIndividual.setId(resultSet.getInt(1));
                prostituteIndividual.setFio(resultSet.getString(2));
                prostituteIndividual.setAge(resultSet.getInt(3));
                prostituteIndividual.setWeight(resultSet.getInt(4));
                prostituteIndividual.setSpecialization(resultSet.getString(5));
                prostituteIndividual.setPricePerHour(resultSet.getDouble(6));
                prostituteIndividual.setId_cl(resultSet.getInt(7));
                prostituteList.add(prostituteIndividual);
            }
        } catch (SQLException ignored) {
        }
        return prostituteList;
    }

    public ProstituteIndividual getId(int id) {
        String sql = "select * from prostitutes where prostitutes.id=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return null;
            ProstituteIndividual trainer = new ProstituteIndividual();
            trainer.setId(resultSet.getInt(1));
            trainer.setFio(resultSet.getString(2));
            trainer.setAge(resultSet.getInt(3));
            trainer.setWeight(resultSet.getInt(4));
            trainer.setSpecialization(resultSet.getString(5));
            trainer.setPricePerHour(resultSet.getDouble(6));
            trainer.setId_cl(resultSet.getInt(7));
            return trainer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(ProstituteIndividual prostituteIndividual){
        String sql = "delete from prostitutes where prostitutes.id_p=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, prostituteIndividual.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ignored) {
        }
        return false;
    }

    public boolean update(ProstituteIndividual prostituteIndividual){
        String sql = "update prostitutes set prostitutes.fio=?, " +
                "prostitutes.age=?, prostitutes.weight=?, prostitutes.specialization=?," +
                "prostitutes.pricePerHour=?, prostitutes.id_cl=? where prostitutes.id_p=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setString(1, prostituteIndividual.getFio());
            preparedStatement.setInt(2, prostituteIndividual.getAge());
            preparedStatement.setInt(3, prostituteIndividual.getWeight());
            preparedStatement.setString(4, prostituteIndividual.getSpecialization());
            preparedStatement.setDouble(5, prostituteIndividual.getPricePerHour());
            preparedStatement.setInt(6,prostituteIndividual.getId_cl());
            preparedStatement.setInt(7, prostituteIndividual.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ignored) {
        }
        return false;
    }

    public List<ProstituteIndividual> getListProstitute(int idClient) {
        String sql = "select * from prostitutes where id_cl = ?";
        ArrayList<ProstituteIndividual> prostituteList = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, idClient);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProstituteIndividual prostituteIndividual = new ProstituteIndividual();

                prostituteIndividual.setId(resultSet.getInt(1));
                prostituteIndividual.setFio(resultSet.getString(2));
                prostituteIndividual.setAge(resultSet.getInt(3));
                prostituteIndividual.setWeight(resultSet.getInt(4));
                prostituteIndividual.setSpecialization(resultSet.getString(5));
                prostituteIndividual.setPricePerHour(resultSet.getDouble(6));
                prostituteIndividual.setId_cl(resultSet.getInt(7));

                prostituteList.add(prostituteIndividual);
            }
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return prostituteList;
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

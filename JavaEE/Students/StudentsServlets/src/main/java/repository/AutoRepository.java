package repository;

import model.Auto;
import util.Constants;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoRepository implements AutoCloseable {

    private Connection conn;

    public AutoRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(Constants.DB_URL,
                    Constants.USERNAME, Constants.PASSWORD);
        } catch (Exception ignored) {

        }
    }

    public boolean add(Auto auto) {
        String sql = "insert into auto(brand, power, year, id_s) values (?,?,?,?)";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, auto.getBrand());
            preparedStatement.setInt(2, auto.getPower());
            preparedStatement.setInt(3, auto.getYear());
            preparedStatement.setInt(4, auto.getId_s());

            int row = preparedStatement.executeUpdate();
            if (row <= 0)
                return false;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    auto.setId(generatedKeys.getInt(1));
            }
            return true;
        } catch (SQLException ignored) {
            ignored.printStackTrace();
        }
        return false;
    }

    public List<Auto> getAutos() {
        String sql = "select * from auto";
        ArrayList<Auto> autos = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Auto auto = new Auto();
                auto.setId(resultSet.getInt(1));
                auto.setBrand(resultSet.getString(2));
                auto.setPower(resultSet.getInt(3));
                auto.setYear(resultSet.getInt(4));
                auto.setId_s(resultSet.getInt(5));

                autos.add(auto);
            }
        } catch (SQLException e) {
        }
        return autos;
    }

    public Auto getById(int id) {
        String sql = "select * from auto where auto.id=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return null;
            Auto trainer = new Auto();
            trainer.setId(resultSet.getInt(1));
            trainer.setBrand(resultSet.getString(2));
            trainer.setPower(resultSet.getInt(3));
            trainer.setYear(resultSet.getInt(4));
            trainer.setId_s(resultSet.getInt(5));
            return trainer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Auto> getListAuto(int idStudent){
        String sql = "select * from auto where id_s = ?";
        ArrayList<Auto> autos = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, idStudent);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Auto auto = new Auto();
                auto.setId(resultSet.getInt(1));
                auto.setBrand(resultSet.getString(2));
                auto.setPower(resultSet.getInt(3));
                auto.setYear(resultSet.getInt(4));
                auto.setId_s(resultSet.getInt(5));

                autos.add(auto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return autos;
    }

    public boolean delete(Auto auto) {
        String sql = "delete from auto where auto.id=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, auto.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ignored) {
        }
        return false;
    }

    public boolean update(Auto auto) {
        String sql = "update auto set auto.brand=?, auto.power=?, auto.year=?, auto.id_s=? where auto.id=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setString(1, auto.getBrand());
            preparedStatement.setInt(2, auto.getPower());
            preparedStatement.setInt(3, auto.getYear());
            preparedStatement.setInt(4, auto.getId_s());
            preparedStatement.setInt(5, auto.getId());
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

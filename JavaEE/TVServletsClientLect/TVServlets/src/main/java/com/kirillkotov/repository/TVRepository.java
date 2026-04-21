package com.kirillkotov.repository;

import com.kirillkotov.model.TV;
import com.kirillkotov.util.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TVRepository implements AutoCloseable {
    private Connection conn;

    public TVRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(Constants.DB_URL, Constants.USERNAME, Constants.PASSWORD);
        } catch (Exception e) {}
    }

    public boolean add(TV tv) {
        String sql = "insert into tvs(brand,model, color,timeExpectancy, price) values (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, tv.getBrand());
            preparedStatement.setString(2, tv.getModel());
            preparedStatement.setString(3, tv.getColor());
            preparedStatement.setInt(4, tv.getTimeExpectancy());
            preparedStatement.setDouble(5, tv.getPrice());

            int row = preparedStatement.executeUpdate();
            if (row <= 0)
                return false;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next())
                    tv.setId(generatedKeys.getInt(1));
            }
            return true;
        } catch (SQLException ignored) {
            //ignored.printStackTrace();
        }
        return false;
    }

    public List<TV> getTVs() {
        String sql = "select * from tvs";
        ArrayList<TV> tvs = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TV tv = new TV();
                tv.setId(resultSet.getInt(1));
                tv.setBrand(resultSet.getString(2));
                tv.setModel(resultSet.getString(3));
                tv.setColor(resultSet.getString(4));
                tv.setTimeExpectancy(resultSet.getInt(5));
                tv.setPrice(resultSet.getDouble(6));
                tvs.add(tv);
            }
        } catch (SQLException e) {
        }
        return tvs;
    }

    public TV getById(int id) {
        String sql = "select * from tvs where tvs.id=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next())
                return null;
            TV tv = new TV();
            tv.setId(resultSet.getInt(1));
            tv.setBrand(resultSet.getString(2));
            tv.setModel(resultSet.getString(3));
            tv.setColor(resultSet.getString(4));
            tv.setTimeExpectancy(resultSet.getInt(5));
            tv.setPrice(resultSet.getDouble(6));
            return tv;
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean delete(TV tv) {
        String sql = "delete from tvs where tvs.id=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, tv.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ignored) {
        }
        return false;
    }

    public boolean update(TV tv) {
        String sql = "update tvs set tvs.brand=?, tvs.model=?, tvs.color=?, tvs.timeExpectancy=?, tvs.price=? where tvs.id=?";
        try (PreparedStatement preparedStatement = this.conn.prepareStatement(sql)) {
            preparedStatement.setString(1, tv.getBrand());
            preparedStatement.setString(2, tv.getModel());
            preparedStatement.setString(3, tv.getColor());
            preparedStatement.setInt(4, tv.getTimeExpectancy());
            preparedStatement.setDouble(5, tv.getPrice());

            preparedStatement.setInt(6, tv.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException ignored) {
                ignored.printStackTrace();
        }
        return false;
    }

    @Override
    public void close()  {
        try {
            if (this.conn != null)
                this.conn.close();
        } catch (SQLException e) {

        }
    }
}

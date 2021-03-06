package com.vopanasyuk.dao;
import com.vopanasyuk.db.AirbnbDB;
import com.vopanasyuk.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hunky on 08.10.15.
 */
public class DaoUser implements DaoModel {

    private final Connection connection;


    public DaoUser(Connection connection) {
        this.connection = connection;
    }
    public DaoUser() {
        connection = new AirbnbDB().getConnection();

    }
    @Override
    public List<User> getAll() throws SQLException {
        String sql = "select * from user;";
        PreparedStatement stmt = connection.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();
        User user = null;
        List<User> list = new ArrayList<User>();


        while (rs.next()){
            User.Builder builder = User.createBuilder();
            user = builder.createUser(rs.getString(2), rs.getString(3), rs.getString(4));
            list.add(user);
        }


        return list;
    }

    @Override
    public User getById(Integer id) throws SQLException {

        String sql = "select * from user where id = ?;";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();

        User user = null;

        while (rs.next()){
            User.Builder builder = User.createBuilder();
            user = builder.createUser(rs.getString(2), rs.getString(3), rs.getString(4));

        }

        return user;

    }

    @Override
    public void update(Object obj) {
        //TODO
    }

    @Override
    public void insert(Object obj) {
        //TODO
    }

    @Override
    public void delete(Object obj) {
        //TODO
    }


}
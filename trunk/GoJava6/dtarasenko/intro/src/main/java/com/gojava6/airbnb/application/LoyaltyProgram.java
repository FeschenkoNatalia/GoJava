package com.gojava6.airbnb.application;

import com.gojava6.airbnb.observer.Observer;
import com.gojava6.airbnb.observer.Subject;
import com.gojava6.airbnb.users.User;
import com.gojava6.airbnb.users.UserController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.gojava6.airbnb.application.JDBC.*;

public class LoyaltyProgram implements Subject {

    private String loyaltyProgramName;
    private boolean available;

    public void setAvailable(boolean available) {
        this.available = available;
        if (available) {
            notifyObservers();
        }
    }

    public void setLoyaltyProgramName(String loyaltyProgramName) {
        this.loyaltyProgramName = loyaltyProgramName;
    }

    @Override
    public void registerObserver(Observer observer) {
        User user = (User) observer; //TODO
        try {
            Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);
            Statement stmt = null;

            try {
                stmt = conn.createStatement();
                String sql = "INSERT INTO observer VALUES (" + user.getUserId() + ")";

                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                System.out.println("SQL query is no correct");
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } finally {
                    conn.close();
                }
            }
        } catch (SQLException ex) {
            System.out.println("No connection");
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        User user = (User) observer; //TODO
        try {
            Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);
            Statement stmt = null;

            try {
                stmt = conn.createStatement();
                String sql = "DELETE FROM observer WHERE user_id =" + user.getUserId();
                stmt.executeUpdate(sql);
            } catch (SQLException ex) {
                System.out.println("SQL query is no correct");
            } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } finally {
                    conn.close();
                }
            }
        } catch (SQLException ex) {
            System.out.println("No connection");
        }
    }

    @Override
    public void notifyObservers() {
        System.out.println("\nNotifying all registered clients about new loyalty programs:");

        UserController userController = new UserController();
        List<Integer> userIdList = getUserIdList();
        for (Integer userId : userIdList) {
            User user = userController.getUser(userId);
            user.update(loyaltyProgramName);
        }
    }

    private List<Integer> getUserIdList() {
        List<Integer> userIdList = new ArrayList<Integer>();

        try {
            Connection conn = DriverManager.getConnection(sqlUrl, sqlUser, sqlPassword);
            Statement stmt = null;
            ResultSet rs = null;

            try {
                stmt = conn.createStatement();
                String sql = "SELECT * FROM observer";
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    Integer userId = rs.getInt("user_id");
                    userIdList.add(userId);
                }
            } catch (SQLException ex) {
                System.out.println("SQL query is no correct");
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } finally {
                    try {
                        if (stmt != null) {
                            stmt.close();
                        }
                    } finally {
                        conn.close();
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("No connection");
        }

        return userIdList;
    }
}

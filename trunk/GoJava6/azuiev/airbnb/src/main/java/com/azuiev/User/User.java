package com.azuiev.User;

import com.azuiev.AirBnB;
import com.azuiev.Apartment.ApartType;
import com.azuiev.Apartment.Apartment;
import com.azuiev.DB.DBAirBnB;
import com.azuiev.DB.Databasible;
import com.azuiev.Validator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * Created by Lera on 21.09.2015.
 */
public class User implements Observer,Databasible {
    private String name;
    private String surName;
    private String email;
    private List<UserRoles> myRoles = new LinkedList<UserRoles>();

    private User(){

    }
    private User(String name, String surName, String email) {
        this.name = name;
        this.surName = surName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void notifyObserver(String s) {
        System.out.println(s);
    }

    private boolean hasRole(UserRoles role) {
        return myRoles.contains(role);
    }

    public void addRole(UserRoles role) {
        if (hasRole(role)) {
            AirBnB.log.info("User: " + this + " already has role - " + role);
            return;
        } else {
            myRoles.add(role);
            AirBnB.log.info("User: " + this + " now has new role - " + role);

        }
    }

    public Apartment registerBook(String city, String address, ApartType apartType) {
        if (hasRole(UserRoles.HOST)) {
            return Apartment.registerBook(this, city, address, apartType);
        } else {
            AirBnB.log.error("User: " + this + "has`t role " + UserRoles.HOST);
            return null;
        }

    }

    public boolean reserveApartment(Apartment apartment, Date start, Date end) {
        if (hasRole(UserRoles.CLIENT)) {
            return apartment.reserveApartment(this, start, end);
        } else {
            AirBnB.log.error("User: " + this + "has`t role " + UserRoles.CLIENT);
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("name = '%s', surName = '%s', email = '%s'", name, surName, email);
    }

    public static Builder createBuilder(){
        return new Builder();
    }

    @Override
    public Databasible fromDB() {
        //TODO
        String query = "select * from user";

        DBAirBnB db = new DBAirBnB();
        ResultSet rs = db.select(query);

        List<User> list = new ArrayList<User>();

        try {
            while (rs.next()){
                User.Builder builder = User.createBuilder();
                User user = builder.createUser(rs.getString(2), rs.getString(3), rs.getString(4));
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean intoDB() {
        //TODO
        String query = "insert into user values (null, '" + name + "', '" + surName + "', '" + email+"')";
        return false;
    }

    public static class Builder{
        private Builder() {
        }

        private static Set<String> emails = new TreeSet<String>();
        private static List<User> users = new LinkedList<User>();

        public User createUser(String name, String surName, String email) {

            User user = new User(name, surName, email);

            if (validate(user)) {
                return user;
            } else {
                return null;
            }
        }
        public User createUser(String name, String surName, String email, UserRoles... roles) {

            User user = createUser(name, surName, email);
            if (user != null){
                for (UserRoles userRoles :roles) {
                    user.addRole(userRoles);
                }

            }
            return user;
        }

        private boolean validate(User user) {
            if (emails.contains(user.getEmail())){
                AirBnB.log.error("Failed to create user. Email is busy - " + user.getEmail());
                return false;
            }

            Validator v = Validator.getInstance();

            if (!v.validateUser(user)) {
                AirBnB.log.error("Failed to create - " + user);
                return false;
            } else {
                AirBnB.log.info("User created - " + user);
                emails.add(user.getEmail());
                users.add(user);
                return true;
            }
        }
    }
}

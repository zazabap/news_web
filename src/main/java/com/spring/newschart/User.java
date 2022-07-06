package com.spring.newschart;

import java.sql.*;
import java.util.List;

public class User {
    private String userName;
    private String password;
    private String email;
    private String phone;
    private Connection conn;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public User(String userName, String password,
                String email, String phone){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public void setUserName(String userName) {this.userName = userName;}
    public void setPassword(String password) { this.password = password;}
    public void setEmail(String email){this.email = email;}
    public void setPhone(String phone){this.phone = phone;}
    public void setConn(Connection conn){this.conn = conn;}

    public String getUserName(){return userName;}
    public String getPassword(){return password;}
    public String getEmail(){return email;}
    public String getPhone(){return phone;}
    public Connection getConn(){ return this.conn;}


    public void setAll(List<String> a){
        userName = a.get(0);
        password = a.get(1);
        email = a.get(2);
        phone = a.get(3);
    }

    // Hisotry type

    public void addBrowseHistory(Connection conn,
                                        int news_id) throws SQLException {

        String sqlhit = "INSERT into user_tbl (" +
                "user_name, user_history_browse, user_last_login) "
                + "values (?, ?, now()) ";
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setString(1, userName);
        pstmt.setInt(2, news_id);
        pstmt.executeUpdate();

        System.out.println("=====Add Browse History=====");
    }


}

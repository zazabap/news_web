package com.spring.newschart;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;


// Extended Another Service while doing news
public class userService {
    private MailService mailService;
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public userService(){};


    @Bean
    public boolean validateConn(Connection conn) throws SQLException {
        System.out.println("Successful Connection"+conn.getSchema());
        return true;
    }

    @Bean
    public void registerUser(Connection conn, User usr) throws SQLException {
        boolean usercheck;
        Scanner myObj = new Scanner(System.in);
        do{
            System.out.println("[New Username]: ");

            usr.setUserName(myObj.nextLine());
            System.out.println("[New Password]: ");
            usr.setPassword(myObj.nextLine());
            System.out.println("[New Email]: ");
            usr.setEmail(myObj.nextLine());
            System.out.println("[New Phone]: ");
            usr.setPhone(myObj.nextLine());
            usercheck = this.validateUser(conn, usr);
            if (usercheck) System.out.println("User Name Registered!");
        }while (usercheck);
        System.out.println("User Name Available! ");

        PreparedStatement pstmt = conn.prepareStatement(
                "INSERT into user_tbl(user_id,user_name,user_password," +
                        "user_email,user_phone) " +
                        "values (?,?,?,?,?);");

        pstmt.setInt(1, 0);
        pstmt.setString(2, usr.getUserName());
        pstmt.setString(3, usr.getPassword());
        pstmt.setString(4, usr.getEmail());
        pstmt.setString(5, usr.getPhone());
        pstmt.executeUpdate();
        System.out.println("Register Successful!");
    }


    public boolean validateUser(Connection conn,
                                       User usr)
            throws SQLException {

        PreparedStatement pstmt = conn.prepareStatement(
                "select * from user_tbl where user_name = ? or " +
                        "user_email = ? ");

        pstmt.setString(1, usr.getUserName());
        pstmt.setString(2, usr.getEmail());

        ResultSet rs = pstmt.executeQuery();
        boolean status = rs.next();
        if(status){
            System.out.println("Username or Email or Phone Exist!");
        }
        return status;
    }

    public void LoginUser(Connection conn, User usr) throws SQLException {

        List<String> r = new ArrayList<String>();
        PreparedStatement pstmt = conn.prepareStatement(
                "select * from user_tbl where user_name = ? and user_password = ? ");

        pstmt.setString(1, usr.getUserName());
        pstmt.setString(2, usr.getPassword());

        ResultSet rs = pstmt.executeQuery();
        boolean status = rs.next();
        if (status) {
            System.out.println("Login Successful!");
        } else {
            System.out.println("User not found or Password not correct");
            System.exit(0);
        }

        loginUpdate(conn,usr);
    }

    public void loginUpdate(Connection conn, User usr) throws SQLException {
        System.out.println("Update Latest Login Time ");
        String sqlhit = "SELECT user_id\n" +
                "  FROM user_tbl " +
                "  WHERE user_name =? and user_password = ?;";
        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setString(1, usr.getUserName());
        pstmt.setString(2, usr.getPassword());
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.next();
        int uid = rs.getInt(1);
        String sqladd = "UPDATE user_tbl " +
                "SET user_last_login = now() WHERE user_id=?;";
        pstmt = conn.prepareStatement(sqladd);
        pstmt.setInt(1, uid);
        pstmt.executeUpdate();
    }



}

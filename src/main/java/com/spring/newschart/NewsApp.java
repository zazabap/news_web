package com.spring.newschart;

import com.example.demospring1.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Scanner;
public class NewsApp {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";
    static final String USER = "user";
    static final String PASS = "P@ssW0rd";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(NewsApp.DB_URL, NewsApp.USER, NewsApp.PASS);

        ApplicationContext context = new ClassPathXmlApplicationContext("NewsApp.xml");
        System.out.println("[Username]: ");
        Scanner myObj = new Scanner(System.in);
        String userName = myObj.nextLine();
        System.out.println("[Password]: ");
        String password = myObj.nextLine();
        News news = context.getBean(News.class);
        User user = context.getBean(User.class);

        User usr = new User(userName, password);
        userService us = new userService();
//        us.registerUser(conn, user);
        us.LoginUser(conn, usr);

        NewsService ns = new NewsService();
        news.setTitle("JiangNan");
        ns.searchNewsTitle(conn, news);
        ns.NewsTop(conn);

        System.out.println(news);
        System.out.println(user);

    }
}

// Some note
// For inversion control IoC, the best example here
// SQL as the data source and each time
// create a News/User/Advertisement instance
// One could inject Connection conn to access the database
// In such scenario it's unnecessary to start new
// connection each time as I tried previously

// One more thing
// When News/Advertisement/Comment
// needs to access the user information
// Then one could inject user information
// into the function and solve the access issue

// We could devide thing more into different services
// Like Login Service Main Service and more.


package com.spring.newschart;

import java.sql.*;
import java.util.Scanner;
import static java.sql.DriverManager.getConnection;


public class News {
    private int nid;
    private String title;
    private String author;
    private String content;
    private java.sql.Timestamp timestamp;
    private int hits;
    private int fav;
    private int forward;
    private boolean paid;

    // For Database related
    private Connection conn;

    public News(String title, String author,
                String content){}
    public News(){}

    public String getTitle(){return this.title;}
    public String getContent(){return this.content;}
    public String getAuthor(){return this.author;}
    public Connection getConn(){ return this.conn;}
    public int getNid() {return this.nid;}

    public void setTitle(String title){this.title = title;}
    public void setContent(String content){ this.content= content;}
    public void setAuthor(String author){ this.author = author;}
    public void setConn(Connection conn){this.conn = conn;}

}

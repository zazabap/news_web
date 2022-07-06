package com.spring.newschart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Advertisement {
    private int aid;
    private String title;
    private String author;
    private String content;
    private double weight;
    private java.sql.Timestamp timestamp;
    private int hits;
    private int fav;
    private int forward;
    private boolean paid;


    // For Database related
    private Connection conn;


    public Advertisement(String title, String author,
                String content){}
    public Advertisement(){}

    public String getTitle(){return this.title;}
    public String getContent(){return this.content;}
    public String getAuthor(){return this.author;}
    public Connection getConn(){ return this.conn;}
    public double getWeight(){return this.weight;}


    public void setTitle(String title){this.title = title;}
    public void setContent(String content){ this.content= content;}
    public void setAuthor(String author){ this.author = author;}
    public void setConn(Connection conn){this.conn = conn;}



}

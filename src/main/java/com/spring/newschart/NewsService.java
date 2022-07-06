package com.spring.newschart;

import java.sql.*;
import java.util.Scanner;

public class NewsService {

    private CommentService commentService;
    private AdService adService;

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    public void setAdService(AdService adService) {
        this.adService = adService;
    }


    // Here mostly fron the user side
    public void searchNewsTitle(Connection conn, News news)
            throws SQLException {
        PreparedStatement prepStmt = null;
        prepStmt = conn
                .prepareStatement("SELECT * FROM news_tbl " +
                        "WHERE news_tbl.news_title LIKE ?" );

        prepStmt.setString(1, "%"+news.getTitle()+"%");

        System.out.println("=======Search News==========");
        System.out.println(prepStmt);

        ResultSet rs = prepStmt.executeQuery();
        // Try to debug first.
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
            }
            System.out.println("");
        }
    }

    public void searchNewsContent(Connection conn, News news)
            throws SQLException {
        PreparedStatement prepStmt = null;
        prepStmt = conn
                .prepareStatement("SELECT * FROM news_tbl " +
                        "WHERE news_tbl.news_content LIKE ?" );

        prepStmt.setString(1, "%"+news.getContent()+"%");

        System.out.println("=======Search News==========");
        System.out.println(prepStmt);

        ResultSet rs = prepStmt.executeQuery();
        // Try to debug first.
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
            }
            System.out.println("");
        }
    }

    public int visitNews(Connection conn, News news, User usr) throws SQLException {

        System.out.println("Which News You would like to see [news_id]: ");
        Scanner myObj = new Scanner(System.in);
        int s = Integer.parseInt(myObj.nextLine());

        String sqlstmt = "SELECT *\n" +
                "  FROM news_tbl " +
                "  WHERE news_id = ?;";
        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);
        pstmt.setInt(1,s);
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  \n");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
            }
            System.out.println("");
        }
        hitNews(conn, s, usr);
        CommentService cs = new CommentService();
        cs.showNewsComment(conn, news);
        return s;
    }

    public void hitNews(Connection conn, int news_id, User usr) throws SQLException {
        String sqlhit = "SELECT news_hit_vol\n" +
                "  FROM news_tbl " +
                "  WHERE news_id = ?;";

        PreparedStatement pstmt = conn.prepareStatement(sqlhit);
        pstmt.setInt(1, news_id);
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        rs.next();
        int addHit = Integer.parseInt(rs.getString(1));
        addHit++;
        System.out.println("======Update Hit Number to"+addHit+"========" );

        String sqladd = "UPDATE news_tbl " +
                "SET news_hit_vol=? WHERE news_id=?;";
        pstmt = conn.prepareStatement(sqladd);
        pstmt.setInt(1,
                addHit);
        pstmt.setInt(2, news_id);
        pstmt.executeUpdate();

        usr.addBrowseHistory(conn, news_id);
    }

    // Here Mostly from the admin side
    public void NewsTop(Connection conn) throws SQLException {

        String sqlstmt = "SELECT news_id, news_title, news_hit_vol\n" +
                "  FROM news_tbl " +
                "  ORDER BY news_hit_vol DESC;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);

        ResultSet rs = pstmt.executeQuery();
        // Try to debug first.
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        int j=0 , l =0;
        l = 10;
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
            }
            System.out.println("");
            j++;
            if (j >l) break;
        }
    }

}

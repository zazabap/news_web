package com.spring.newschart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin {

    private Connection conn;

    public void setConn(Connection conn){this.conn = conn;}

    public void addAds(Connection conn, String title,
                       String author, String content, double weight) throws
            SQLException {
        PreparedStatement prepStmt = null;

        prepStmt = conn
                .prepareStatement("INSERT into ad_tbl (" +
                        "ad_id, ad_title, ad_datetime, " +
                        "ad_investor, ad_content, ad_weight) "
                        + "values (0, ?, now(), ? ,?, ?) ");
        prepStmt.setString(1, title);
        prepStmt.setString(2, author);
        prepStmt.setString(3, content);
        prepStmt.setDouble(4, weight);
        System.out.println("=======Add News==========");
        System.out.println(prepStmt);
        prepStmt.executeUpdate();
    }

    public static void deleteAds(Connection conn, int nid)
            throws SQLException{

        String del = "DELETE FROM news_tbl\n" +
                "    WHERE news_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(del);
        pstmt.setInt(1,nid);
        pstmt.executeUpdate();

        del = "DELETE FROM user_tbl\n" +
                "    WHERE user_history_review = ?";
        pstmt = conn.prepareStatement(del);
        pstmt.setInt(1,nid);
        pstmt.executeUpdate();

    }

}

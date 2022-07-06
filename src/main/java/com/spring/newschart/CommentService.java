package com.spring.newschart;

import java.sql.*;

public class CommentService {


    public CommentService(){}

    public String getCommentator(Connection conn, int user_id) throws SQLException {
        String uname = "";
        String sqlstmt = "SELECT user_name" +
                "  FROM user_tbl " +
                "  WHERE user_id = ?;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);
        pstmt.setInt(1, user_id);
        int uid = 0;
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  \n");
                uname = rs.getString(i);
            }
        }
        return uname;
    }

    public void showNewsComment(Connection conn, News news) throws SQLException {
        String sqlstmt = "SELECT review_content, review_upvote_vol, " +
                "review_downvote_vol, user_id" +
                "  FROM review_tbl " +
                "  WHERE news_id = ?;";

        PreparedStatement pstmt = conn.prepareStatement(sqlstmt);
        pstmt.setInt(1, news.getNid());

        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        int count =1;
        while (rs.next()) {
            System.out.print("Review :" + count);
            for (int i = 1; i <= columnsNumber-1; i++) {
                if (i > 1) System.out.print(",");
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " " +columnValue );
            }
            //Add Commenter's name
            String userName = getCommentator(conn, rs.getInt(columnsNumber));
            System.out.print( " " + userName );
            System.out.println("");
            count++;
        }
    }
}

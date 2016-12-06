package cseiii_GitMining;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBTool.DBTool;

public class getdata {
	private static Integer getAll() {
	    Connection conn = DBTool.getConn();
	    String sql = "SELECT word,value FROM gitmining.statistic_table where table_name='User_public_gists';";
	    String str = "";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        int rowCount = 0;
	        try {
	            rs.last();
	            rowCount = rs.getRow();
	        } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }
	        rs.first();
	        String[] op = new String[rowCount];
	        for(int i=0;i<rowCount;i++,rs.next()){
	        	op[i]=rs.getString("word")+" "+rs.getInt("value");
	        	System.out.println(op[i]);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public static void main(String[] args){
		getdata wocao = new getdata();
		wocao.getAll();
	}

}

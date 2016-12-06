package cseiii_GitMining;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;

import DBTool.DBTool;
import HTTP.HttpRequest;

public class RepositryData {
	public static void main(String[] args){
		RepositryData wocao = new RepositryData();
		try {
			wocao.doit(wocao.getData());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void doit(String[] str) throws IOException, ClassNotFoundException, SQLException{
		Connection conn = DBTool.getConn();
		PreparedStatement pstmt;
		String name = "";
		String sql="UPDATE repository SET commits=?,issues=?,pulls=? where full_name=?";
		for(int t=0;t<str.length;t++){

			try {
				name = str[t];
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        pstmt.setInt(1, getCommits(name));
		        pstmt.setInt(2, getIssues(name));
		        pstmt.setInt(3, getPull_Requests(name));
		        pstmt.setString(4, name);
		        System.out.println(pstmt);
		        pstmt.executeUpdate();
		        pstmt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }


			System.out.println(t);
		}
	
	
	}
	public static String[] getData() {
		// TODO Auto-generated method stub
	  Connection conn = DBTool.getConn();
	    String sql = "SELECT full_name FROM gitmining.repository;";
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
	        rs.beforeFirst();
	        String[] op = new String[rowCount];
	        for(int i=0;rs.next();){
	        	op[i]=rs.getString("full_name");
	        	i++;
	        }
	        return op;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	public int getCommits(String Name){
		int low=0;
		int high=128;
		int page=1;
		boolean isEnd=false;
		String str;
		JSONArray temp;

		for(int i=0;i<8;i++){
			if(low>=high){
				return low*50-25;
			}else{
				page=(low+high)/2;
				str = HttpRequest.sendGet("http://www.gitmining.net/api/repository/"+Name+"/commits/shas?page="+page, "");
				temp=new JSONArray(str);
				if(temp.length()==0){
					high=page-1;
				}else if(temp.length()==50){
					low=page+1;
				}else{
					return (page-1)*50+temp.length();
				}
			}
			page=(low+high)/2;
		}

		return 0;
	}

	public int getIssues(String Name){
		int low=0;
		int high=64;
		int page=1;
		boolean isEnd=false;
		String str;
		JSONArray temp;

		for(int i=0;i<7;i++){
			if(low>=high){
				return low*50-25;
			}else{
				page=(low+high)/2;
				str = HttpRequest.sendGet("http://www.gitmining.net/api/repository/"+Name+"/issues/numbers?page="+page, "");
				temp=new JSONArray(str);
				if(temp.length()==0){
					high=page-1;
				}else if(temp.length()==50){
					low=page+1;
				}else{
					return (page-1)*50+temp.length();
				}
			}
			page=(low+high)/2;
		}

		return 0;
	}

	public int getPull_Requests(String Name){
		int low=0;
		int high=64;
		int page=1;
		boolean isEnd=false;
		String str;
		JSONArray temp;

		for(int i=0;i<7;i++){
			if(low>=high){
				return low*50-25;
			}else{
				page=(low+high)/2;
				str = HttpRequest.sendGet("http://www.gitmining.net/api/repository/"+Name+"/pulls/numbers?page="+page, "");
				temp=new JSONArray(str);
				if(temp.length()==0){
					high=page-1;
				}else if(temp.length()==50){
					low=page+1;
				}else{
					return (page-1)*50+temp.length();
				}
			}
			page=(low+high)/2;
		}

		return 0;
	}

}

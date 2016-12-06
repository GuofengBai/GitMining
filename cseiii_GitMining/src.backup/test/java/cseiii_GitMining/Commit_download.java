package cseiii_GitMining;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import DBTool.DBTool;
import HTTP.HttpRequest;

public class Commit_download {
	
	public static ArrayList<String> getSha(String full_name){
		ArrayList<String> al=new ArrayList<String>();
		
		int page=1;
		JSONArray temp = null;
		String str;
		String rn=full_name.replace('.', '+');
		int i;
		
		do{
			
			str = HttpRequest.sendGet("http://www.gitmining.net/api/repository/"+rn+"/commits/shas?page="+page, "");
			temp=new JSONArray(str);
			for(i=0;i<temp.length();i++){
				al.add(temp.getString(i));
			}
			page++;
			
		}while(temp.length()==50);
		
		return al;
	}
	
	public static void commit_download(String full_name){
		List<String> al=getSha(full_name);
		System.out.println("getSha ok!");
		String str;
		String rn=full_name.replace('.', '+');
		JSONObject obj;
		Connection conn = DBTool.getConn();
		PreparedStatement pstmt;
		String sql="INSERT INTO commits(full_name,sha,author_login,committer_login,"
				+ "message,total,additions,deletions,files,date) VALUE(?,?,?,?,?,?,?,?,?,?)";
		for(String sha:al){
			str = HttpRequest.sendGet("http://www.gitmining.net/api/repository/"+rn+"/commit/"+sha, "");
			obj=new JSONObject(str);
			try {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, full_name);
				pstmt.setString(2, sha);
				
				if(obj.has("author")){
					if(obj.getJSONObject("author").has("login")){
						pstmt.setString(3, obj.getJSONObject("author").getString("login"));
					}else{
						pstmt.setString(3, null);
					}
				}else{
					pstmt.setString(3, null);
				}
				
				if(obj.has("committer")){
					if(obj.getJSONObject("committer").has("login")){
						pstmt.setString(4, obj.getJSONObject("committer").getString("login"));
					}else{
						pstmt.setString(4, null);
					}
				}else{
					pstmt.setString(4, null);
				}
				pstmt.setString(5, obj.getJSONObject("commit").getString("message"));
				
				if(obj.has("stats")){
					pstmt.setInt(6, obj.getJSONObject("stats").getInt("total"));
					pstmt.setInt(7, obj.getJSONObject("stats").getInt("additions"));
					pstmt.setInt(8, obj.getJSONObject("stats").getInt("deletions"));
				}else{
					pstmt.setInt(6, 0);
					pstmt.setInt(7, 0);
					pstmt.setInt(8, 0);
				}
				
				if(obj.has("files")){
					pstmt.setInt(9, obj.getJSONArray("files").length());
				}else{
					pstmt.setInt(9, 0);
				}
				pstmt.setString(10, obj.getJSONObject("commit").getJSONObject("committer").getString("date"));
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void run(){
		
		Connection conn = DBTool.getConn();
	    String sql = "SELECT full_name FROM repository;";
	    Statement pstmt;
	    List<String> list=new ArrayList<String>();
	    int i;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        i=0;
	        rs.next();
	        while(i<3137){
	        	rs.next();
	        	i++;
	        }
	        for(;i<3216;i++,rs.next()){
	        	list.add(rs.getString("full_name"));
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    i=0;
	    for(String s:list){
	    	System.out.println(s+"    "+i);
	    	commit_download(s);
	    	i++;
	    }
		
	}
	
	public static void main(String[] args){
		commit_download("unetbootin/unetbootin");
		//run();
	}

}

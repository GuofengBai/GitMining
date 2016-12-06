package Data.UserData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import PO.UserPO;
import VO.UserListLineItem;
import VO.UserSortType;

import org.json.JSONObject;

import DBTool.DBTool;
import DataService.UserDataService.UserDataService;
import HTTP.HttpRequest;

public class UserData implements UserDataService {

	public static int per_page=10;

	public static ArrayList<String> nowList;
	
	public static UserSortType type;

	public UserData(){
		init();
	}

	public void init(){
		
		Connection conn = DBTool.getConn();
	    String sql = "SELECT login FROM user ORDER BY followers DESC;";
	    Statement pstmt;
	    nowList = new ArrayList<String>();
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	nowList.add(rs.getString("login"));
	        }
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}

	public Vector<UserListLineItem> getList(int page) {
		// TODO Auto-generated method stub

		if((page-1)*per_page>=nowList.size()){
			return new Vector<UserListLineItem>();
		}

		Vector<UserListLineItem> ul=new Vector<UserListLineItem>();
		
		Connection conn = DBTool.getConn();
	    Statement pstmt;
		String sql = "SELECT * FROM user WHERE login=";

		for(int i=(page-1)*per_page;i<page*per_page&&i<nowList.size();i++){
			UserListLineItem vo=new UserListLineItem();

			vo.add(nowList.get(i));

			try {
		        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		        ResultSet rs = pstmt.executeQuery(sql+"'"+nowList.get(i)+"'");
		        
		        rs.first();
		        
		        vo.add(rs.getInt("id"));

				vo.add(String.valueOf(rs.getString("type")));
				
				pstmt.close();
				
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
			
			ul.add(vo);

		}

		return ul;
	}

	public void search(String key) {
		nowList.clear();
		Connection conn = DBTool.getConn();
	    String sql = "SELECT login FROM user WHERE login LIKE '%"+key+"%'ORDER BY followers DESC;";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	nowList.add(rs.getString("login"));
	        }
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void setOrder(UserSortType type){
		this.type=type;
		nowList.clear();
		Connection conn = DBTool.getConn();
	    String sql = "SELECT login FROM user "+getOrderSqlString(type);
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	nowList.add(rs.getString("login"));
	        }
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public String getOrderSqlString(UserSortType type){
		return "ORDER BY "+type.toString()+" DESC";
	}

	public void setNormal(){
		setOrder(UserSortType.FOLLOWERS);
	}
	
	public int getTotalPage(){
		return (nowList.size()-1)/per_page+1;
	}

	public UserPO getUser(String Name) {
		// TODO Auto-generated method stub
		UserPO po=new UserPO();
		Connection conn = DBTool.getConn();
	    String sql = "SELECT * FROM user WHERE login='"+Name+"';";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        rs.last();
	        if(rs.getRow()>0){
	        	rs.first();
	        	po.login=rs.getString("login");
	        	po.id=String.valueOf(rs.getInt("id"));
	        	po.type=rs.getString("type");
	        	po.name=rs.getString("name");
	        	po.company=rs.getString("company");
	        	po.location=rs.getString("location");
	        	po.email=rs.getString("email");
	        	po.bio=rs.getString("bio");
	        	po.blog=rs.getString("blog");
	        	po.followers=rs.getInt("followers");
	        	po.following=rs.getInt("following");
	        	po.ownsRepos=getUserOwnsRepos(Name);
	        	po.relatedRepos=getUserRelatedRepos(Name);
	        	po.created_at=rs.getString("created_at");
	        	po.updated_at=rs.getString("updated_at");
	        	pstmt.close();
	        	return po;
	        }
	
		} catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    sql="http://gitmining.net/api/user/"+Name;
	    sql=HttpRequest.sendGet(sql, "");
	    JSONObject obj=new JSONObject(sql);
	    po.login=obj.getString("login");
    	po.id=String.valueOf(obj.getInt("id"));
    	po.type=obj.getString("type");
    	po.name=String.valueOf(obj.getString("name"));
    	po.location=String.valueOf(obj.getString("location"));
    	if(obj.has("email")){
			po.email=String.valueOf(obj.get("email"));
		}else{
			po.email="";
		}
    	if(obj.has("bio")){
			po.bio=String.valueOf(obj.get("bio"));
		}else{
			po.bio="";
		}
		if(obj.has("blog")){
			po.blog=String.valueOf(obj.get("blog"));
		}else{
			po.blog="";
		}
		if(obj.has("company")){
			po.company=String.valueOf(obj.get("company"));
		}else{
			po.company="";
		}
    	po.followers=obj.getInt("followers");
    	po.following=obj.getInt("following");
    	po.ownsRepos=getUserOwnsRepos(Name);
    	po.relatedRepos=getUserRelatedRepos(Name);
    	po.created_at=obj.getString("created_at");
    	po.updated_at=obj.getString("updated_at");
		return po;
	}
	
	public List<String> getUserOwnsRepos(String Name){
		List<String> gown=new ArrayList<String>();

		Connection conn = DBTool.getConn();
	    String sql = "SELECT DISTINCT owned FROM user_owned WHERE login='"+Name+"';";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	gown.add(rs.getString("owned"));
	        }
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

		return gown;
	}

	public List<String> getUserRelatedRepos(String Name){
		List<String> grel=new ArrayList<String>();

		Connection conn = DBTool.getConn();
	    String sql = "SELECT DISTINCT full_name FROM repos_users WHERE login='"+Name+"';";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	grel.add(rs.getString("full_name"));
	        }
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

		return grel;
	}
	
	public Map<String,Integer> getCoworkers(String Name){
		
		Map<String,Integer> map=new LinkedHashMap<String,Integer>();
		
		Connection conn = DBTool.getConn();
	    String sql = "SELECT login, count(DISTINCT full_name) as number FROM repos_users"
	    		+ " WHERE full_name IN (SELECT DISTINCT full_name FROM repos_users"
	    		+ " WHERE login='"+Name+"') AND login<>'"+Name+"'GROUP BY login"
	    				+ " HAVING number>2 ORDER BY number DESC";
	    Statement pstmt;
	    
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	map.put(rs.getString("login"), rs.getInt("number"));
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		return map;
		
	}

}

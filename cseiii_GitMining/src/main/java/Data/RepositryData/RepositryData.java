package Data.RepositryData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import PO.ReposPO;
import VO.RepoListLineItem;
import VO.RepoSortType;
import DBTool.DBTool;
import DataService.RepositryDataService.RepositryDataService;

public class RepositryData implements RepositryDataService {
	
	public static int per_page=10;

	public static ArrayList<String> nowList;
	
	public static RepoSortType type;

	public RepositryData(){

		init();

	}

	public void init(){
		
		nowList = new ArrayList<String>();

		setOrder(RepoSortType.FORKS);
	}
	
	public void search(String key){
		nowList.clear();

		Connection conn = DBTool.getConn();
	    String sql = "SELECT full_name FROM repository WHERE full_name LIKE '%"+key+"%' OR description LIKE '%"+key+"%' ORDER BY forks DESC;";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	nowList.add(rs.getString("full_name"));
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	public void setOrder(RepoSortType type){
		
		this.type=type;

		nowList.clear();

		Connection conn = DBTool.getConn();
	    String sql = "SELECT full_name FROM repository "+getOrderSqlString(type);
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	nowList.add(rs.getString("full_name"));
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}
	
	public String getOrderSqlString(RepoSortType type){
		return "ORDER BY "+type.toString()+" DESC";
	}

	public Vector<RepoListLineItem> getList(int page) {
		// TODO Auto-generated method stub

		if((page-1)*per_page>=nowList.size()){
			return new Vector<RepoListLineItem>();
		}

		Vector<RepoListLineItem> rl=new Vector<RepoListLineItem>();

		Connection conn = DBTool.getConn();
	    Statement pstmt;
		String sql = "SELECT * FROM repository WHERE full_name=";
		
		for(int i=(page-1)*per_page;i<page*per_page&&i<nowList.size();i++){
			RepoListLineItem vo=new RepoListLineItem();

			vo.add(nowList.get(i));

			try {
		        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		        ResultSet rs = pstmt.executeQuery(sql+"'"+nowList.get(i)+"'");
		        
		        rs.first();
		        
		        vo.add(rs.getString("language"));

				vo.add(String.valueOf(rs.getInt("forks")));

				vo.add(String.valueOf(rs.getInt("stars")));

				vo.add(rs.getString("updated_at"));
				
				vo.add(rs.getString("description"));
				
				String keywords = "";
				
				for(int j = getKeywords(nowList.get(i)).size()-1;j>=0;j--){
					keywords += getKeywords(nowList.get(i)).get(j) + "     ";
				}

				vo.add(keywords);
				
				pstmt.close();
				
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

			rl.add(vo);
		}

		return rl;
	}

	public ReposPO getRepositry(String Name) {
		// TODO Auto-generated method stub
		ReposPO po=new ReposPO();
		
		Connection conn = DBTool.getConn();
	    String sql = "SELECT * FROM repository WHERE full_name='"+Name+"';";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        rs.first();
	        po.description=rs.getString("description");
			
			po.forks=rs.getInt("forks");
			
			po.html_url=rs.getString("html_url");
			
			po.language=rs.getString("language");
			
			po.ownername=rs.getString("owner_name");
			
			po.reponame=rs.getString("full_name");
			
			po.stars=rs.getInt("stars");
			
			po.subscribers=rs.getInt("subscribers");
			
			po.watchers=rs.getInt("watchers");
			
			po.contributors=getContributors(Name);

			po.collaborators=getCollaborators(Name);

			po.commits=rs.getInt("commits");

			po.issues=rs.getInt("issues");

			po.pull_requests=rs.getInt("pulls");
			
			pstmt.close();
			conn.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    po.keyword=getKeywords(po.reponame);
	    
		return po;
	}
	
	public List<String> getContributors(String Name){
		
		List<String> gcon=new ArrayList<String>();

		Connection conn = DBTool.getConn();
	    String sql = "SELECT DISTINCT contributor FROM repository_contributors WHERE full_name='"+Name+"';";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	gcon.add(rs.getString("contributor"));
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

		return gcon;
		
	}

	public List<String> getCollaborators(String Name){

		List<String> gcol=new ArrayList<String>();

		Connection conn = DBTool.getConn();
	    String sql = "SELECT DISTINCT collaborator FROM repository_collaborators WHERE full_name='"+Name+"';";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	gcol.add(rs.getString("collaborator"));
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

		return gcol;

	}
	
	public Map<String,Integer> getReposRelatedByContributor(String Name){
		
		Map<String,Integer> map=new LinkedHashMap<String,Integer>();
		
		Connection conn = DBTool.getConn();
	    String sql = "SELECT full_name, count(DISTINCT login) as number FROM repos_users"
	    		+ " WHERE login IN (SELECT DISTINCT contributor FROM repository_contributors"
	    		+ " WHERE full_name='"+Name+"') AND full_name<>'"+Name+"'GROUP BY full_name"
	    				+ " HAVING number>2 ORDER BY number DESC";
	    Statement pstmt;
	    
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	map.put(rs.getString("full_name"), rs.getInt("number"));
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		return map;
		
	}

	public List<String> getKeywords(String full_name) {
		List<String> keyword=new ArrayList<String>();

		Connection conn = DBTool.getConn();
	    String sql = "SELECT keyword FROM repository_keyword WHERE full_name='"+full_name+"';";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	keyword.add(rs.getString("keyword"));
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

		return keyword;
	}

	public List<String> getReposWithOneKeyword(String keyword) {
		List<String> repos=new ArrayList<String>();

		Connection conn = DBTool.getConn();
	    String sql = "SELECT DISTINCT full_name FROM repository_keyword WHERE keyword='"+keyword+"';";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	repos.add(rs.getString("full_name"));
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

		return repos;
	}

	public List<String> getReposWithSomeKeywords(List<String> keywords) {
		List<String> result=new ArrayList<String>();
		List<String> temp;
		
		for(String s:keywords){
			temp=getReposWithOneKeyword(s);
			temp.removeAll(result);
			result.addAll(temp);
		}
		
		return result;
	}

	public int getTotalPage() {
		return (nowList.size()-1)/per_page+1;
	}
	
}

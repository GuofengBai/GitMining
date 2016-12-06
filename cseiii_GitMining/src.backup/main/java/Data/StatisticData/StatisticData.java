package Data.StatisticData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DBTool.DBTool;
import DataService.StatisticDataService.StatisticDataService;

public class StatisticData implements StatisticDataService {

	public Object[][] getCommitsStateOfContributors(String full_name) {
		Connection conn = DBTool.getConn();
	    String sql = "SELECT author_login,count(*) as num,sum(total) as tsum,avg(total) as tavg,"
	    		+ "sum(additions) as asum,avg(additions) as aavg,sum(deletions) as dsum,"
	    		+ "avg(deletions) as davg,avg(files) as favg "
	    		+ "FROM commits WHERE full_name='"+full_name+"' GROUP BY author_login;";
	    Statement pstmt;
	    ResultSet rs;
	    Object[][] result = null;
	    double total=0;
	    int i=0;
	    
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        rs = pstmt.executeQuery(sql);
	        rs.last();
	        result=new Object[rs.getRow()][10];
	        rs.beforeFirst();
	        while(rs.next()){
	        	total+=rs.getInt("tsum");
	        	result[i][0]=rs.getString("author_login");
	        	result[i][1]=rs.getInt("num");
	        	result[i][2]=rs.getInt("tsum");
	        	result[i][3]=rs.getDouble("tavg");
	        	result[i][4]=rs.getInt("asum");
	        	result[i][5]=rs.getDouble("aavg");
	        	result[i][6]=rs.getInt("dsum");
	        	result[i][7]=rs.getDouble("davg");
	        	result[i][8]=rs.getDouble("favg");
	        	i++;
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    i--;
	    for(;i>-1;i--){
	    	result[i][9]=100*((Integer)result[i][2])/total;
	    }
	    
		return result;
	}

	public Object[] getCommitsStateByRepos(String full_name) {
		Connection conn = DBTool.getConn();
	    String sql = "SELECT count(*) as num,sum(total) as tsum,avg(total) as tavg,"
	    		+ "sum(additions) as asum,avg(additions) as aavg,sum(deletions) as dsum,"
	    		+ "avg(deletions) as davg,avg(files) as favg "
	    		+ "FROM commits WHERE full_name='"+full_name+"';";
	    Statement pstmt;
	    ResultSet rs;
	    Object[] result=new Object[8];
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        rs = pstmt.executeQuery(sql);
	        rs.next();
	        result[0]=rs.getInt("num");
	        result[1]=rs.getInt("tsum");
	        result[2]=rs.getDouble("tavg");
	        result[3]=rs.getInt("asum");
	        result[4]=rs.getDouble("aavg");
	        result[5]=rs.getInt("dsum");
	        result[6]=rs.getDouble("davg");
	        result[7]=rs.getDouble("favg");
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return result;
	}

	public Object[][] getCommitsMessageByRepos(String full_name) {
		Connection conn = DBTool.getConn();
	    String sql = "SELECT * FROM commits WHERE full_name='"+full_name+"' ORDER BY date;";
	    Statement pstmt;
	    ResultSet rs;
	    Object[][] result = null;
	    int i=0;
	    
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        rs = pstmt.executeQuery(sql);
	        rs.last();
	        result=new Object[rs.getRow()][7];
	        rs.beforeFirst();
	        while(rs.next()){
	            result[i][0]=rs.getString("date");
	        	result[i][1]=rs.getString("author_login");
	        	result[i][2]=rs.getInt("total");
	        	result[i][3]=rs.getInt("additions");
	        	result[i][4]=rs.getInt("deletions");
	        	result[i][5]=rs.getInt("files");
	        	result[i][6]=rs.getString("message");
	        	i++;
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return result;
	}

	public Object[][] getCommitsStateOfRelatedRepos(String login) {
		Connection conn = DBTool.getConn();
	    String sql = "SELECT full_name,count(*) as num,sum(total) as tsum,avg(total) as tavg,"
	    		+ "sum(additions) as asum,avg(additions) as aavg,sum(deletions) as dsum,"
	    		+ "avg(deletions) as davg,avg(files) as favg "
	    		+ "FROM commits WHERE author_login='"+login+"' GROUP BY full_name;";
	    Statement pstmt;
	    ResultSet rs;
	    Object[][] result = null;
	    int i=0;
	    
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        rs = pstmt.executeQuery(sql);
	        rs.last();
	        result=new Object[rs.getRow()][9];
	        rs.beforeFirst();
	        while(rs.next()){
	        	result[i][0]=rs.getString("full_name");
	        	result[i][1]=rs.getInt("num");
	        	result[i][2]=rs.getInt("tsum");
	        	result[i][3]=rs.getDouble("tavg");
	        	result[i][4]=rs.getInt("asum");
	        	result[i][5]=rs.getDouble("aavg");
	        	result[i][6]=rs.getInt("dsum");
	        	result[i][7]=rs.getDouble("davg");
	        	result[i][8]=rs.getDouble("favg");
	        	i++;
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return result;
	}

	public Object[] getCommitsStateByUser(String login) {
		Connection conn = DBTool.getConn();
	    String sql = "SELECT count(*) as num,sum(total) as tsum,avg(total) as tavg,"
	    		+ "sum(additions) as asum,avg(additions) as aavg,sum(deletions) as dsum,"
	    		+ "avg(deletions) as davg,avg(files) as favg "
	    		+ "FROM commits WHERE author_login='"+login+"';";
	    Statement pstmt;
	    ResultSet rs;
	    Object[] result=new Object[8];
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        rs = pstmt.executeQuery(sql);
	        rs.next();
	        result[0]=rs.getInt("num");
	        result[1]=rs.getInt("tsum");
	        result[2]=rs.getDouble("tavg");
	        result[3]=rs.getInt("asum");
	        result[4]=rs.getDouble("aavg");
	        result[5]=rs.getInt("dsum");
	        result[6]=rs.getDouble("davg");
	        result[7]=rs.getDouble("favg");
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return result;
	}

	public Object[][] getCommitsMessageByUser(String login) {
		Connection conn = DBTool.getConn();
	    String sql = "SELECT * FROM commits WHERE author_login='"+login+"' ORDER BY date;";
	    Statement pstmt;
	    ResultSet rs;
	    Object[][] result = null;
	    int i=0;
	    
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        rs = pstmt.executeQuery(sql);
	        rs.last();
	        result=new Object[rs.getRow()][7];
	        rs.beforeFirst();
	        while(rs.next()){
	            result[i][0]=rs.getString("date");
	        	result[i][1]=rs.getString("full_name");
	        	result[i][2]=rs.getInt("total");
	        	result[i][3]=rs.getInt("additions");
	        	result[i][4]=rs.getInt("deletions");
	        	result[i][5]=rs.getInt("files");
	        	result[i][6]=rs.getString("message");
	        	i++;
	        }
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return result;
	}
	
	public Object[] getCommitsStatusOfUserTillOneRepository(String full_name,String login){
		 Object[] status = new Object[11];
		 Connection conn = DBTool.getConn();
		 String sql = "SELECT min(date),max(date) FROM commits WHERE author_login='"+login+
				 "' AND full_name='"+full_name+"'";
		 Statement pstmt;
		 ResultSet rs;
		 try {
			pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery(sql);
			if(rs.next()){
				status[1]=rs.getString(1);
				status[2]=rs.getString(2);
			}else{
				return status;
			}
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		 sql="SELECT min(date),count(*) as num,sum(total) as tsum,avg(total) as tavg,"
	    		+ "sum(additions) as asum,avg(additions) as aavg,sum(deletions) as dsum,"
	    		+ "avg(deletions) as davg,avg(files) as favg FROM commits WHERE author_login='"+
	    		login+"' AND date<='"+(String)status[2]+"'";
		 try {
			pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery(sql);
			if(rs.next()){
				status[0]=rs.getString(1);
				status[3]=rs.getInt("num");
				status[4]=rs.getInt("tsum");
				status[5]=rs.getDouble("tavg");
				status[6]=rs.getInt("asum");
				status[7]=rs.getDouble("aavg");
				status[8]=rs.getInt("dsum");
				status[9]=rs.getDouble("davg");
				status[10]=rs.getDouble("favg");
			}else{
				return status;
			}
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
		 }
		 
	     return status;
	 }

}

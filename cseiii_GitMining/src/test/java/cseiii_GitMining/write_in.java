package cseiii_GitMining;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import DBTool.DBTool;
import PO.UserPO;

public class write_in {
	private void doit() throws IOException, ClassNotFoundException, SQLException{
		String theway = "user_type";
		String[] op = new String[1000];
		int i = 0;
		String lineText;
		try {
			BufferedReader reader = new BufferedReader(new FileReader("resources/localData/"+theway+".txt"));
			while ((lineText = reader.readLine()) != null) {
				op[i] = lineText;
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String[] ldata;
		Connection conn = DBTool.getConn();
		PreparedStatement pstmt;
		String sql="INSERT INTO gitmining.statistic_table(table_name,word,value) values(?,?,?)";
/*		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, theway);
		pstmt.setString(2, "count");
		pstmt.setInt(3, Integer.parseInt(op[0]));
		pstmt.executeUpdate();
	    pstmt.close();
*/
		for(int t=0;t<i;t++){

			try {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        pstmt.setString(1, theway);
		        ldata = op[t].split(":");
		        pstmt.setString(2, ldata[0]);
		        pstmt.setInt(3, Integer.parseInt(ldata[1]));
		        System.out.println(pstmt);
		        pstmt.executeUpdate();
		        pstmt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }


			System.out.println(t);
		}
	
	
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException{
		write_in wocao = new write_in();
		wocao.doit();
	}
		

}

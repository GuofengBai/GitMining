package Presentation.Charts;

import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.SpiderWebPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

import DBTool.DBTool;

public class SpiderWebChart extends ApplicationFrame {

	private static int contributors=0;
	private static int commits=0;
	private static int pullrequest=0;
	private static int issues=0;
	private static int stars=0;
	private static int forks=0;
	private static double con,com,pr,is,st,fo;

	public SpiderWebChart(String paramString) {
		
		super(paramString);
	
		JPanel localJPanel = createDemoPanel(50,250, 50,  50, 50, 50);
		localJPanel.setPreferredSize(new Dimension(700, 570));
		setContentPane(localJPanel);
	}
	
	public static void DataTrans(){
		DecimalFormat df = new DecimalFormat("#0.00");
		int total_num=0;
		
		Connection conn = DBTool.getConn();
	    String sql = "SELECT count(*) as number FROM repository;";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        rs.first();
	        total_num=rs.getInt("number");
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		int conRanking=0;
		sql="SELECT full_name,count(DISTINCT contributor) as number FROM repository_contributors GROUP BY full_name HAVING number<"+contributors+";";
		try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        try {
	            rs.last();
	            conRanking= rs.getRow();
	        } catch (Exception e) {
	            // TODO: handle exception
	            e.printStackTrace();
	        }
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		int comRanking=0;
		sql="SELECT count(*) as number FROM repository WHERE commits<"+commits+";";
		try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        rs.first();
	        comRanking=rs.getInt("number");
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		int prRanking=0;
		sql="SELECT count(*) as number FROM repository WHERE pulls<"+pullrequest+";";
		try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        rs.first();
	        prRanking=rs.getInt("number");
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		int isRanking=0;
		sql="SELECT count(*) as number FROM repository WHERE issues<"+issues+";";
		try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        rs.first();
	        isRanking=rs.getInt("number");
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		int stRanking=0;
		sql="SELECT count(*) as number FROM repository WHERE stars<"+stars+";";
		try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        rs.first();
	        stRanking=rs.getInt("number");
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		int foRanking=0;
		sql="SELECT count(*) as number FROM repository WHERE forks<"+forks+";";
		try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        rs.first();
	        foRanking=rs.getInt("number");
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		
		con=(double)conRanking/total_num;
		com=(double)comRanking/total_num;
		pr=(double)prRanking/total_num;
		is=(double)isRanking/total_num;
		st=(double)stRanking/total_num;
		fo=(double)foRanking/total_num;
		//System.out.println(conRanking+" "+comRanking+" "+prRanking+" "+isRanking+" "+stRanking+" "+foRanking);
		//System.out.println(list1.size()+" "+list2.size()+" "+list2.size()+" "+list2.size()+" "+list3.size()+" "+list3.size());
		//System.out.println(df.format(con)+" "+df.format(com)+" "+df.format(pr)+" "+df.format(is)+" "+df.format(st)+" "+df.format(fo));
	}

	private static CategoryDataset createDataset() {
		
		String str1 = "First";
		String str2 = "Second";
		String str3 = "Third";
		String str4 = "Contributors";
		String str5 = "Commits";
		String str6 = "pull_requests";
		String str7 = "issues";
		String str8 = "stars";
		String str9 = "forks";
		
		DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
		localDefaultCategoryDataset.addValue(con, str1, str4);
		localDefaultCategoryDataset.addValue(com, str1, str5);
		localDefaultCategoryDataset.addValue(pr, str1, str6);
		localDefaultCategoryDataset.addValue(is, str1, str7);
		localDefaultCategoryDataset.addValue(st, str1, str8);
		localDefaultCategoryDataset.addValue(fo, str1, str9);
		
		/*
		 * localDefaultCategoryDataset.addValue(5.0D, str2, str4);
		 * localDefaultCategoryDataset.addValue(7.0D, str2, str5);
		 * localDefaultCategoryDataset.addValue(6.0D, str2, str6);
		 * localDefaultCategoryDataset.addValue(8.0D, str2, str7);
		 * localDefaultCategoryDataset.addValue(4.0D, str2, str8);
		 * localDefaultCategoryDataset.addValue(4.0D, str3, str4);
		 * localDefaultCategoryDataset.addValue(3.0D, str3, str5);
		 * localDefaultCategoryDataset.addValue(2.0D, str3, str6);
		 * localDefaultCategoryDataset.addValue(3.0D, str3, str7);
		 * localDefaultCategoryDataset.addValue(6.0D, str3, str8);
		 */
		return localDefaultCategoryDataset;
	}

	private static JFreeChart createChart(CategoryDataset paramCategoryDataset) {
		SpiderWebPlot localSpiderWebPlot = new SpiderWebPlot(paramCategoryDataset);
		localSpiderWebPlot.setMaxValue(1);
		localSpiderWebPlot.setStartAngle(0.0D);
		localSpiderWebPlot.setInteriorGap(0.15D);
		localSpiderWebPlot.setToolTipGenerator(new StandardCategoryToolTipGenerator());

		JFreeChart localJFreeChart = new JFreeChart("Repository Analysis", TextTitle.DEFAULT_FONT,
				localSpiderWebPlot, false);
		LegendTitle localLegendTitle = new LegendTitle(localSpiderWebPlot);
		localLegendTitle.setPosition(RectangleEdge.TOP);
		localJFreeChart.addSubtitle(localLegendTitle);
		ChartUtilities.applyCurrentTheme(localJFreeChart);
		TextTitle localTextTitle = new TextTitle("source:http://gitmining.net", new Font("Monospaced", 0, 10));
		localTextTitle.setPosition(RectangleEdge.BOTTOM);
		localTextTitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		localJFreeChart.addSubtitle(localTextTitle);
		return localJFreeChart;
	}

	public static JPanel createDemoPanel(int contributors, int commits, int pullrequest, int issues, int stars,
			int forks) {
		SpiderWebChart.contributors=contributors;
		SpiderWebChart.commits=commits;
		SpiderWebChart.pullrequest=pullrequest;
		SpiderWebChart.issues=issues;
		SpiderWebChart.stars=stars;
		SpiderWebChart.forks=forks;
		DataTrans();
		JFreeChart localJFreeChart = createChart(createDataset());
		return new ChartPanel(localJFreeChart);
	}

	public static void main(String[] paramArrayOfString) {
		SpiderWebChart localSpiderWebChartDemo1 = new SpiderWebChart("JFreeChart: SpiderWebChartDemo1.java");
		localSpiderWebChartDemo1.pack();
		RefineryUtilities.centerFrameOnScreen(localSpiderWebChartDemo1);
		localSpiderWebChartDemo1.setVisible(true);
	
	}
}

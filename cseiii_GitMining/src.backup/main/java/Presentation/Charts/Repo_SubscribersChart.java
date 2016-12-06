package Presentation.Charts;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import DBTool.DBTool;

public class Repo_SubscribersChart extends ApplicationFrame{
	public Repo_SubscribersChart(String paramString){
	    super(paramString);
	    JPanel localJPanel = createDemoPanel();
	    localJPanel.setPreferredSize(new Dimension(500, 270));
	    setContentPane(localJPanel);
	  }
	  
	  private static CategoryDataset createDataset(){
	    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
	    Connection conn = DBTool.getConn();
	    String sql = "SELECT word,value FROM gitmining.statistic_table where table_name='Repositry_subscribers';";
	    String str = "";
	    Statement pstmt;
	    String[] op = null;
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
	        op = new String[rowCount];
	        int sum=0;
	        for(int i=1;rs.next();){
	        	if(rs.getString("word").equals("count")){
	        		sum =rs.getInt("value");
	        	}else{
	        		op[i]=rs.getString("word")+":"+rs.getInt("value");
	        		i++;
	        	}
	        	op[0]=sum+"";
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
			for(int i=1;i<op.length;i++){
				String [] ops=op[i].split(":");
				localDefaultCategoryDataset.addValue(Double.parseDouble(ops[1]) , "Range" ,ops[0]);
			}
	    
	    return localDefaultCategoryDataset;
	  }
	  
	  private static JFreeChart createChart(CategoryDataset paramCategoryDataset){
	    JFreeChart localJFreeChart = ChartFactory.createBarChart("Numbers of Repository in Different Ranges of Subscribers", null, "Numbers", paramCategoryDataset);
	    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
	    NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
	    localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    BarRenderer localBarRenderer = (BarRenderer)localCategoryPlot.getRenderer();
	    localBarRenderer.setDrawBarOutline(false);
	    localBarRenderer.setMaximumBarWidth(0.1D);
	    localBarRenderer.setLegendItemLabelGenerator(new StandardCategorySeriesLabelGenerator("{0}"));
	    return localJFreeChart;
	  }
	  
	  public static JPanel createDemoPanel(){
	    JFreeChart localChart = createChart(createDataset());
	    return new ChartPanel(localChart);
	  }
	  
	  public static void main(String[] paramArrayOfString){
		Repo_SubscribersChart localRepo_SubscribersChart = new Repo_SubscribersChart("Repo_SubscribersChart.java");
		localRepo_SubscribersChart.pack();
	    RefineryUtilities.centerFrameOnScreen(localRepo_SubscribersChart);
	    localRepo_SubscribersChart.setVisible(true);
	  }
}

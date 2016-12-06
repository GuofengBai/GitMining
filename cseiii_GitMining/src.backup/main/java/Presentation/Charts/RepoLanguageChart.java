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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import DBTool.DBTool;


public class RepoLanguageChart extends ApplicationFrame{
	 public RepoLanguageChart(String paramString){
	    super(paramString);
	    JPanel localJPanel = createDemoPanel();
	    localJPanel.setPreferredSize(new Dimension(500, 270));
	    setContentPane(localJPanel);
	  }
	  
	  private static CategoryDataset createDataset(){
	    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
	    Connection conn = DBTool.getConn();
	    String sql = "SELECT word,value FROM gitmining.statistic_table where table_name='Repository_language';";
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
	        for(int i=0;rs.next();){
	        		op[i]=rs.getString("word")+":"+rs.getInt("value");
	        		i++;
	        	
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
			for(int i=0;i<op.length;i++){
				String [] ops=op[i].split(":");
				localDefaultCategoryDataset.addValue(Double.parseDouble(ops[1]) , "Different Languages" ,ops[0]);
			}
	    
	    return localDefaultCategoryDataset;
	  }
	  
	  private static JFreeChart createChart(CategoryDataset paramCategoryDataset){
	    JFreeChart localJFreeChart = ChartFactory.createBarChart("Numbers of Repository in Different Languages", null, "Language", paramCategoryDataset);
	    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
	    NumberAxis localNumberAxis = (NumberAxis)localCategoryPlot.getRangeAxis();
	    localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
	    localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
	    BarRenderer localBarRenderer = (BarRenderer)localCategoryPlot.getRenderer();
	    localBarRenderer.setDrawBarOutline(false);
	    localBarRenderer.setMaximumBarWidth(0.1D);
	    localBarRenderer.setLegendItemLabelGenerator(new StandardCategorySeriesLabelGenerator("{0}"));
	    return localJFreeChart;
	  }
	  
	  public static JPanel createDemoPanel(){
	    JFreeChart localJFreeChart = createChart(createDataset());
	    return new ChartPanel(localJFreeChart);
	  }
	  
	  public static void main(String[] paramArrayOfString){
		RepoLanguageChart localRepoLanguageChart = new RepoLanguageChart("RepoLanguageChart.java");
		localRepoLanguageChart.pack();
	    RefineryUtilities.centerFrameOnScreen(localRepoLanguageChart);
	    localRepoLanguageChart.setVisible(true);
	  }
}

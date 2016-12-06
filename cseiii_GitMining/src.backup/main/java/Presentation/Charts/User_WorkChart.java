package Presentation.Charts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import DBTool.DBTool;

public class User_WorkChart extends ApplicationFrame{
	private static String username;
	  public User_WorkChart(String paramString)
	  {
	    super(paramString);
	    JPanel localJPanel = createDemoPanel("mojombo");
	    localJPanel.setPreferredSize(new Dimension(500, 270));
	    setContentPane(localJPanel);
	  }
	  
	  private static XYDataset createDataset()
	  {
	    TimeSeries addition = new TimeSeries("total code",Second.class);
	    int tot = 0;
	    Second time = new Second(0, 0, 0, 1, 1, 2000);
	    String oldtime = "";
	    Connection conn = DBTool.getConn();
	    String sql = "SELECT total,date FROM gitmining.commits where author_login='"+username+"' order by date;";
	    String date = "";
	    String hour = "";
	    Statement pstmt;
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        rs.next();
	        oldtime = rs.getString("date");
	        tot = rs.getInt("total");
	        for(;rs.next();){
	        	date=oldtime.substring(0, 10);
	        	hour=oldtime.substring(11, 19);
	            String[] ymd = date.split("-");
	            if(ymd[1].charAt(0)=='0'){
	            	ymd[1] = ymd[1].substring(1);
	            }
	            if(ymd[2].charAt(0)=='0'){
	            	ymd[2] = ymd[2].substring(1);
	            }
	            String[] hms = hour.split(":");
	            if(hms[0].charAt(0)=='0'){
	            	hms[0] = hms[0].substring(1);
	            }
	            if(hms[1].charAt(0)=='0'){
	            	hms[1] = hms[1].substring(1);
	            }
	            if(hms[2].charAt(0)=='0'){
	            	hms[2] = hms[2].substring(1);
	            }
	            tot += rs.getInt("total");
	            if(oldtime.equals(rs.getString("date"))){
	            	continue;
	            }else{
	            	oldtime=rs.getString("date");
	            }
	            time = new Second(Integer.parseInt(hms[2]),Integer.parseInt(hms[1]),Integer.parseInt(hms[0]),
	            		Integer.parseInt(ymd[2]),Integer.parseInt(ymd[1]),Integer.parseInt(ymd[0]));
	            addition.add(time,new Double(tot));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    TimeSeriesCollection localTimeSeriesCollection = new TimeSeriesCollection();
	    localTimeSeriesCollection.addSeries(addition);
	    return localTimeSeriesCollection;
	  }
	  
	  private static JFreeChart createChart(XYDataset paramXYDataset)
	  {
	    JFreeChart localJFreeChart = ChartFactory.createTimeSeriesChart("User_WorkChart", "date", "code", paramXYDataset);
	    XYPlot localXYPlot = (XYPlot)localJFreeChart.getPlot();
	    localXYPlot.setDomainPannable(true);
	    DeviationRenderer localDeviationRenderer = new DeviationRenderer(true, false);
	    localDeviationRenderer.setSeriesStroke(0, new BasicStroke(3.0F, 1, 1));
	    localDeviationRenderer.setSeriesStroke(0, new BasicStroke(3.0F, 1, 1));
	    localDeviationRenderer.setSeriesStroke(1, new BasicStroke(3.0F, 1, 1));
	    localDeviationRenderer.setSeriesFillPaint(1, new Color(200, 200, 255));
	    localDeviationRenderer.setSeriesFillPaint(0, new Color(255, 200, 200));
	    localXYPlot.setRenderer(localDeviationRenderer);
	    NumberAxis localNumberAxis = (NumberAxis)localXYPlot.getRangeAxis();
	    localNumberAxis.setAutoRangeIncludesZero(false);
	    localNumberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    return localJFreeChart;
	  }
	  
	  public static JPanel createDemoPanel(String str)
	  {
		  username = str;
	    JFreeChart localJFreeChart = createChart(createDataset());
	    ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
	    localChartPanel.setMouseWheelEnabled(true);
	    return localChartPanel;
	  }
	  
	  public static void main(String[] paramArrayOfString)
	  {
		  User_WorkChart localDeviationRendererDemo1 = new User_WorkChart("JFreeChart : DeviationRendererDemo1.java");
	    localDeviationRendererDemo1.pack();
	    RefineryUtilities.centerFrameOnScreen(localDeviationRendererDemo1);
	    localDeviationRendererDemo1.setVisible(true);
	  }
}

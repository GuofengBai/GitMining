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
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import DBTool.DBTool;

public class Repo_DevelopChart
  extends ApplicationFrame
{
	private static String reponame;
  public Repo_DevelopChart(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createDemoPanel("rtucker/imap2maildir");
    localJPanel.setPreferredSize(new Dimension(500, 270));
    setContentPane(localJPanel);
  }
  
  private static XYDataset createDataset()
  {
    TimeSeries addition = new TimeSeries("addition",Second.class);
    TimeSeries deletion = new TimeSeries("deletion",Second.class);
    double add = 0;
    double del = 0;
    long count_x = 0;
    long add_count_y = 0;
    long count_x2= 0;
    long add_count_xy= 0;
    long del_count_y = 0;
    long del_count_xy= 0;
    long n = 0;
    long day =0;
    Second time = new Second(0, 0, 0, 1, 1, 2000);
    String oldtime = "";
    Connection conn = DBTool.getConn();
    String sql = "SELECT additions,deletions,date FROM gitmining.commits where full_name='"+reponame+"' order by date;";
    String date = "";
    String hour = "";
    Statement pstmt;
    try {
        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pstmt.executeQuery(sql);
        rs.next();
        oldtime = rs.getString("date");
        add = rs.getInt("additions");
        del = rs.getInt("deletions");
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
            add += rs.getInt("additions");
            del += rs.getInt("deletions");
            if(oldtime.equals(rs.getString("date"))){
            	continue;
            }else{
            	oldtime=rs.getString("date");
            }
            time = new Second(Integer.parseInt(hms[2]),Integer.parseInt(hms[1]),Integer.parseInt(hms[0]),
            		Integer.parseInt(ymd[2]),Integer.parseInt(ymd[1]),Integer.parseInt(ymd[0]));
            day = (Integer.parseInt(ymd[0])-2000)*365+(Integer.parseInt(ymd[1])-1)*30+Integer.parseInt(ymd[2])-1;
            count_x += day;
            count_x2 += day*day;
            add_count_y +=add;
            del_count_y +=del;
            add_count_xy+=add*day;
            del_count_xy+=del*day;
            n++;
            addition.add(time,new Double(add));
            deletion.add(time,new Double(del));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    double addxy = add_count_xy-count_x*add_count_y/n;
    double addxx = count_x2-count_x*count_x/n;
    double delxy = del_count_xy-count_x*del_count_y/n;
    double delxx = count_x2-count_x*count_x/n;
    double kadd = addxy/addxx;
    double kdel = delxy/delxx;
    double avgaddy = add_count_y/n;
    double avgaddx = count_x/n;
    double starta = avgaddx - avgaddy/kadd;
    double enda = (7300-avgaddx)*kadd+avgaddy;
    int y=2000,m=1,d=1;
    for(;starta>365;starta-=365,y++);
    for(;starta>30;starta-=30,m++);
    for(;starta>1;starta-=1,d++);
    TimeSeries huigui1 = new TimeSeries("goback",Second.class);
    huigui1.add(new Second(0,0,0,d,m,y),0);
    huigui1.add(new Second(0,0,0,1,1,2020),enda);
    double avgdely = del_count_y/n;
    double avgdelx = count_x/n;
    double startd = avgdelx - avgdely/kdel;
    double endd = (7300-avgdelx)*kdel+avgdely;
    y=2000;m=1;d=1;
    for(;startd>365;startd-=365,y++);
    for(;startd>30;startd-=30,m++);
    for(;startd>1;startd-=1,d++);
    TimeSeries huigui2 = new TimeSeries("goback",Second.class);
    huigui2.add(new Second(0,0,0,d,m,y),0);
    huigui2.add(new Second(0,0,0,1,1,2020),endd);
    //time = new Second(0,0,0,1,1,2020);
    //add = add+ (7300-day)*kadd;
    //del = del+ (7300-day)*kdel;
    //addition.add(time,add);
    //deletion.add(time,del);
    TimeSeriesCollection localTimeSeriesCollection = new TimeSeriesCollection();
    localTimeSeriesCollection.addSeries(deletion);
    localTimeSeriesCollection.addSeries(addition);
    localTimeSeriesCollection.addSeries(huigui2);
    localTimeSeriesCollection.addSeries(huigui1);
    return localTimeSeriesCollection;
  }
  
  private static JFreeChart createChart(XYDataset paramXYDataset)
  {
    JFreeChart localJFreeChart = ChartFactory.createTimeSeriesChart("Repo_DevelopChart", "date", "code", paramXYDataset);
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
	  reponame = str;
    JFreeChart localJFreeChart = createChart(createDataset());
    ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
    localChartPanel.setMouseWheelEnabled(true);
    return localChartPanel;
  }
  
  public static void main(String[] paramArrayOfString)
  {
	  Repo_DevelopChart localDeviationRendererDemo1 = new Repo_DevelopChart("JFreeChart : DeviationRendererDemo1.java");
    localDeviationRendererDemo1.pack();
    RefineryUtilities.centerFrameOnScreen(localDeviationRendererDemo1);
    localDeviationRendererDemo1.setVisible(true);
  }
}

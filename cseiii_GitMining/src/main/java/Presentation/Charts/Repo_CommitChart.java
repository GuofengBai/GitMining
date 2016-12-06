package Presentation.Charts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYPointerAnnotation;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYAreaRenderer;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.TableXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.TextAnchor;

import DBTool.DBTool;

public class Repo_CommitChart
  extends ApplicationFrame
{
  public Repo_CommitChart(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createDemoPanel();
    localJPanel.setPreferredSize(new Dimension(500, 270));
    setContentPane(localJPanel);
  }
  
  private static TableXYDataset createDataset()
  {
    DefaultTableXYDataset localDefaultTableXYDataset = new DefaultTableXYDataset();
    XYSeries localXYSeries1 = new XYSeries("commits", true, false);
    Connection conn = DBTool.getConn();
    String sql = "SELECT num FROM gitmining.repository_commits order by num;";
    String str = "";
    Statement pstmt;
    int num = 0 ;
    int width = 0;
    try {
        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = pstmt.executeQuery(sql);
        rs.next();
        for(width=80;width<3000;width+=20){
        	num = 0;
        	for(;rs.getInt("num")<=width;rs.next()){
        		num++;
        	}
        	localXYSeries1.add(width, num);
        }
        num=0;
        for(;rs.getInt("num")<24000;rs.next()){
        	num++;
        }
        localXYSeries1.add(3001, num);
        localXYSeries1.add(3020, num);
    } catch (SQLException e) {
        e.printStackTrace();
    }
    localDefaultTableXYDataset.addSeries(localXYSeries1);
    return localDefaultTableXYDataset;
  }
  
  private static JFreeChart createChart(TableXYDataset paramTableXYDataset)
  {
	  JFreeChart localJFreeChart = ChartFactory.createXYAreaChart("Repo_CommitChart", "Commits", "Count", paramTableXYDataset, PlotOrientation.VERTICAL, true, true, false);
	    XYPlot localXYPlot = (XYPlot)localJFreeChart.getPlot();
	    localXYPlot.setForegroundAlpha(0.65F);
	    ValueAxis localValueAxis1 = localXYPlot.getDomainAxis();
	    localValueAxis1.setTickMarkPaint(Color.black);
	    localValueAxis1.setLowerMargin(0.0D);
	    localValueAxis1.setUpperMargin(0.0D);
	    ValueAxis localValueAxis2 = localXYPlot.getRangeAxis();
	    localValueAxis2.setTickMarkPaint(Color.black);
	    XYPointerAnnotation localXYPointerAnnotation = new XYPointerAnnotation("Test", 5.0D, -500.0D, 2.356194490192345D);
	    localXYPointerAnnotation.setTipRadius(0.0D);
	    localXYPointerAnnotation.setBaseRadius(35.0D);
	    localXYPointerAnnotation.setFont(new Font("SansSerif", 0, 9));
	    localXYPointerAnnotation.setPaint(Color.blue);
	    localXYPointerAnnotation.setTextAnchor(TextAnchor.HALF_ASCENT_RIGHT);
	    localXYPlot.addAnnotation(localXYPointerAnnotation);
	    return localJFreeChart;
  }
  
  public static JPanel createDemoPanel()
  {
    JFreeChart localJFreeChart = createChart(createDataset());
    return new ChartPanel(localJFreeChart);
  }
  
  public static void main(String[] paramArrayOfString)
  {
	  Repo_CommitChart localStackedXYAreaRendererDemo1 = new Repo_CommitChart("StackedXYAreaRendererDemo1");
    localStackedXYAreaRendererDemo1.pack();
    RefineryUtilities.centerFrameOnScreen(localStackedXYAreaRendererDemo1);
    localStackedXYAreaRendererDemo1.setVisible(true);
  }
}


/* Location:              F:\User\Kobe\Desktop\Java\jfreechart-1.0.19\jfreechart-1.0.19-demo.jar!\demo\StackedXYAreaRendererDemo1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

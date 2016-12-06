package Presentation.Charts;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.Ellipse2D.Double;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

import DBTool.DBTool;

public class UserPublicGistsChart
  extends ApplicationFrame
{
  public UserPublicGistsChart(String paramString)
  {
    super(paramString);
    JPanel localJPanel = createDemoPanel();
    localJPanel.setPreferredSize(new Dimension(500, 270));
    setContentPane(localJPanel);
  }
  
  private static CategoryDataset createDataset()
  {
    DefaultCategoryDataset localDefaultCategoryDataset = new DefaultCategoryDataset();
    String[] str = getData();
    String[] s = str[0].split(" ");
    int zero = Integer.parseInt(s[0]);
    int count = Integer.parseInt(s[2]);
    localDefaultCategoryDataset.addValue(zero, "public_gists", "0");;
    for(int i=1;i<=10;i++){
    	String[] a = str[i].split(" ");
    	//System.out.println(a[1]+" "+a[0]);
    	localDefaultCategoryDataset.addValue(Integer.parseInt(a[1]),"public_gists",a[0]);
    }
    localDefaultCategoryDataset.addValue(count, "public_gists", "all");
    return localDefaultCategoryDataset;
  }
  
  private static JFreeChart createChart(CategoryDataset paramCategoryDataset)
  {
    JFreeChart localJFreeChart = ChartFactory.createLineChart("Gists Per User", null, "User", paramCategoryDataset, PlotOrientation.VERTICAL, false, true, false);
    TextTitle localTextTitle = new TextTitle("Source: Java In A Nutshell (5th Edition) by David Flanagan (O'Reilly)");
    CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
    localCategoryPlot.setRangePannable(true);
    localCategoryPlot.setRangeGridlinesVisible(false);
    URL localURL = UserPublicReposChart.class.getClassLoader().getResource("OnBridge11small.png");
    if (localURL != null)
    {
      ImageIcon localObject = new ImageIcon(localURL);
      localJFreeChart.setBackgroundImage(((ImageIcon)localObject).getImage());
      localCategoryPlot.setBackgroundPaint(null);
    }
    Object localObject = (NumberAxis)localCategoryPlot.getRangeAxis();
    ((NumberAxis)localObject).setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    ChartUtilities.applyCurrentTheme(localJFreeChart);
    LineAndShapeRenderer localLineAndShapeRenderer = (LineAndShapeRenderer)localCategoryPlot.getRenderer();
    localLineAndShapeRenderer.setBaseShapesVisible(true);
    localLineAndShapeRenderer.setDrawOutlines(true);
    localLineAndShapeRenderer.setUseFillPaint(true);
    localLineAndShapeRenderer.setBaseFillPaint(Color.white);
    localLineAndShapeRenderer.setSeriesStroke(0, new BasicStroke(3.0F));
    localLineAndShapeRenderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
    localLineAndShapeRenderer.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5.0D, -5.0D, 10.0D, 10.0D));
    return localJFreeChart;
  }
  
  public static JPanel createDemoPanel()
  {
    JFreeChart localJFreeChart = createChart(createDataset());
    ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
    localChartPanel.setMouseWheelEnabled(true);
    return localChartPanel;
  }
  public static String[] getData() {
		// TODO Auto-generated method stub
	  Connection conn = DBTool.getConn();
	    String sql = "SELECT word,value FROM gitmining.statistic_table where table_name='User_public_gists';";
	    String str = "";
	    Statement pstmt;
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
	        String[] op = new String[rowCount];
	        int zero=0;
	        int max=0;
	        int sum=0;
	        for(int i=1;rs.next();){
	        	if(rs.getString("word").equals("zero")){
	        		zero =rs.getInt("value");
	        	}else
	        	if(rs.getString("word").equals("max")){
	        		max =rs.getInt("value");
	        	}else
	        	if(rs.getString("word").equals("sum")){
	        		sum =rs.getInt("value");
	        	}else{
	        		op[i]=rs.getString("word")+" "+rs.getInt("value");
	        		i++;
	        	}
	        	op[0]=zero+" "+max+" "+sum;
	        }
	        return op;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
  
  public static void main(String[] paramArrayOfString)
  {
    UserPublicGistsChart localLineChartDemo1 = new UserPublicGistsChart("JFreeChart: LineChartDemo1.java");
    localLineChartDemo1.pack();
    RefineryUtilities.centerFrameOnScreen(localLineChartDemo1);
    localLineChartDemo1.setVisible(true);
  }
}


/* Location:              F:\User\Kobe\Desktop\Java\jfreechart-1.0.19\jfreechart-1.0.19-demo.jar!\demo\LineChartDemo1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */

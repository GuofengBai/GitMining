package Presentation.Charts;

import java.awt.BasicStroke;
import java.awt.Color;
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

import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.xy.AbstractXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

import DBTool.DBTool;
import PO.ReposPO;

public class ForksAndStarsScatterPlot extends ApplicationFrame {
	static XYDataset data=new MyXYData(" ");
	public ForksAndStarsScatterPlot(String paramString) {
		super(paramString);
		JPanel localJPanel = createDemoPanel();
		localJPanel.setPreferredSize(new Dimension(900, 470));
		setContentPane(localJPanel);
	}

	private static JFreeChart createChart(XYDataset paramXYDataset) {
		DecimalFormat df = new DecimalFormat("#0.00"); 
		JFreeChart localJFreeChart = ChartFactory.createScatterPlot(
				"Scatter Plot by Forks and Stars in each Repository \n"+"ρ="+ df.format(((MyXYData) data).getCC()), "Stars", "Forks", paramXYDataset,
				PlotOrientation.VERTICAL, true, true, false);
		XYPlot localXYPlot = (XYPlot) localJFreeChart.getPlot();
		localXYPlot.setNoDataMessage("NO DATA");
		localXYPlot.setDomainPannable(true);
		localXYPlot.setRangePannable(true);
		localXYPlot.setDomainZeroBaselineVisible(true);
		localXYPlot.setRangeZeroBaselineVisible(true);
		localXYPlot.setDomainGridlineStroke(new BasicStroke(0.0F));
		localXYPlot.setDomainMinorGridlineStroke(new BasicStroke(0.0F));
		localXYPlot.setDomainGridlinePaint(Color.blue);
		localXYPlot.setRangeGridlineStroke(new BasicStroke(0.0F));
		localXYPlot.setRangeMinorGridlineStroke(new BasicStroke(0.0F));
		localXYPlot.setRangeGridlinePaint(Color.blue);
		localXYPlot.setDomainMinorGridlinesVisible(true);
		localXYPlot.setRangeMinorGridlinesVisible(true);
		XYLineAndShapeRenderer localXYLineAndShapeRenderer = (XYLineAndShapeRenderer) localXYPlot.getRenderer();
		localXYLineAndShapeRenderer.setSeriesOutlinePaint(0, Color.black);
		localXYLineAndShapeRenderer.setUseOutlinePaint(true);
		NumberAxis localNumberAxis1 = (NumberAxis) localXYPlot.getDomainAxis();
		localNumberAxis1.setAutoRangeIncludesZero(false);
		localNumberAxis1.setTickMarkInsideLength(2.0F);
		localNumberAxis1.setTickMarkOutsideLength(2.0F);
		localNumberAxis1.setMinorTickCount(2);
		localNumberAxis1.setMinorTickMarksVisible(true);
		NumberAxis localNumberAxis2 = (NumberAxis) localXYPlot.getRangeAxis();
		localNumberAxis2.setTickMarkInsideLength(2.0F);
		localNumberAxis2.setTickMarkOutsideLength(2.0F);
		localNumberAxis2.setMinorTickCount(2);
		localNumberAxis2.setMinorTickMarksVisible(true);
		TextTitle localTextTitle = new TextTitle("source:http://gitmining.net", new Font("Monospaced", 0, 10));
        localTextTitle.setPosition(RectangleEdge.BOTTOM);
        localTextTitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        localJFreeChart.addSubtitle(localTextTitle);
		return localJFreeChart;
		
	}

	public static JPanel createDemoPanel() {
		
		JFreeChart localJFreeChart = createChart(data);
		ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
		localChartPanel.setMouseWheelEnabled(true);
		return localChartPanel;
	}

	public static void main(String[] paramArrayOfString) {
		ForksAndStarsScatterPlot localScatterPlotDemo1 = new ForksAndStarsScatterPlot("Scatter Plot by Forks and Stars");
		localScatterPlotDemo1.pack();
		RefineryUtilities.centerFrameOnScreen(localScatterPlotDemo1);
		localScatterPlotDemo1.setVisible(true);
		//MyXYData data = new MyXYData("  ");
	}
}

class MyXYData extends AbstractXYDataset implements XYDataset, DomainInfo, RangeInfo {
	private Double[][] xValues;
	private Double[][] yValues;
	private int seriesCount;
	private int itemCount;
	private Number domainMin;
	private Number domainMax;
	private Number rangeMin;
	private Number rangeMax;
	private Range domainRange;
	private Range range;
	public double SDX;
	public double SDY;
	public double Correlationcoefficient;
	//ArrayList<String> list = new ArrayList<String>();
	
	public double getSDX(){
		return this.SDX;
	}
	public double getSDY(){
		return this.SDY;
	}
	public double getCC(){
		return this.Correlationcoefficient;
	}

	public MyXYData(String filename) {
		Connection conn = DBTool.getConn();
	    String sql = "SELECT stars,forks FROM repository;";
	    Statement pstmt;
	    int lines=0;
	    int[] data1=new int[4000];
	    int[] data2=new int[4000];
	    try {
	        pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        ResultSet rs = pstmt.executeQuery(sql);
	        while(rs.next()){
	        	data1[lines]=rs.getInt("stars");
	        	data2[lines]=rs.getInt("forks");
	        	lines++;
	        }
	        pstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		// for(int i=0;i<list.size();i++){
		// System.out.println(list.get(i));
		// }
		this.xValues = new Double[1][lines];
		this.yValues = new Double[1][lines];
		this.seriesCount = 1;
		this.itemCount = lines;
		this.SDX=0;
		this.SDY=0;
		this.Correlationcoefficient=0;
		double d1 = Double.POSITIVE_INFINITY;
		double d2 = Double.NEGATIVE_INFINITY;
		double d3 = Double.POSITIVE_INFINITY;
		double d4 = Double.NEGATIVE_INFINITY;
		double Xavg = 0, Yavg = 0;
		for (int i = 0; i < lines; i++) {
			double d5 = (double)data1[i];
			Xavg = (Xavg + d5);
			{
				this.xValues[0][i] = new Double(d5);
				if (d5 < d1) {
					d1 = d5;
				}
				if (d5 > d2) {
					d2 = d5;
				}
			}
			double d6 = (double)data2[i];
			Yavg = (Yavg + d6);
			{
				this.yValues[0][i] = new Double(d6);
				if (d6 < d3) {
					d3 = d6;
				}
				if (d6 > d4) {
					d4 = d6;
				}
			}
		}
		Xavg = Xavg / itemCount;
		Yavg = Yavg / itemCount;
		double tempSumX=0,tempSumY=0;
		double fenzi=0,fenmu=0;
		for (int i = 0; i < lines; i++) {
	
			double d5 = (double)data1[i];
			double d6 = (double)data2[i];
			tempSumX=tempSumX+(d5-Xavg)*(d5-Xavg);
			tempSumY=tempSumY+(d6-Yavg)*(d6-Yavg);
			fenzi=fenzi+(d5-Xavg)*(d6-Yavg);
			
		}
		fenmu=Math.sqrt(tempSumX*tempSumY);
		SDX=Math.sqrt(tempSumX/(itemCount-1));
		SDY=Math.sqrt(tempSumY/(itemCount-1));
		this.Correlationcoefficient=fenzi/fenmu;
		

		this.domainMin = new Double(d1);
		this.domainMax = new Double(d2);
		this.domainRange = new Range(d1, d2);
		this.rangeMin = new Double(d3);
		this.rangeMax = new Double(d4);
		this.range = new Range(d3, d4);
	}

	public double getRangeLowerBound() {
		return this.rangeMin.doubleValue();
	}

	public double getRangeLowerBound(boolean paramBoolean) {
		return this.rangeMin.doubleValue();
	}

	public double getRangeUpperBound() {
		return this.rangeMax.doubleValue();
	}

	public double getRangeUpperBound(boolean paramBoolean) {
		return this.rangeMax.doubleValue();
	}

	public Range getRangeBounds(boolean paramBoolean) {
		return this.range;
	}

	public Range getDomainBounds() {
		return this.domainRange;
	}

	public Range getDomainBounds(boolean paramBoolean) {
		return this.domainRange;
	}

	public double getDomainLowerBound() {
		return this.domainMin.doubleValue();
	}

	public double getDomainLowerBound(boolean paramBoolean) {
		return this.domainMin.doubleValue();
	}

	public double getDomainUpperBound() {
		return this.domainMax.doubleValue();
	}

	public double getDomainUpperBound(boolean paramBoolean) {
		return this.domainMax.doubleValue();
	}

	public int getItemCount(int paramInt) {
		return this.itemCount;
	}

	public Number getX(int paramInt1, int paramInt2) {
		return this.xValues[paramInt1][paramInt2];
	}

	public Number getY(int paramInt1, int paramInt2) {
		return this.yValues[paramInt1][paramInt2];
	}

	@Override
	public int getSeriesCount() {
		return this.seriesCount;
	}

	@Override
	public Comparable getSeriesKey(int paramInt) {
		return "Sample " + paramInt;
	}

}

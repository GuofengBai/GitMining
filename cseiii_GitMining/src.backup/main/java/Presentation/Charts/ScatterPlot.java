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

public class ScatterPlot extends ApplicationFrame {
	static XYDataset data = new StarsXYData(" ");
	static XYDataset data1 = new ForksXYData(" ");
	static XYDataset data2 = new IssuesXYData(" ");

	public ScatterPlot(String paramString) {
		super(paramString);
		JPanel localJPanel = createStarsPanel();
		localJPanel.setPreferredSize(new Dimension(900, 470));
		setContentPane(localJPanel);
	}

	private static JFreeChart createStarsChart(XYDataset paramXYDataset) {
		DecimalFormat df = new DecimalFormat("#0.00");
		JFreeChart localJFreeChart = ChartFactory.createScatterPlot(
				"Scatter Plot by Stars and D/A\n" + "ρ=" + df.format(((StarsXYData) data).getCC()),  "Deletions/Additions","Stars",
				paramXYDataset, PlotOrientation.VERTICAL, true, true, false);
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

	public static JPanel createStarsPanel() {

		JFreeChart localJFreeChart = createStarsChart(data);
		ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
		localChartPanel.setMouseWheelEnabled(true);
		return localChartPanel;
	}
	private static JFreeChart createForksChart(XYDataset paramXYDataset) {
		DecimalFormat df = new DecimalFormat("#0.00");
		JFreeChart localJFreeChart = ChartFactory.createScatterPlot(
				"Scatter Plot by Forks and D/A\n" + "ρ=" + df.format(((ForksXYData) data1).getCC()),  "Deletions/Additions","Forks",
				paramXYDataset, PlotOrientation.VERTICAL, true, true, false);
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

	public static JPanel createForkssPanel() {

		JFreeChart localJFreeChart = createForksChart(data1);
		ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
		localChartPanel.setMouseWheelEnabled(true);
		return localChartPanel;
	}
	private static JFreeChart createIssuesChart(XYDataset paramXYDataset) {
		DecimalFormat df = new DecimalFormat("#0.00");
		JFreeChart localJFreeChart = ChartFactory.createScatterPlot(
				"Scatter Plot by Size and D+A\n" + "ρ=" + df.format(((IssuesXYData) data2).getCC()),  "Deletions+Additions","Size",
				paramXYDataset, PlotOrientation.VERTICAL, true, true, false);
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

	public static JPanel createIssuesPanel() {

		JFreeChart localJFreeChart = createIssuesChart(data2);
		ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
		localChartPanel.setMouseWheelEnabled(true);
		return localChartPanel;
	}

	public static void main(String[] paramArrayOfString) {
		ScatterPlot localScatterPlotDemo1 = new ScatterPlot("Scatter Plot by Forks and Stars");
		localScatterPlotDemo1.pack();
		RefineryUtilities.centerFrameOnScreen(localScatterPlotDemo1);
		localScatterPlotDemo1.setVisible(true);
		// StarsXYData data = new StarsXYData(" ");
	}
}

class StarsXYData extends AbstractXYDataset implements XYDataset, DomainInfo, RangeInfo {
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
	// ArrayList<String> list = new ArrayList<String>();

	public double getSDX() {
		return this.SDX;
	}

	public double getSDY() {
		return this.SDY;
	}

	public double getCC() {
		return this.Correlationcoefficient;
	}

	public StarsXYData(String filename) {
		Connection conn = DBTool.getConn();
		String sql = "SELECT stars,full_name FROM repository order by stars desc;";
		String sql1 = "SELECT dsum,asum,full_name FROM repository_commits WHERE asum>dsum;";
		Statement pstmt;
		int lines1 = 0;
		int lines2 = 0;
		int[] data1 = new int[4000];
		int[] stars = new int[4000];
		String[] names1 = new String[4000];
		String[] names2 = new String[4000];
		Double[] data2 = new Double[4000];
		Double[] dou3 = new Double[4000];
		Double[] dou4 = new Double[4000];
		int[] data3 = new int[4000];
		int[] data4 = new int[4000];
		try {
			pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				stars[lines1] = rs.getInt("stars");
				names1[lines1] = rs.getString("full_name");
				lines1++;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {

			pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery(sql1);
			while (rs.next()) {
				data3[lines2] = rs.getInt("dsum");
				data4[lines2] = rs.getInt("asum");
				dou3[lines2]=(double)data3[lines2];
				dou4[lines2]=(double)data4[lines2];
				names2[lines2] = rs.getString("full_name");
				lines2++;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for(int i=0;i<list.size();i++){
		// System.out.println(list.get(i));
		// }
		int min = 0;
		if (lines1 >= lines2)
			min = lines2;
		else
			min = lines1;
		int k = 0;
		for (int i = 0; i < lines1; i++) {
			for (int j = 0; j < lines2; j++) {
				if (names1[i].equals(names2[j])) {
					data1[k] = stars[i];
					if (dou4[j] != 0) {
						data2[k] =  (dou3[j] / dou4[j]);
						//System.out.println(dou3[j] + " " + dou4[j] + " " + data2[k]);
					} else{
						data2[k] = 0.0;
						}
					k++;
				}
			}
		}
		this.xValues = new Double[1][min];
		this.yValues = new Double[1][min];
		this.seriesCount = 1;
		this.itemCount = min;
		this.SDX = 0;
		this.SDY = 0;
		this.Correlationcoefficient = 0;
		double d1 = Double.POSITIVE_INFINITY;
		double d2 = Double.NEGATIVE_INFINITY;
		double d3 = Double.POSITIVE_INFINITY;
		double d4 = Double.NEGATIVE_INFINITY;
		double Xavg = 0, Yavg = 0;
		for (int i = 0; i < 300; i++) {
			double d5 = (double) data1[i];
			Xavg = (Xavg + d5);
			{
				this.yValues[0][i] = new Double(d5);
				if (d5 < d1) {
					d1 = d5;
				}
				if (d5 > d2) {
					d2 = d5;
				}
			}
			double d6 = data2[i];
			Yavg = (Yavg + d6);
			{
				this.xValues[0][i] = new Double(d6);
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
		double tempSumX = 0, tempSumY = 0;
		double fenzi = 0, fenmu = 0;
		for (int i = 0; i < 300; i++) {

			double d5 = (double) data1[i];
			double d6 = (double) data2[i];
			tempSumX = tempSumX + (d5 - Xavg) * (d5 - Xavg);
			tempSumY = tempSumY + (d6 - Yavg) * (d6 - Yavg);
			fenzi = fenzi + (d5 - Xavg) * (d6 - Yavg);

		}
		fenmu = Math.sqrt(tempSumX * tempSumY);
		SDX = Math.sqrt(tempSumX / (itemCount - 1));
		SDY = Math.sqrt(tempSumY / (itemCount - 1));
		this.Correlationcoefficient = fenzi / fenmu;

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
class ForksXYData extends AbstractXYDataset implements XYDataset, DomainInfo, RangeInfo {
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
	// ArrayList<String> list = new ArrayList<String>();

	public double getSDX() {
		return this.SDX;
	}

	public double getSDY() {
		return this.SDY;
	}

	public double getCC() {
		return this.Correlationcoefficient;
	}

	public ForksXYData(String filename) {
		Connection conn = DBTool.getConn();
		String sql = "SELECT forks,full_name FROM repository order by forks desc;";
		String sql1 = "SELECT dsum,asum,full_name FROM repository_commits WHERE asum>dsum;";
		Statement pstmt;
		int lines1 = 0;
		int lines2 = 0;
		int[] data1 = new int[4000];
		int[] forks = new int[4000];
		String[] names1 = new String[4000];
		String[] names2 = new String[4000];
		Double[] data2 = new Double[4000];
		Double[] dou3 = new Double[4000];
		Double[] dou4 = new Double[4000];
		int[] data3 = new int[4000];
		int[] data4 = new int[4000];
		try {
			pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				forks[lines1] = rs.getInt("forks");
				names1[lines1] = rs.getString("full_name");
				lines1++;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {

			pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery(sql1);
			while (rs.next()) {
				data3[lines2] = rs.getInt("dsum");
				data4[lines2] = rs.getInt("asum");
				dou3[lines2]=(double)data3[lines2];
				dou4[lines2]=(double)data4[lines2];
				names2[lines2] = rs.getString("full_name");
				lines2++;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for(int i=0;i<list.size();i++){
		// System.out.println(list.get(i));
		// }
		int min = 0;
		if (lines1 >= lines2)
			min = lines2;
		else
			min = lines1;
		int k = 0;
		for (int i = 0; i < lines1; i++) {
			for (int j = 0; j < lines2; j++) {
				if (names1[i].equals(names2[j])) {
					data1[k] = forks[i];
					if (dou4[j] != 0) {
						data2[k] =  (dou3[j] / dou4[j]);
						//System.out.println(dou3[j] + " " + dou4[j] + " " + data2[k]);
					} else{
						data2[k] = 0.0;
						}
					k++;
				}
			}
		}
		this.xValues = new Double[1][min];
		this.yValues = new Double[1][min];
		this.seriesCount = 1;
		this.itemCount = min;
		this.SDX = 0;
		this.SDY = 0;
		this.Correlationcoefficient = 0;
		double d1 = Double.POSITIVE_INFINITY;
		double d2 = Double.NEGATIVE_INFINITY;
		double d3 = Double.POSITIVE_INFINITY;
		double d4 = Double.NEGATIVE_INFINITY;
		double Xavg = 0, Yavg = 0;
		for (int i = 0; i < 300; i++) {
			double d5 = (double) data1[i];
			Xavg = (Xavg + d5);
			{
				this.yValues[0][i] = new Double(d5);
				if (d5 < d1) {
					d1 = d5;
				}
				if (d5 > d2) {
					d2 = d5;
				}
			}
			double d6 = data2[i];
			Yavg = (Yavg + d6);
			{
				this.xValues[0][i] = new Double(d6);
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
		double tempSumX = 0, tempSumY = 0;
		double fenzi = 0, fenmu = 0;
		for (int i = 0; i < 300; i++) {

			double d5 = (double) data1[i];
			double d6 = (double) data2[i];
			tempSumX = tempSumX + (d5 - Xavg) * (d5 - Xavg);
			tempSumY = tempSumY + (d6 - Yavg) * (d6 - Yavg);
			fenzi = fenzi + (d5 - Xavg) * (d6 - Yavg);

		}
		fenmu = Math.sqrt(tempSumX * tempSumY);
		SDX = Math.sqrt(tempSumX / (itemCount - 1));
		SDY = Math.sqrt(tempSumY / (itemCount - 1));
		this.Correlationcoefficient = fenzi / fenmu;

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
class IssuesXYData extends AbstractXYDataset implements XYDataset, DomainInfo, RangeInfo {
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
	// ArrayList<String> list = new ArrayList<String>();

	public double getSDX() {
		return this.SDX;
	}

	public double getSDY() {
		return this.SDY;
	}

	public double getCC() {
		return this.Correlationcoefficient;
	}

	public IssuesXYData(String filename) {
		Connection conn = DBTool.getConn();
		String sql = "SELECT size,full_name FROM repository order by size desc;";
		String sql1 = "SELECT dsum,asum,full_name,tsum FROM repository_commits WHERE asum>dsum AND tsum<1000000;";
		Statement pstmt;
		int lines1 = 0;
		int lines2 = 0;
		int[] data1 = new int[4000];
		int[] issues = new int[4000];
		String[] names1 = new String[4000];
		String[] names2 = new String[4000];
		Double[] data2 = new Double[4000];
		Double[] dou3 = new Double[4000];
		Double[] dou4 = new Double[4000];
		int[] data3 = new int[4000];
		int[] data4 = new int[4000];
		try {
			pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {
				issues[lines1] = rs.getInt("size");
				names1[lines1] = rs.getString("full_name");
				lines1++;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {

			pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = pstmt.executeQuery(sql1);
			while (rs.next()) {
				data3[lines2] = rs.getInt("dsum");
				data4[lines2] = rs.getInt("asum");
				dou3[lines2]=(double)data3[lines2];
				dou4[lines2]=(double)data4[lines2];
				names2[lines2] = rs.getString("full_name");
				lines2++;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// for(int i=0;i<list.size();i++){
		// System.out.println(list.get(i));
		// }
		int min = 0;
		if (lines1 >= lines2)
			min = lines2;
		else
			min = lines1;
		int k = 0;
		for (int i = 0; i < lines1; i++) {
			for (int j = 0; j < lines2; j++) {
				if (names1[i].equals(names2[j])) {
					data1[k] = issues[i];
					if (dou4[j] != 0) {
						data2[k] =  (dou3[j] + dou4[j]);
						//System.out.println(dou3[j] + " " + dou4[j] + " " + data2[k]);
					} else{
						data2[k] = 0.0;
						}
					k++;
				}
			}
		}
		this.xValues = new Double[1][min];
		this.yValues = new Double[1][min];
		this.seriesCount = 1;
		this.itemCount = min;
		this.SDX = 0;
		this.SDY = 0;
		this.Correlationcoefficient = 0;
		double d1 = Double.POSITIVE_INFINITY;
		double d2 = Double.NEGATIVE_INFINITY;
		double d3 = Double.POSITIVE_INFINITY;
		double d4 = Double.NEGATIVE_INFINITY;
		double Xavg = 0, Yavg = 0;
		for (int i = 0; i < 300; i++) {
			double d5 = (double) data1[i];
			Xavg = (Xavg + d5);
			{
				this.yValues[0][i] = new Double(d5);
				if (d5 < d1) {
					d1 = d5;
				}
				if (d5 > d2) {
					d2 = d5;
				}
			}
			double d6 = data2[i];
			Yavg = (Yavg + d6);
			{
				this.xValues[0][i] = new Double(d6);
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
		double tempSumX = 0, tempSumY = 0;
		double fenzi = 0, fenmu = 0;
		for (int i = 0; i < 300; i++) {

			double d5 = (double) data1[i];
			double d6 = (double) data2[i];
			tempSumX = tempSumX + (d5 - Xavg) * (d5 - Xavg);
			tempSumY = tempSumY + (d6 - Yavg) * (d6 - Yavg);
			fenzi = fenzi + (d5 - Xavg) * (d6 - Yavg);

		}
		fenmu = Math.sqrt(tempSumX * tempSumY);
		SDX = Math.sqrt(tempSumX / (itemCount - 1));
		SDY = Math.sqrt(tempSumY / (itemCount - 1));
		this.Correlationcoefficient = fenzi / fenmu;

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

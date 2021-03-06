package Presentation.Charts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

import DBTool.DBTool;

public class UserTypeChart extends ApplicationFrame {
	public UserTypeChart(String paramString) {
		super(paramString);
		JPanel localJPanel = createDemoPanel();
		localJPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(localJPanel);
	}

	private static PieDataset createDataset() {
		 Connection conn = DBTool.getConn();
		    String sql = "SELECT word,value FROM gitmining.statistic_table where table_name='user_type';";
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
		
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		double sum=0;
		for(int i=0;i<op.length;i++){
			String [] ops =op[i].split(":");
			sum=sum+Double.parseDouble(ops[1]);
		}
		for(int i=0;i<op.length;i++){
			String [] ops =op[i].split(":");
			localDefaultPieDataset.setValue(ops[0],Double.parseDouble(ops[1])/sum);
		}
	
		return localDefaultPieDataset;
	}

	private static JFreeChart createChart(PieDataset paramPieDataset) {
		JFreeChart localJFreeChart = ChartFactory.createPieChart("User Type Chart", paramPieDataset, true, true,
				false);
		PiePlot localPiePlot = (PiePlot) localJFreeChart.getPlot();
		localPiePlot.setSectionPaint("One", new Color(160, 160, 255));
		localPiePlot.setSectionPaint("Two", new Color(128, 128, 223));
		localPiePlot.setSectionPaint("Three", new Color(96, 96, 191));
		localPiePlot.setSectionPaint("Four", new Color(64, 64, 159));
		localPiePlot.setSectionPaint("Five", new Color(32, 32, 127));
		localPiePlot.setSectionPaint("Six", new Color(0, 0, 111));
		localPiePlot.setNoDataMessage("No data available");
		localPiePlot.setExplodePercent("Two", 0.2D);
		localPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2} percent)"));
		localPiePlot.setLabelBackgroundPaint(new Color(220, 220, 220));
		localPiePlot
				.setLegendLabelToolTipGenerator(new StandardPieSectionLabelGenerator("Tooltip for legend item {0}"));
		localPiePlot.setSimpleLabels(true);
		localPiePlot.setInteriorGap(0.0D);
		 TextTitle localTextTitle = new TextTitle("source:http://gitmining.net", new Font("Monospaced", 0, 10));
		    localTextTitle.setPosition(RectangleEdge.BOTTOM);
		    localTextTitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		    localJFreeChart.addSubtitle(localTextTitle);
		return localJFreeChart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart localJFreeChart = createChart(createDataset());
		ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
		localChartPanel.setMouseWheelEnabled(true);
		return localChartPanel;
	}

	public static void main(String[] paramArrayOfString) {
		UserTypeChart localPieChartDemo2 = new UserTypeChart("User Type Chart.java");
		localPieChartDemo2.pack();
		RefineryUtilities.centerFrameOnScreen(localPieChartDemo2);
		localPieChartDemo2.setVisible(true);
	}
}

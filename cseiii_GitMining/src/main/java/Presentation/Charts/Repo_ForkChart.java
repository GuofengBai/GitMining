package Presentation.Charts;

import java.awt.Color;
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
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import DBTool.DBTool;

public class Repo_ForkChart extends ApplicationFrame{
	public Repo_ForkChart(String paramString){
		super(paramString);
		JPanel localJPanel = createDemoPanel();
		localJPanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(localJPanel);
	}

	private static PieDataset createDataset() {
		Connection conn = DBTool.getConn();
	    String sql = "SELECT word,value FROM gitmining.statistic_table where table_name='Repository_fork';";
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
	        	if(rs.getString("word").equals("sum")){
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
		
		DefaultPieDataset localDefaultPieDataset = new DefaultPieDataset();
		double sum=Double.parseDouble(op[0]);
		for(int i=1;i<op.length;i++){
			String [] ops =op[i].split(":");
			localDefaultPieDataset.setValue(ops[0],Double.parseDouble(ops[1])/sum);
		}
	
		return localDefaultPieDataset;
	}

	private static JFreeChart createChart(PieDataset paramPieDataset) {
		JFreeChart localJFreeChart = ChartFactory.createPieChart("Repositry Forked or Not", paramPieDataset, true, true,
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
		return localJFreeChart;
	}

	public static JPanel createDemoPanel() {
		JFreeChart localJFreeChart = createChart(createDataset());
		ChartPanel localChartPanel = new ChartPanel(localJFreeChart);
		localChartPanel.setMouseWheelEnabled(true);
		return localChartPanel;
	}

	public static void main(String[] paramArrayOfString) {
		Repo_ForkChart localPieChart = new Repo_ForkChart("Repo_ForkChart.java");
		localPieChart.pack();
		RefineryUtilities.centerFrameOnScreen(localPieChart);
		localPieChart.setVisible(true);
	}
}

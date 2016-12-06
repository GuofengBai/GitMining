package Presentation.Charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

import DBTool.DBTool;

import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

/**
 * Created by guofengbai on 16-4-11.
 */
public class Repository_ContributorsChart extends ApplicationFrame {

    public Repository_ContributorsChart(String paramString)
    {
        super(paramString);
        JPanel localJPanel = createDemoPanel();
        localJPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(localJPanel);
    }

    public static JFreeChart createChart(CategoryDataset[] paramArrayOfCategoryDataset)
    {
        JFreeChart localJFreeChart = ChartFactory.createBarChart("contributors per repository Chart", "contributors", "repositories", paramArrayOfCategoryDataset[0]);
        localJFreeChart.addSubtitle(new TextTitle("As at april 2016"));
        localJFreeChart.removeLegend();
        CategoryPlot localCategoryPlot = (CategoryPlot)localJFreeChart.getPlot();
        CategoryAxis localCategoryAxis = localCategoryPlot.getDomainAxis();
        localCategoryAxis.setLowerMargin(0.02D);
        localCategoryAxis.setUpperMargin(0.02D);
        localCategoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
        NumberAxis localNumberAxis1 = (NumberAxis)localCategoryPlot.getRangeAxis();
        localNumberAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        LineAndShapeRenderer localLineAndShapeRenderer = new LineAndShapeRenderer();
        NumberAxis localNumberAxis2 = new NumberAxis("Percent");
        localNumberAxis2.setNumberFormatOverride(NumberFormat.getPercentInstance());
        localCategoryPlot.setRangeAxis(1, localNumberAxis2);
        localCategoryPlot.setDataset(1, paramArrayOfCategoryDataset[1]);
        localCategoryPlot.setRenderer(1, localLineAndShapeRenderer);
        localCategoryPlot.mapDatasetToRangeAxis(1, 1);
        localCategoryPlot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
        ChartUtilities.applyCurrentTheme(localJFreeChart);
        TextTitle localTextTitle = new TextTitle("source:http://gitmining.net", new Font("Monospaced", 0, 10));
        localTextTitle.setPosition(RectangleEdge.BOTTOM);
        localTextTitle.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        localJFreeChart.addSubtitle(localTextTitle);
        return localJFreeChart;
    }

    public static CategoryDataset[] createDatasets()
    {
        DefaultKeyedValues localDefaultKeyedValues = new DefaultKeyedValues();

        int length;

        Connection conn = DBTool.getConn();
	    String sql = "SELECT word,value FROM gitmining.statistic_table where table_name='Repository_contributors';";
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
	        for(;rs.next();){
	        	String[] ops={rs.getString("word"),rs.getInt("value")+""};
                localDefaultKeyedValues.addValue(ops[0],Integer.parseInt(ops[1]));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

        KeyedValues localKeyedValues = DataUtilities.getCumulativePercentages(localDefaultKeyedValues);
        CategoryDataset localCategoryDataset1 = DatasetUtilities.createCategoryDataset("contributors", localDefaultKeyedValues);
        CategoryDataset localCategoryDataset2 = DatasetUtilities.createCategoryDataset("Cumulative", localKeyedValues);
        return new CategoryDataset[] { localCategoryDataset1, localCategoryDataset2 };
    }

    public static JPanel createDemoPanel()
    {
        CategoryDataset[] arrayOfCategoryDataset = createDatasets();
        JFreeChart localJFreeChart = createChart(arrayOfCategoryDataset);
        return new ChartPanel(localJFreeChart);
    }

    public static void main(String[] paramArrayOfString)
    {
        Repository_ContributorsChart localBarChart = new Repository_ContributorsChart("JFreeChart");
        localBarChart.pack();
        RefineryUtilities.centerFrameOnScreen(localBarChart);
        localBarChart.setVisible(true);
    }

}

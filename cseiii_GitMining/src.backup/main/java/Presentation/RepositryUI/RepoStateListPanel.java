package Presentation.RepositryUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Presentation.Charts.ForksAndStarsScatterPlot;
import Presentation.Charts.Repo_CommitChart;
import Presentation.Charts.Repo_ForkChart;
import Presentation.Charts.Repo_ForksNumberChart;
import Presentation.Charts.Repo_OwnerTypeChart;
import Presentation.Charts.Repo_SizeChart;
import Presentation.Charts.Repo_StarsChart;
import Presentation.Charts.Repo_SubscribersChart;
import Presentation.Charts.Repo_TotalChart;
import Presentation.Charts.Repository_CollaboratorsChart;
import Presentation.Charts.RepoCreateTimeChart;
import Presentation.Charts.RepoLanguageChart;
import Presentation.Charts.Repository_ContributorsChart;
import Presentation.Charts.ScatterPlot;
import VO.RepoListLineItem;

public class RepoStateListPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public RepoStateListPanel() {
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		this.setLayout(new BoxLayout(this,1));
		this.add(RepoLanguageChart.createDemoPanel());
		this.add(RepoCreateTimeChart.createDemoPanel());
		this.add(Repository_CollaboratorsChart.createDemoPanel());
		this.add(Repository_ContributorsChart.createDemoPanel());
		this.add(Repo_ForkChart.createDemoPanel());
		this.add(Repo_ForksNumberChart.createDemoPanel());
		this.add(Repo_OwnerTypeChart.createDemoPanel());
		this.add(Repo_SizeChart.createDemoPanel());
		this.add(Repo_StarsChart.createDemoPanel());
		this.add(Repo_SubscribersChart.createDemoPanel());
		this.add(ForksAndStarsScatterPlot.createDemoPanel());
		this.add(Repo_CommitChart.createDemoPanel());
		this.add(Repo_TotalChart.createDemoPanel());
		this.add(ScatterPlot.createStarsPanel());
		this.add(ScatterPlot.createForkssPanel());
		this.add(ScatterPlot.createIssuesPanel());
		this.setPreferredSize(new Dimension(625,7000));

	}

}

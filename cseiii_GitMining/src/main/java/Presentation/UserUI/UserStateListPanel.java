package Presentation.UserUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import Presentation.Charts.UserCityChart;
import Presentation.Charts.UserCompanyChart;
import Presentation.Charts.UserFollowersChart;
import Presentation.Charts.UserFollowingChart;
import Presentation.Charts.UserCountryChart;
import Presentation.Charts.UserPublicGistsChart;
import Presentation.Charts.UserPublicReposChart;
import Presentation.Charts.UserTimeChart;
import Presentation.Charts.UserTypeChart;
import Presentation.Charts.User_OwnsReposChart;
import Presentation.Charts.User_RelatedReposChart;

public class UserStateListPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public UserStateListPanel() {
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		this.setLayout(new BoxLayout(this,1));
		this.add(UserTypeChart.createDemoPanel());
		this.add(UserTimeChart.createDemoPanel());
		this.add(User_OwnsReposChart.createDemoPanel());
		this.add(User_RelatedReposChart.createDemoPanel());
		this.add(UserCityChart.createDemoPanel());
		this.add(UserCompanyChart.createDemoPanel());
		this.add(UserCountryChart.createDemoPanel());
		this.add(UserPublicReposChart.createDemoPanel());
		this.add(UserPublicGistsChart.createDemoPanel());
		this.add(UserFollowersChart.createDemoPanel());
		this.add(UserFollowingChart.createDemoPanel());
		this.setPreferredSize(new Dimension(625,6000));

	}

}

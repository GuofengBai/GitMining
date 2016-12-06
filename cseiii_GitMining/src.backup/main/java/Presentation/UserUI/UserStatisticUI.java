package Presentation.UserUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Presentation.RepositryUI.RepoStateListPanel;

public class UserStatisticUI extends JPanel {

	/**
	 * Create the panel.
	 */
	public UserStatisticUI() {
setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1125, 560);
		add(scrollPane);
		JPanel p = new UserStateListPanel();
		scrollPane.setViewportView(p);

	}

}

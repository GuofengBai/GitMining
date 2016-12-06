package Presentation.RepositryUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RepositoryStatisticUI extends JPanel{
	public RepositoryStatisticUI() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1125, 560);
		add(scrollPane);
		JPanel p = new RepoStateListPanel();
		scrollPane.setViewportView(p);
	}
}

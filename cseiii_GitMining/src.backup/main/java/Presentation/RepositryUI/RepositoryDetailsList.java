package Presentation.RepositryUI;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import VO.RepositryVO;

public class RepositoryDetailsList extends JPanel {
	private JScrollPane scrollPane;
	/**
	 * Create the panel.
	 */
	public RepositoryDetailsList(RepositoryDetails view) {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 650, 600);
		add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(1135,560));
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,1));
		panel.setPreferredSize(new Dimension(1115,2100));
		panel.add(view);
		scrollPane.setViewportView(panel);
	}

}

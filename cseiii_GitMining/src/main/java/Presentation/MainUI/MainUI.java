package Presentation.MainUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Presentation.RepositryUI.RepositryListUI;
import java.awt.Toolkit;
import java.awt.Dimension;

public class MainUI extends JFrame {

	public JPanel contentPane;
	
	public JPanel content;

	/**
	 * Create the frame.
	 */
	public MainUI() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(MainUI.class.getResource("/Presentation/MainUI/080.jpg")));
		setTitle("GitMining");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1165, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	
		contentPane.setLayout(new BorderLayout());
		
		NavigationPanel navigationPanel = new NavigationPanel();
		navigationPanel.setPreferredSize(new Dimension(800, 100));
		contentPane.add(navigationPanel, BorderLayout.NORTH);
		
		content=new RepositryListUI();
		content.setPreferredSize(new Dimension(800, 200));
		contentPane.add(content, BorderLayout.CENTER);
		setVisible(true);
	}

}

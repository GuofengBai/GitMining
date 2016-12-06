package Presentation.RepositryUI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.UIManager;

import cseiii_GitMining.BLFactory;
import BusinessLogicService.RepositryBLService.RepositryBLService;
import Presentation.MainUI.ViewController;


public class RepositryPanel extends JPanel {
	private JTextArea Description;
	private JLabel Name;

	/**
	 * Create the panel.
	 */
	public RepositryPanel(String name,String description,String date,String forks,String stars,String language,String keywords) {
		setBackground(SystemColor.window);
		setLayout(null);
		
		Name = new JLabel(name);
		Name.setFont(new Font("Algerian", Font.PLAIN, 25));
		Name.setBounds(10, 10, 430, 39);
		add(Name);
		
		JLabel Date = new JLabel(date);
		Date.setBounds(10, 113, 220, 15);
		add(Date);
		
		Description = new JTextArea();
		Description.setBackground(SystemColor.textHighlightText);
		Description.setEditable(false);
		Description.setLineWrap(true);
		Description.setWrapStyleWord(true);
		Description.setBounds(353, 44, 392, 59);
		add(Description);
		Description.setColumns(10);
		Description.setText(description);
		
		JLabel lblNewLabel = new JLabel("forks");
		lblNewLabel.setFont(new Font("Blackadder ITC", Font.PLAIN, 30));
		lblNewLabel.setBounds(830, 8, 67, 39);
		add(lblNewLabel);
		
		JLabel lblStars = new JLabel("stars");
		lblStars.setFont(new Font("Blackadder ITC", Font.PLAIN, 30));
		lblStars.setBounds(958, 8, 67, 38);
		add(lblStars);
		
		JLabel Forks = new JLabel(forks);
		Forks.setFont(new Font("Serif", Font.PLAIN, 30));
		Forks.setBounds(830, 60, 80, 43);
		add(Forks);
		
		JLabel Stars = new JLabel(stars);
		Stars.setFont(new Font("Serif", Font.PLAIN, 30));
		Stars.setBounds(958, 60, 80, 43);
		add(Stars);
		
		JLabel Language = new JLabel(language);
		Language.setBounds(10, 79, 153, 15);
		add(Language);
		
		JLabel lblNewLabel_1 = new JLabel("=====================================================================================================================================================================================");
		lblNewLabel_1.setBounds(10, 127, 1113, 19);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(keywords);
		lblNewLabel_2.setBounds(10, 48, 332, 15);
		add(lblNewLabel_2);
		
		Name.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				Name.setFont(new Font("Algerian", Font.PLAIN, 25));
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				Name.setFont(new Font("Algerian", Font.PLAIN, 30));
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				RepositryBLService bl = BLFactory.getRepositryBL();
				RepositoryDetails panel = new RepositoryDetails(bl.getRepositry(Name.getText()));
				RepositoryDetailsList listpanel=new RepositoryDetailsList(panel);
				ViewController.jumpToAnotherView(listpanel);
			}
		});

	}
}

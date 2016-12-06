package Presentation.MainUI;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Label;

import javax.swing.JLabel;

import Presentation.RepositryUI.RepositoryStatisticUI;
import Presentation.RepositryUI.RepositryListUI;
import Presentation.UserUI.UserListUI;
import Presentation.UserUI.UserStatisticUI;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NavigationPanel extends JPanel {
	JLabel lblRepository;
	JLabel lblUser;
	JLabel lblReposStatistic;
	JLabel lblUserStatistic;

	/**
	 * Create the panel.
	 */
	public NavigationPanel() {
		setLayout(null);
		
		lblRepository = new JLabel("Repository");
		lblRepository.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblRepository.setBounds(43, 10, 209, 48);
		add(lblRepository);
		this.setPreferredSize(new Dimension(892, 100));
		lblRepository.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				RepositryListUI ui=new RepositryListUI();
				ViewController.jumpToAnotherView(ui);
				
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				lblRepository.setFont(new Font("Segoe UI Black", Font.PLAIN, 35));
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				lblRepository.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		lblUser = new JLabel("User");
		lblUser.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblUser.setBounds(462, 10, 104, 48);
		add(lblUser);
		lblUser.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				UserListUI ui=new UserListUI();
				ViewController.jumpToAnotherView(ui);
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblUser.setFont(new Font("Segoe UI Black", Font.PLAIN, 35));
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lblUser.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JLabel lblAbout = new JLabel("About");
		lblAbout.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblAbout.setBounds(804, 20, 118, 29);
		add(lblAbout);
		
		lblReposStatistic = new JLabel("Repos statistic");
		lblReposStatistic.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		lblReposStatistic.setBounds(43, 54, 184, 48);
		add(lblReposStatistic);
		lblReposStatistic.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				RepositoryStatisticUI ui=new RepositoryStatisticUI();
				ViewController.jumpToAnotherView(ui);
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblReposStatistic.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lblReposStatistic.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		lblUserStatistic = new JLabel("User statistic");
		lblUserStatistic.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
		lblUserStatistic.setBounds(442, 54, 184, 48);
		add(lblUserStatistic);
		lblUserStatistic.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				UserStatisticUI ui=new UserStatisticUI();
				ViewController.jumpToAnotherView(ui);
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblUserStatistic.setFont(new Font("Segoe UI Black", Font.PLAIN, 24));
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lblUserStatistic.setFont(new Font("Segoe UI Black", Font.PLAIN, 22));
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

	}
}

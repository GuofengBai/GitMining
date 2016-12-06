package Presentation.UserUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import BusinessLogicService.RepositryBLService.RepositryBLService;
import BusinessLogicService.UserBLService.UserBLService;
import Presentation.MainUI.ViewController;
import Presentation.RepositryUI.RepositoryDetails;
import cseiii_GitMining.BLFactory;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserPanel extends JPanel {
	private JLabel Name;

	/**
	 * Create the panel.
	 */
	public UserPanel(String name,String id,String type) {
		setBackground(SystemColor.window);
		setLayout(null);
		
		Name = new JLabel(name);
		Name.setFont(new Font("Algerian", Font.PLAIN, 25));
		Name.setBounds(10, 10, 430, 39);
		add(Name);
		
		JLabel lblNewLabel = new JLabel("User id:");
		lblNewLabel.setFont(new Font("Blackadder ITC", Font.PLAIN, 30));
		lblNewLabel.setBounds(20, 59, 139, 39);
		add(lblNewLabel);
		
		JLabel lblStars = new JLabel("User Type:");
		lblStars.setFont(new Font("Blackadder ITC", Font.PLAIN, 30));
		lblStars.setBounds(448, 59, 176, 38);
		add(lblStars);
		
		
		JLabel lblNewLabel_1 = new JLabel("=====================================================================================================================================================================================");
		lblNewLabel_1.setBounds(10, 108, 1102, 19);
		add(lblNewLabel_1);
		
		JLabel Id = new JLabel(id);
		Id.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 20));
		Id.setBounds(136, 67, 211, 29);
		add(Id);
		
		JLabel Type = new JLabel(type);
		Type.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 20));
		Type.setBounds(608, 67, 139, 29);
		add(Type);
		
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
				UserBLService bl = BLFactory.getUserBL();
				UserDetails panel = new UserDetails(bl.getUser(Name.getText()));
				UserDetailsList listpanel =new UserDetailsList(panel);
				ViewController.jumpToAnotherView(listpanel);
			}
		});

	}

}

package Presentation.UserUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import VO.UserVO;

import javax.swing.JButton;

public class UserUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable repoTable;

	/**
	 * Create the panel.
	 */
	public UserUI(UserVO vo) {
		setLayout(null);
		
		JLabel lblName = new JLabel("ID");
		lblName.setBounds(22, 49, 54, 15);
		add(lblName);
		
		JLabel lblBlog = new JLabel("Bio");
		lblBlog.setBounds(22, 250, 54, 15);
		add(lblBlog);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setBounds(22, 155, 54, 15);
		add(lblCompany);
		
		JLabel nameLabel = new JLabel(vo.id);
		nameLabel.setBounds(86, 49, 93, 15);
		add(nameLabel);
		
		JLabel bioLabel = new JLabel(vo.bio);
		bioLabel.setBounds(86, 250, 128, 15);
		add(bioLabel);
		
		JLabel companyLabel = new JLabel(vo.company);
		companyLabel.setBounds(86, 155, 128, 15);
		add(companyLabel);
		
		repoTable = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		Vector <String> column=new Vector <String>();
		column.add("repos");
		
		Vector <String> list=new Vector <String>();
		for(Object temp:vo.ownsRepos){
			String strTemp=(String)temp;
			list.add(strTemp);
		}
		
		repoTable.setModel(new DefaultTableModel(column,list));
		repoTable.setBounds(204, 49, 114, 219);
		
		
		JScrollPane scrollPane = new JScrollPane(repoTable);
		scrollPane.setBounds(237, 36, 329, 359);
		add(scrollPane);
		
		JLabel lblLogin = new JLabel("Name");
		lblLogin.setBounds(22, 74, 66, 15);
		add(lblLogin);
		
		JLabel loginLabel = new JLabel(vo.name);
		loginLabel.setBounds(86, 74, 93, 15);
		add(loginLabel);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(22, 99, 54, 15);
		add(lblType);
		
		JLabel label = new JLabel("");
		label.setBounds(86, 99, 83, 15);
		add(label);
		
		JLabel typeLabel = new JLabel(vo.type);
		typeLabel.setBounds(109, 99, 54, 15);
		add(typeLabel);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(22, 180, 54, 15);
		add(lblLocation);
		
		JLabel locationLabel = new JLabel(vo.location);
		locationLabel.setBounds(86, 180, 128, 15);
		add(locationLabel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(22, 225, 54, 15);
		add(lblEmail);
		
		JLabel emailLabel = new JLabel(vo.email);
		emailLabel.setBounds(86, 225, 111, 15);
		add(emailLabel);
		
		JLabel lblFollowers = new JLabel("Followers");
		lblFollowers.setBounds(22, 305, 54, 15);
		add(lblFollowers);
		
		JLabel followerLabel = new JLabel(String.valueOf(vo.followers));
		followerLabel.setBounds(109, 305, 54, 15);
		add(followerLabel);
		
		JLabel lblFollowing = new JLabel("Following");
		lblFollowing.setBounds(22, 330, 54, 15);
		add(lblFollowing);
		
		JLabel followingLabel = new JLabel(String.valueOf(vo.following));
		followingLabel.setBounds(109, 330, 54, 15);
		add(followingLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(480, 405, 93, 23);
		add(btnBack);
		btnBack.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});

	}
}

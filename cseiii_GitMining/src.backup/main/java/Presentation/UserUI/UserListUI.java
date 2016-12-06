package Presentation.UserUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import BusinessLogicService.RepositryBLService.RepositryBLService;
import BusinessLogicService.UserBLService.UserBLService;
import Presentation.RepositryUI.RepositryListPanel;
import VO.RepoSortType;
import VO.UserSortType;
import cseiii_GitMining.BLFactory;

public class UserListUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField keyWord;
	private JTextField page;
	private JScrollPane scrollPane;
	private JComboBox<String> sortType;

	/**
	 * Create the panel.
	 */
	public UserListUI() {
		setBackground(SystemColor.window);
		setLayout(null);
		
		JLabel lblKeyWord = new JLabel("Key word");
		lblKeyWord.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		lblKeyWord.setBounds(262, 10, 81, 36);
		add(lblKeyWord);
		
		JLabel lblNewLabel = new JLabel("Sort by");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		lblNewLabel.setBounds(10, 10, 81, 36);
		add(lblNewLabel);
		
		sortType = new JComboBox();
		sortType.setBounds(95, 20, 138, 21);
		add(sortType);
		sortType.addItem("public_repos");
		sortType.addItem("public_gists");
		sortType.addItem("followers");
		sortType.addItem("following");
		sortType.addItem("created_at");
		sortType.addItem("updated_at");
		
		keyWord = new JTextField();
		keyWord.setBounds(347, 20, 138, 21);
		add(keyWord);
		keyWord.setColumns(10);
		
		JButton Search = new JButton("Search");
		Search.setFont(new Font("Broadway", Font.PLAIN, 15));
		Search.setBounds(526, 10, 93, 41);
		add(Search);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 66, 1125, 440);
		add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(300,5000));
		
		page = new JTextField("1");
		page.setBounds(693, 520, 48, 29);
		add(page);
		page.setColumns(10);
		
		final JLabel totalpage = new JLabel("/6736");
		totalpage.setFont(new Font("宋体", Font.PLAIN, 24));
		totalpage.setBounds(751, 519, 81, 30);
		add(totalpage);
		
		final JLabel lblNewLabel_1 = new JLabel("Turn to page>>");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(539, 520, 151, 29);
		add(lblNewLabel_1);
		lblNewLabel_1.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 21));
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				UserBLService bl = BLFactory.getUserBL();
				bl.setPage(Integer.parseInt(page.getText()));
				JPanel list = new UserListPanel(bl.getUserList(Integer.parseInt(page.getText())));
				scrollPane.setViewportView(list);
			}
		});
		JButton NextPage = new JButton("Next Page");
		NextPage.setFont(new Font("Broadway", Font.PLAIN, 15));
		NextPage.setBounds(850, 520, 138, 29);
		add(NextPage);
		
		JButton PrevPage = new JButton("Prev Page");
		PrevPage.setFont(new Font("Broadway", Font.PLAIN, 15));
		PrevPage.setBounds(380, 520, 138, 29);
		add(PrevPage);
		
		Search.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserBLService bl = BLFactory.getUserBL();
				for(UserSortType type:UserSortType.values()){
					if(sortType.getSelectedItem().equals(type.toString())){
						bl.setSortType(type);
						break;
					}
				}

				if(!keyWord.getText().equals("")&&keyWord.getText()!=null){
					bl.setKey(keyWord.getText());
					keyWord.setText("");
				}
				totalpage.setText("/"+bl.getTotalPage());
				page.setText("1");
				JPanel list = new UserListPanel(bl.getNextPage());
				scrollPane.setViewportView(list);
			}
		});
		
		PrevPage.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(page.getText().equals("1")){
					return;
				}
				UserBLService bl = BLFactory.getUserBL();
				JPanel list = new UserListPanel(bl.getPrePage());
				scrollPane.setViewportView(list);
				String str = page.getText();
				page.setText(Integer.parseInt(str)-1+"");
			}
		});
		
		NextPage.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserBLService bl = BLFactory.getUserBL();
				JPanel list = new UserListPanel(bl.getNextPage());
				scrollPane.setViewportView(list);
				String str = page.getText();
				page.setText(Integer.parseInt(str)+1+"");
			}
		});

		UserBLService bl = BLFactory.getUserBL();
		bl.setPage(0);
		JPanel list = new UserListPanel(bl.getNextPage());
		scrollPane.setViewportView(list);

	}
}

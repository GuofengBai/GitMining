package Presentation.RepositryUI;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.JButton;

import VO.RepoSortType;
import cseiii_GitMining.BLFactory;
import BusinessLogic.RepositryBL.RepositryBL;
import BusinessLogicService.RepositryBLService.RepositryBLService;

public class RepositryListUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField keyWord;
	private JTextField page;
	private JComboBox<String> sortType;
	private JScrollPane scrollPane;
	

	/**
	 * Create the panel.
	 */
	public RepositryListUI() {

		setBackground(SystemColor.window);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sort by");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		lblNewLabel.setBounds(10, 10, 81, 36);
		add(lblNewLabel);
		
		sortType = new JComboBox();
		sortType.setBounds(95, 20, 138, 21);
		add(sortType);
		sortType.addItem("forks");
		sortType.addItem("stars");
		sortType.addItem("commits");
		sortType.addItem("issues");
		sortType.addItem("pulls");
		sortType.addItem("watchers");
		sortType.addItem("subscribers");
		sortType.addItem("size");
		sortType.addItem("created_at");
		sortType.addItem("updated_at");
		sortType.addItem("pushed_at");
		
		JLabel lblKeyWord = new JLabel("Key word");
		lblKeyWord.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		lblKeyWord.setBounds(262, 10, 81, 36);
		add(lblKeyWord);
		
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
		
		final JLabel totalpage = new JLabel("/322");
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
				RepositryBLService bl = BLFactory.getRepositryBL();
				bl.setPage(Integer.parseInt(page.getText()));
				JPanel list = new RepositryListPanel(bl.getRepositryList(Integer.parseInt(page.getText())));
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
				RepositryBLService bl = BLFactory.getRepositryBL();
				for(RepoSortType type:RepoSortType.values()){
					if(sortType.getSelectedItem().equals(type.toString())){
						bl.setSortType(type);
						break;
					}
				}
				if(!keyWord.getText().equals("")){
					bl.setKey(keyWord.getText());
					keyWord.setText("");
				}
				totalpage.setText("/"+bl.getTotalPage());
				page.setText("1");
				JPanel list = new RepositryListPanel(bl.getNextPage());
				scrollPane.setViewportView(list);
			}
		});
		
		PrevPage.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(page.getText().equals("1")){
					return;
				}
				RepositryBLService bl = BLFactory.getRepositryBL();
				JPanel list = new RepositryListPanel(bl.getPrePage());
				scrollPane.setViewportView(list);
				String str = page.getText();
				page.setText(Integer.parseInt(str)-1+"");
			}
		});
		
		NextPage.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				RepositryBLService bl = BLFactory.getRepositryBL();
				JPanel list = new RepositryListPanel(bl.getNextPage());
				scrollPane.setViewportView(list);
				String str = page.getText();
				page.setText(Integer.parseInt(str)+1+"");
			}
		});

		RepositryBLService bl = BLFactory.getRepositryBL();
		bl.setSortType(RepoSortType.FORKS);
		JPanel list = new RepositryListPanel(bl.getNextPage());
		scrollPane.setViewportView(list);
		this.setPreferredSize(new Dimension(929, 750));
		
		
	}
}

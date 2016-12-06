package Presentation.UserUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import BusinessLogicService.RepositryBLService.RepositryBLService;
import BusinessLogicService.UserBLService.UserBLService;
import Presentation.Charts.User_WorkChart;
import Presentation.MainUI.ViewController;
import Presentation.RepositryUI.RepositoryDetails;
import Presentation.RepositryUI.RepositoryDetailsList;

import VO.RepositryVO;
import VO.UserVO;

import javax.swing.JTextArea;

import cseiii_GitMining.BLFactory;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;

public class UserDetails extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel model;
	private JTable relatable;
	private DefaultTableModel relamodel;
	private JTable coworkertable;
	private DefaultTableModel coworkermodel;	
	private JTable relaCommitTable;
	private DefaultTableModel relaCommitmodel;
	private JTable committable;
	private DefaultTableModel commitmodel;
	private JTable messagetable;
	private DefaultTableModel messagemodel;
	/**
	 * Create the panel.
	 */
	public UserDetails(UserVO vo) {
		setBackground(SystemColor.window);
		setForeground(SystemColor.window);
		setLayout(null);
		
		initLabel(vo);
		initTable(vo);
		
		

		JPanel workchart=User_WorkChart.createDemoPanel(vo.login);
		workchart.setBounds(100, 1420, 800, 450);
		this.add(workchart);
	
		JButton btnBack = new JButton("back");
		btnBack.setBounds(909, 30, 93, 23);
		add(btnBack);
		btnBack.setFont(new Font("Broadway", Font.PLAIN, 15));
		
		JLabel lblrelatedUsersAnd = new JLabel("===========================================Related Users and Repositories===========================================");
		lblrelatedUsersAnd.setFont(new Font("Arial", Font.PLAIN, 18));
		lblrelatedUsersAnd.setBounds(0, 514, 1170, 18);
		add(lblrelatedUsersAnd);
		
		
		btnBack.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ViewController.back();
			}
			
		});
		

		

	}
	
	private void initTable(UserVO vo){
		Vector <String> column=new Vector <String>();
		column.add("OwnsRepos");
		
		Vector <String> list=new Vector <String>();
		model=new DefaultTableModel(list,column);
		if(vo.ownsRepos.size()>0){
		for(int i=0;i<vo.ownsRepos.size();i++){
			Rows strTemp=new Rows((String)vo.ownsRepos.get(i));
			
			model.addRow(strTemp);
		}}
		else{
			Rows temp=new Rows(" ");
			model.addRow(temp);
		}

		
		
//ownsrepo	
		table = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
				
		table.setModel(model);
		table.setBounds(464, 191, 191, 156);
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Arial", Font.PLAIN, 18));
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		table.setFillsViewportHeight(true);
		//table.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		table.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==MouseEvent.BUTTON1 && e.getClickCount() == 2){
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
					String repoName=(String) model.getValueAt(row, 0);

					RepositryBLService bl=BLFactory.getRepositryBL();
					RepositryVO repo=bl.getRepositry(repoName);
							
					RepositoryDetails newpanel=new RepositoryDetails(repo);
					RepositoryDetailsList pp=new RepositoryDetailsList(newpanel);
					ViewController.jumpToAnotherView(pp);
							
				}
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
						
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
						
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
						
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
						
			}
					
		});		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(463, 207, 480, 285);
		add(scrollPane);
		
//relatable	
		relatable = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		Vector <String> relacolumn=new Vector <String>();
		relacolumn.add("RelatedRepos");
		
		Vector <String> relalist=new Vector <String>();
		relamodel=new DefaultTableModel(relalist,relacolumn);
		if(vo.relatedRepos.size()>0){
		for(int i=0;i<vo.relatedRepos.size();i++){
			Rows strTemp=new Rows((String)vo.relatedRepos.get(i));
			
			relamodel.addRow(strTemp);
		}}
		else{
			Rows temp=new Rows(" ");
			relamodel.addRow(temp);
		}
		
		relatable.setModel(relamodel);
		relatable.setBounds(220, 49, 114, 219);
		relatable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		relatable.setFont(new Font("Arial", Font.PLAIN, 15));
		relatable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		relatable.setFillsViewportHeight(true);
		//relatable.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		relatable.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==MouseEvent.BUTTON1 && e.getClickCount() == 2){
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
					String repoName=(String) relamodel.getValueAt(row, 0);
					RepositryBLService bl=BLFactory.getRepositryBL();
					RepositryVO repo=bl.getRepositry(repoName);
					
					RepositoryDetails newpanel=new RepositoryDetails(repo);
					RepositoryDetailsList pp=new RepositoryDetailsList(newpanel);
					ViewController.jumpToAnotherView(pp);
					
				}
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JScrollPane relascrollPane = new JScrollPane(relatable);
		relascrollPane.setBounds(40, 578, 429, 162);
		add(relascrollPane);
		relascrollPane.setViewportView(relatable);
		
		
		coworkertable = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		coworkertable.setBounds(706, 219, 227, 234);
		Object[] coworkercolumn={"Coworker Name","Repos"}; 
		Object[][] coworkerlist=BLFactory.getUserBL().getCoworkers(vo.login);
		coworkermodel=new DefaultTableModel(coworkerlist,coworkercolumn);
		coworkertable.setModel(coworkermodel);
		coworkertable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		coworkertable.setFont(new Font("Arial", Font.PLAIN, 15));
		coworkertable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		coworkertable.setFillsViewportHeight(true);
		//coworkertable.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		coworkertable.addMouseListener(new MouseListener(){

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==MouseEvent.BUTTON1 && e.getClickCount() == 2){
					int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint());
					String username = (String) coworkermodel.getValueAt(row, 0);
					UserBLService bl = BLFactory.getUserBL();
					UserVO user = bl.getUser(username);
					UserDetails newpanel=new UserDetails(user);
					UserDetailsList listpanel=new UserDetailsList(newpanel);
					ViewController.jumpToAnotherView(listpanel);
					
				}
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane(coworkertable);
		scrollPane_1.setBounds(620, 578, 364, 162);
		add(scrollPane_1);
		
		
		
		
		relaCommitTable = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		relaCommitTable.setBounds(104, 563, 469, 119);
		Object[] relaCommitcolumn={"repo name","commit number","lines","avg-lines","additions","avg_add","deletions","avg-del","avg-file"}; 
		Object[][] relaCommitlist=BLFactory.getStatisticBL().getCommitsStateOfRelatedRepos(vo.login);
		relaCommitmodel=new DefaultTableModel(relaCommitlist,relaCommitcolumn);
		relaCommitTable.setModel(relaCommitmodel);
		relaCommitTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		relaCommitTable.setFont(new Font("Arial", Font.PLAIN, 18));
		relaCommitTable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		//relaCommitTable.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		relaCommitTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane_2 = new JScrollPane(relaCommitTable);
		scrollPane_2.setBounds(40, 880, 1049, 150);
		add(scrollPane_2);
		
		committable = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		committable.setBounds(104, 563, 469, 119);
		Object[] commitcolumn={"commit number","lines","avg-lines","additions","avg_add","deletions","avg-del","avg-file"}; 
		Object[][] commitlist=new Object[1][8];
		commitlist[0]=BLFactory.getStatisticBL().getCommitsStateByUser(vo.login);
		commitmodel=new DefaultTableModel(commitlist,commitcolumn);
		committable.setModel(commitmodel);
		committable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		committable.setFont(new Font("Arial", Font.PLAIN, 18));
		committable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		//committable.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		committable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(40, 790, 1049, 51);
		scrollPane_3.setViewportView(committable);
		add(scrollPane_3);
		
		messagetable = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		messagetable.setBounds(104, 563, 469, 119);
		Object[] messagecolumn={"date","repo","lines","additions","deletions","file","message"}; 
		Object[][] messagelist;
		messagelist=BLFactory.getStatisticBL().getCommitsMessageByUser(vo.login);
		messagemodel=new DefaultTableModel(messagelist,messagecolumn);
		messagetable.setModel(messagemodel);
		messagetable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		messagetable.setFont(new Font("Arial", Font.PLAIN, 18));
		messagetable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		messagetable.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		messagetable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(40, 1080, 1049, 320);
		scrollPane_4.setViewportView(messagetable);
		add(scrollPane_4);
	}
	
	private void initLabel(UserVO vo){
		JLabel lblName = new JLabel("ID  "+vo.id);
		lblName.setBounds(563, 77, 287, 25);
		lblName.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		add(lblName);
		
		JLabel lblLoginname = new JLabel("LoginName  "+vo.login);
		lblLoginname.setBounds(563, 119, 300, 25);
		lblLoginname.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		add(lblLoginname);
		
		JLabel lblBio = new JLabel("Bio  ");
		lblBio.setBounds(22, 250, 50, 25);
		lblBio.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		add(lblBio);
		JTextArea bioArea = new JTextArea();
		bioArea.setLineWrap(true);
		bioArea.setForeground(Color.BLACK);
		bioArea.setBounds(22, 278, 394, 160);
		add(bioArea);
		bioArea.setText(vo.bio);
		bioArea.setEditable(false);
		bioArea.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JLabel lblBlog = new JLabel("Blog  "+vo.blog);
		lblBlog.setBounds(22, 207, 547, 25);
		lblBlog.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		add(lblBlog);
		
		JLabel lblCompany = new JLabel("Company  "+vo.company);
		lblCompany.setBounds(22, 119, 339, 25);
		lblCompany.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		add(lblCompany);
		
		JLabel lblLogin = new JLabel("Name  "+vo.name,JLabel.CENTER);
		lblLogin.setForeground(SystemColor.textHighlight);
		lblLogin.setBounds(288, 0, 596, 33);
		lblLogin.setFont(new Font("Cooper Black", Font.ITALIC, 32));
		add(lblLogin);
		
		JLabel lblType = new JLabel("Type  "+vo.type);
		lblType.setBounds(22, 77, 278, 25);
		lblType.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		add(lblType);

		JLabel lblLocation = new JLabel("Location  "+vo.location);
		lblLocation.setBounds(563, 158, 287, 25);
		lblLocation.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		add(lblLocation);
		
		JLabel lblEmail = new JLabel("Email  "+vo.email);
		lblEmail.setBounds(22, 158, 501, 25);
		lblEmail.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		add(lblEmail);
		
		JLabel lblFollowers = new JLabel("Followers  "+String.valueOf(vo.followers));
		lblFollowers.setBounds(22, 448, 193, 25);
		lblFollowers.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		add(lblFollowers);
		
		JLabel lblFollowing = new JLabel("Following  "+String.valueOf(vo.following));
		lblFollowing.setBounds(220, 448, 159, 25);
		lblFollowing.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		add(lblFollowing);
		
		JLabel lblRelatedRepositories = new JLabel("Related Repositories");
		lblRelatedRepositories.setBounds(150, 545, 200, 25);
		lblRelatedRepositories.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		add(lblRelatedRepositories);
		
		JLabel lblCoworkers = new JLabel("Coworkers");
		lblCoworkers.setBounds(750, 545, 200, 25);
		lblCoworkers.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		add(lblCoworkers);
		
		JLabel lblCommitState = new JLabel("=======================================================Commit State========================================================");
		lblCommitState.setBounds(0, 755, 1205, 25);
		lblCommitState.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		add(lblCommitState);
		
		JLabel lblCommitState2 = new JLabel("Commit State of Related Repos");
		lblCommitState2.setBounds(420, 850, 300, 25);
		lblCommitState2.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		add(lblCommitState2);
		
		JLabel lblCommitMessage = new JLabel("Commit Message");
		lblCommitMessage.setBounds(480, 1040, 300, 25);
		lblCommitMessage.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		add(lblCommitMessage);
	}
}


class Rows extends Vector<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Rows(String name){
		this.add(name);
	}
}
class TableCellTextAreaRenderer extends JTextArea implements TableCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TableCellTextAreaRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        // 计算当下行的�?佳高�?
        int maxPreferredHeight = 0;
        for (int i = 0; i < table.getColumnCount(); i++) {
            setText("" + table.getValueAt(row, i));
            setSize(table.getColumnModel().getColumn(column).getWidth(), 0);
            maxPreferredHeight = Math.max(maxPreferredHeight, getPreferredSize().height);
        }

        if (table.getRowHeight(row) != maxPreferredHeight)  // 少了这行则处理器瞎忙
            table.setRowHeight(row, maxPreferredHeight);

        setText(value == null ? "" : value.toString());
        return this;
    }
}

package Presentation.RepositryUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import BusinessLogic.RepositryBL.RepositryBL;
import BusinessLogicService.RepositryBLService.RepositryBLService;
import BusinessLogicService.UserBLService.UserBLService;
import Presentation.MainUI.ViewController;
import Presentation.UserUI.UserDetails;
import Presentation.UserUI.UserDetailsList;
import VO.RepositryVO;
import VO.UserVO;
import cseiii_GitMining.BLFactory;
import Presentation.Charts.Repo_DevelopChart;
import Presentation.Charts.Repository_CollaboratorsChart;
import Presentation.Charts.SpiderWebChart;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import java.awt.SystemColor;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class RepositoryDetails extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField URLfield;
	private JTextArea DescriptionField;
	private JTable table;
	private JTable table2;
	private JTable contributorsStateTable;
	private JTable commitTable;
	private JTable repoTable;
	private JTable table3;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private DefaultTableModel contributorsStateModel;
	private DefaultTableModel commitModel;
	private DefaultTableModel repoModel;
	private DefaultTableModel model3;
	private RepositryBLService rbl;
	private List<String> keywords;
	
	private void initLabel(RepositryVO data) {
		String Reponame =  data.reponame;
		JLabel reponame = new JLabel(Reponame,JLabel.CENTER);
		reponame.setForeground(SystemColor.textHighlight);
		reponame.setFont(new Font("Cooper Black", Font.ITALIC, 32));
		reponame.setBounds(288, 0, 596, 33);
		add(reponame);

		JLabel Url = new JLabel("URL");
		Url.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		Url.setBounds(55, 95, 72, 18);
		add(Url);

		JLabel Forks = new JLabel("Forks               " + String.valueOf(data.forks));
		Forks.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		Forks.setBounds(447, 285, 204, 18);
		add(Forks);

		JLabel Stars = new JLabel("Stars               "+ String.valueOf(data.stars));
		Stars.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		Stars.setBounds(149, 285, 218, 18);
		add(Stars);

		JLabel Description = new JLabel("description");
		Description.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		Description.setBounds(33, 139, 105, 26);
		add(Description);
		JLabel lblNewLabel = new JLabel("==================================================related===============================================");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		lblNewLabel.setBounds(0, 423, 1170, 18);
		add(lblNewLabel);
		
		JLabel lblcommit = new JLabel("==================================================commit===============================================");
		lblcommit.setFont(new Font("Arial", Font.PLAIN, 18));
		lblcommit.setBounds(0, 687, 1170, 18);
		add(lblcommit);

		JLabel Ownername = new JLabel("ownername    " + data.ownername);
		Ownername.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		Ownername.setBounds(55, 44, 303, 26);
		add(Ownername);

		JLabel Watchers = new JLabel("Watchers        " + String.valueOf(data.watchers));
		Watchers.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		Watchers.setBounds(447, 316, 204, 18);
		add(Watchers);

		JLabel Contributors = new JLabel("Contributors    " + String.valueOf(data.contributors.size()));
		Contributors.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		Contributors.setBounds(149, 316, 204, 18);
		add(Contributors);

		JLabel Language = new JLabel("Language        " + data.language);
		Language.setFont(new Font("Comic Sans MS", Font.ITALIC, 18));
		Language.setBounds(149, 347, 293, 24);
		add(Language);
		
		JLabel lblNewLabel_1 = new JLabel("Contributors");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(871, 442, 132, 33);
		add(lblNewLabel_1);
		
		JLabel lblRelatedRepositories = new JLabel("Related Repositories by Contributor");
		lblRelatedRepositories.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblRelatedRepositories.setBounds(55, 442, 303, 33);
		add(lblRelatedRepositories);
		
		JLabel lblCommitsDataIn = new JLabel("Commits Data in this Repository");
		lblCommitsDataIn.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblCommitsDataIn.setBounds(447, 704, 323, 33);
		add(lblCommitsDataIn);
		
		JLabel lblCommitsDataBy = new JLabel("Commits Data by Every Contributor");
		lblCommitsDataBy.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblCommitsDataBy.setBounds(435, 835, 323, 33);
		add(lblCommitsDataBy);
		
		JLabel lblECommitsDataBy = new JLabel("Every Commit Detail Data");
		lblECommitsDataBy.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblECommitsDataBy.setBounds(455, 1154, 323, 33);
		add(lblECommitsDataBy);
		
		JLabel lblNewLabel_2 = new JLabel("Key Words:");
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.ITALIC, 22));
		lblNewLabel_2.setBounds(43, 388, 121, 33);
		add(lblNewLabel_2);
		
		int length=keywords.size();
		JLabel[] keywordslabel=new JLabel[length];
		int x=200;
		for(int i=0;i<length;i++){
			keywordslabel[i]=new JLabel(keywords.get(i),JLabel.CENTER);
			keywordslabel[i].setForeground(SystemColor.textHighlight);
			keywordslabel[i].setFont(new Font("Comic Sans MS",Font.ITALIC,22));
			keywordslabel[i].setBounds(x,388,140,33);
			x=x+180;
			add(keywordslabel[i]);
		}
		
		JLabel lblRelatedRepositoriesBy = new JLabel("Related Repositories by Key Words");
		lblRelatedRepositoriesBy.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblRelatedRepositoriesBy.setBounds(415, 442, 303, 33);
		add(lblRelatedRepositoriesBy);


		
		//JPanel p=Repository_CollaboratorsChart.createDemoPanel();
		JPanel p = SpiderWebChart.createDemoPanel(data.contributors.size(),data.commits,data.pull_requests,data.issues,data.stars,data.forks);
		
		JPanel developChart=Repo_DevelopChart.createDemoPanel(data.reponame);
		/*NEED FIX HERE!�?
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		this.add(p);
		this.add(developChart);
		p.setBounds(675, 90, 390, 310);
		developChart.setBounds(10, 1550, 1080, 330);
	}

	private void initText(RepositryVO data) {
		URLfield = new JTextField();
		URLfield.setFont(new Font("Arial", Font.PLAIN, 18));
		URLfield.setBackground(SystemColor.text);
		URLfield.setForeground(SystemColor.window);
		URLfield.setEnabled(false);
		URLfield.setBounds(149, 92, 400, 24);
		URLfield.setText(data.html_url);
		add(URLfield);
		URLfield.setColumns(10);
	}

	@SuppressWarnings("unchecked")
	private void initModel(RepositryVO data) {
		Vector<String> columns = new Vector<String>();
		columns.add("contributor names");
		Vector<String> vdata = new Vector<String>();
		model = new DefaultTableModel(vdata, columns);

		for (int i = 0; i < data.contributors.size(); i++) {
			Rows row=new Rows((String)data.contributors.get(i));
			model.addRow(row);
		}
		
		List<String> redata=rbl.getReposWithSomeKeywords(keywords);
		Vector<String> columns6=new Vector<String>();
		columns6.add("Related Repositories");
		Vector<String> data6=new Vector<String>();
		model3=new DefaultTableModel(data6, columns6);
		for(int i=0;i<redata.size();i++){
			Rows row=new Rows((String)redata.get(i));
			model3.addRow(row);
		}
	
		Object[] columns2 = {"name","related contributors"};
		Object[][] relatedData;
		relatedData=BLFactory.getRepositryBL().getReposRelatedByContributor(data.reponame);
		model2 = new DefaultTableModel(relatedData, columns2);
	
		Object[][] contributorsStateData;
		contributorsStateData=BLFactory.getStatisticBL().getCommitsStateOfContributors(data.reponame);
		Object[] columns3={"contributor name","commits number","change lines","avg-change","add lines","avg-add","delete lines","avg-delete","avg-files","Contribution degree"};
		contributorsStateModel=new DefaultTableModel(contributorsStateData, columns3);
		
		
		Object[][] commitData;
		commitData=BLFactory.getStatisticBL().getCommitsMessageByRepos(data.reponame);
		Object[] columns4={"date","user","change lines","add lines","delete lines","change files","message"};
		commitModel=new DefaultTableModel(commitData, columns4);
		
		Object[] repodata=BLFactory.getStatisticBL().getCommitsStateByRepos(data.reponame);
		Object[] columns5={"commits","lines","avg-lines ","additions","avg-add","deletions","avg-del"};
		Object[][] temp=new Object[1][8];
		for(int i=0;i<8;i++){
			temp[0][i]=repodata[i];
		}
		repoModel=new DefaultTableModel(temp, columns5);
	}

	private void initTable(RepositryVO data) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(772, 480, 322, 192);
		add(scrollPane);
		table = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(table);
		table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		table.setFont(new Font("Arial", Font.PLAIN, 18));
		table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		table.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1 && arg0.getClickCount() == 2) {
					int row = ((JTable) arg0.getSource()).rowAtPoint(arg0.getPoint());
					String username = (String) model.getValueAt(row, 0);
					UserBLService bl = BLFactory.getUserBL();
					UserVO user = bl.getUser(username);
					UserDetails newpanel=new UserDetails(user);
					UserDetailsList pp=new UserDetailsList(newpanel);
					ViewController.jumpToAnotherView(pp);

				}

			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane6 = new JScrollPane();
		scrollPane6.setBounds(408, 480, 322, 192);
		add(scrollPane6);
		table3 = new JTable(model3) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane6.setViewportView(table3);
		table3.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		table3.setFont(new Font("Arial", Font.PLAIN, 18));
		table3.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table3.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		table3.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1 && arg0.getClickCount() == 2) {
					int row = ((JTable) arg0.getSource()).rowAtPoint(arg0.getPoint());
					String username = (String) model3.getValueAt(row, 0);
					RepositryBLService bl = BLFactory.getRepositryBL();
					RepositryVO re = bl.getRepositry(username);
					
					RepositoryDetails newpanel=new RepositoryDetails(re);
					RepositoryDetailsList pp=new RepositoryDetailsList(newpanel);
					ViewController.jumpToAnotherView(pp);

				}
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		table3.setFillsViewportHeight(true);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(45, 480, 322, 192);
		add(scrollPane2);
		table2 = new JTable(model2) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane2.setViewportView(table2);
		table2.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		table2.setFont(new Font("Arial", Font.PLAIN, 18));
		table2.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		table2.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1 && arg0.getClickCount() == 2) {
					int row = ((JTable) arg0.getSource()).rowAtPoint(arg0.getPoint());
					String username = (String) model2.getValueAt(row, 0);

					RepositryBLService bl = BLFactory.getRepositryBL();
					RepositryVO re = bl.getRepositry(username);
					
					RepositoryDetails newpanel=new RepositoryDetails(re);
					RepositoryDetailsList pp=new RepositoryDetailsList(newpanel);
					ViewController.jumpToAnotherView(pp);

				}

			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		table2.setFillsViewportHeight(true);
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(45, 870, 1049, 282);
		add(scrollPane3);
		contributorsStateTable = new JTable(contributorsStateModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane3.setViewportView(contributorsStateTable);
		contributorsStateTable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		contributorsStateTable.setFont(new Font("Arial", Font.PLAIN, 18));
		contributorsStateTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contributorsStateTable.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		TableColumn c = contributorsStateTable.getColumnModel().getColumn(9);
		c.setMinWidth(120);
		contributorsStateTable.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		contributorsStateTable.setFillsViewportHeight(true);
		
		JScrollPane scrollPane4 = new JScrollPane();
		scrollPane4.setBounds(45, 1190, 1049, 320);
		add(scrollPane4);
		commitTable = new JTable(commitModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane4.setViewportView(commitTable);
		commitTable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		commitTable.setFont(new Font("Arial", Font.PLAIN, 18));
		commitTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		commitTable.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		TableColumn c1 = commitTable.getColumnModel().getColumn(0);
		c1.setMinWidth(120);
		TableColumn c2 = commitTable.getColumnModel().getColumn(6);
		c2.setMinWidth(420);
		commitTable.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				

			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		commitTable.setFillsViewportHeight(true);

		JScrollPane scrollPane5 = new JScrollPane();
		scrollPane5.setBounds(45, 740, 1049, 82);
		add(scrollPane5);
		repoTable = new JTable(repoModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane5.setViewportView(repoTable);
		repoTable.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 18));
		repoTable.setFont(new Font("Arial", Font.PLAIN, 18));
		repoTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		repoTable.setDefaultRenderer(Object.class, new TableCellTextAreaRenderer());
		repoTable.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {
				

			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
		
		repoTable.setFillsViewportHeight(true);
	}

	private void initButton(RepositryVO data) {
		JButton Copy = new JButton("copy");
		Copy.setFont(new Font("Broadway", Font.PLAIN, 15));
		Copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					Clipboard clipboard = getToolkit().getSystemClipboard();
					StringSelection text1 = new StringSelection(URLfield.getText());
					clipboard.setContents(text1, null);
				}
			}
		});
		Copy.setBounds(572, 91, 89, 27);
		add(Copy);

		JButton Back = new JButton("back");
		Back.setFont(new Font("Broadway", Font.PLAIN, 15));
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ViewController.back();
				// 跳转到上�?个界�?

			}

		});
		Back.setBounds(972, 40, 89, 27);
		add(Back);
		
				DescriptionField = new JTextArea();
				DescriptionField.setBounds(149, 141, 400, 138);
				add(DescriptionField);
				DescriptionField.setBorder (BorderFactory.createLineBorder(Color.gray,1));
				DescriptionField.setForeground(Color.BLACK);
				DescriptionField.setFont(new Font("Arial", Font.PLAIN, 18));
				DescriptionField.setBackground(Color.WHITE);
				DescriptionField.setEditable(false);
				DescriptionField.setText(data.description);
				DescriptionField.setLineWrap(true);
				DescriptionField.setWrapStyleWord(true);
				DescriptionField.setColumns(10);
				
		
	
		

	}

	/**
	 * Create the panel.
	 */
	public RepositoryDetails(RepositryVO data) {
		rbl=BLFactory.getRepositryBL();
		keywords=rbl.getKeywords(data.reponame);
		setBackground(SystemColor.window);
		setLayout(null);
		initLabel(data);
		initText(data);
		initModel(data);
		initTable(data);
		initButton(data);
	}
}

class LinkLabel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;

	public LinkLabel(String text) {
		this.text = text;
		this.setText("contributors " + text);

		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setForeground(Color.BLUE);
			}

			public void mouseExited(MouseEvent e) {
				setForeground(Color.BLACK);
			}

			public void mouseClicked(MouseEvent e) {

			}
		});
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

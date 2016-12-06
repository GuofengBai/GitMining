package Presentation.RepositryUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class RepositryUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable contributorTable;
	private JTable collaboratorTable;

	/**
	 * Create the panel.
	 */
	public RepositryUI() {
		setLayout(null);
		
		JLabel lblName = new JLabel("name");
		lblName.setBounds(78, 44, 54, 15);
		add(lblName);
		
		JLabel lblDescription = new JLabel("description");
		lblDescription.setBounds(61, 99, 75, 15);
		add(lblDescription);
		
		JLabel lblDate = new JLabel("date:");
		lblDate.setBounds(393, 44, 54, 15);
		add(lblDate);
		
		JLabel lblStars = new JLabel("stars:");
		lblStars.setBounds(393, 83, 54, 15);
		add(lblStars);
		
		JLabel lblNewLabel = new JLabel("forks:");
		lblNewLabel.setBounds(393, 135, 54, 15);
		add(lblNewLabel);
		
		JLabel lblIssues = new JLabel("issues:");
		lblIssues.setBounds(393, 179, 54, 15);
		add(lblIssues);
		
		contributorTable = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		contributorTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"Contributors"
			}
		));
		contributorTable.setBounds(47, 223, 170, 192);
		
		collaboratorTable = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		collaboratorTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"Collaborators"
			}
		));
		collaboratorTable.setBounds(349, 223, 177, 192);
		
		JScrollPane scrollPane = new JScrollPane(contributorTable);
		scrollPane.setBounds(47, 223, 170, 192);
		add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane(collaboratorTable);
		scrollPane_1.setBounds(349, 223, 177, 192);
		add(scrollPane_1);
		
		JLabel nameLabel = new JLabel(" ");
		nameLabel.setBounds(123, 44, 54, 15);
		add(nameLabel);
		
		JLabel descripitionLabel = new JLabel("description here");
		descripitionLabel.setBounds(90, 135, 151, 15);
		add(descripitionLabel);
		
		JLabel dateLabel = new JLabel("date");
		dateLabel.setBounds(444, 44, 54, 15);
		add(dateLabel);
		
		JLabel starLabel = new JLabel("star");
		starLabel.setBounds(444, 83, 54, 15);
		add(starLabel);
		
		JLabel forkLabel = new JLabel("fork");
		forkLabel.setBounds(444, 135, 54, 15);
		add(forkLabel);
		
		JLabel issueLabel = new JLabel("issue");
		issueLabel.setBounds(444, 179, 54, 15);
		add(issueLabel);

	}
}

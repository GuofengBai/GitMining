package Presentation.RepositryUI;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import org.json.JSONArray;
import org.json.JSONObject;

import VO.RepoListLineItem;

import java.awt.Font;
import java.awt.Component;
import java.util.Vector;


public class RepositryListPanel extends JPanel {
	JPanel[] p = new JPanel[10];
	JSONObject object;

	/**
	 * Create the panel.
	 */
	public RepositryListPanel(Vector<RepoListLineItem> array) {
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		this.setLayout(new BoxLayout(this,1));
		RepoListLineItem item;
		for(int i=0;i<array.size();i++){
			item = array.get(i);
			p[i] = new RepositryPanel(item.get(0).toString(), item.get(5).toString(), item.get(4).toString(), 
					item.get(2).toString(), item.get(3).toString(), item.get(1).toString() , item.get(6).toString());
			this.add(p[i]);
		}
		this.setPreferredSize(new Dimension(625,1400));
	
		
		

	}
}

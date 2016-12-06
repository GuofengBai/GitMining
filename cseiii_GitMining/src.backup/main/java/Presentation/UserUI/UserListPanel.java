package Presentation.UserUI;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.json.JSONObject;

import Presentation.RepositryUI.RepositryPanel;
import VO.RepoListLineItem;
import VO.UserListLineItem;

public class UserListPanel extends JPanel{
	JPanel[] p = new JPanel[10];
	JSONObject object;
	public UserListPanel(Vector<UserListLineItem> array) {
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(255, 255, 255));
		this.setLayout(new BoxLayout(this,1));
		UserListLineItem item;
		for(int i=0;i<array.size();i++){
			item = array.get(i);
			p[i] = new UserPanel(item.get(0).toString(), item.get(1).toString(), item.get(2).toString());
			this.add(p[i]);
		}
		this.setPreferredSize(new Dimension(625,1200));
	}

}

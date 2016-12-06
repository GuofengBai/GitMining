package Presentation.MainUI;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class ViewController {
	
	public static MainUI mainUI;
	
	public static List<JPanel> panelList;
	
	public static void init(){
		
		panelList=new ArrayList<JPanel>();
		
		mainUI=new MainUI();
		
		mainUI.setVisible(true);
		
	}
	
	public static void jumpToAnotherView(JPanel panel){
		
		panelList.add(mainUI.content);
		
		if(panelList.size()>100){
			
			panelList.remove(0);
		
		}
		
		mainUI.content.invalidate();
		
		mainUI.contentPane.remove(mainUI.content);
		
		mainUI.content=panel;
		
		mainUI.contentPane.add(mainUI.content);
		
		mainUI.contentPane.repaint();
		
		mainUI.validate();
		
	}
	
	public static void back(){
		
		mainUI.content.invalidate();
		
		mainUI.contentPane.remove(mainUI.content);
		
		mainUI.content=panelList.get(panelList.size()-1);
		
		panelList.remove(panelList.size()-1);
		
		mainUI.contentPane.add(mainUI.content);
		
		mainUI.contentPane.repaint();
		
		mainUI.validate();
		
	}

}

	

package cseiii_GitMining;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Presentation.MainUI.ViewController;

public class Main 
{
    public static void main( String[] args )
    {
    	String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
    	try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        ViewController.init();
    }
}

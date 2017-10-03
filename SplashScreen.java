package Ap;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;


public class SplashScreen extends JWindow {
int duration;
Container c;
JLabel l6;
SplashScreen(int d)
{
	duration=d;
}
public void showSplash()
{
	
c=getContentPane();
c.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
	Icon icon=new ImageIcon("appinstalled.gif");
	l6=new JLabel(icon,l6.CENTER);
	l6.setBorder(BorderFactory.createRaisedBevelBorder());
	Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
	JPanel content=(JPanel)getContentPane();
	content.setBackground(Color.WHITE); 
	int height=100;
	int width=300;		
	
	int x=(screen.width-width)/2;
	int y=(screen.height-height)/2;
	setBounds(x,y,width,height);
	JLabel data=new JLabel("Devoloped by Prasad Rao and Rohan Kulkarni",JLabel.CENTER);
	data.setFont(new Font("Arial",Font.BOLD,18));
	content.add(data,BorderLayout.SOUTH);
	content.setBorder(BorderFactory.createLineBorder(Color.black,12));
	content.add(l6,BorderLayout.CENTER);
	setVisible(true);
	try
	{
		Thread.sleep(duration);
	}
	catch(Exception e)
	{}
		setVisible(false);
		
	
}
	public void showSplashAndExit()
	{
		showSplash();
	}
}



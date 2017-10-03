package Ap;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class MyMenu  extends JFrame implements ActionListener
{
    JButton b1,b2;
    JPanel p1;
    public MyMenu()
    {
        b1=new JButton("ADDRESS BOOK");
        b2=new JButton("Quit");
        b1.addActionListener(this);
        b2.addActionListener(this);
        p1=new JPanel(new GridLayout(2,1,10,10));
        p1.add(b1);
        p1.add(b2);
        add(p1);
        setVisible(true);
        setSize(300,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
            new MyMenu();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b1)
		{
		AddressBook ad=new AddressBook();
		}
		if(e.getSource()==b2)
		{
			System.exit(0);
		}
		
	}

}




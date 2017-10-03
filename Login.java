package Ap;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;


public class Login extends JFrame implements ActionListener
{
    JLabel l1,l2;
    JTextField t1;
    JPasswordField pf1;
    JButton b1,b2;
    JPanel p1;
    public Login()
    {
    	l1=new JLabel("User Name");
    	l2=new JLabel("Password");
    	t1=new JTextField(25);
    	pf1=new JPasswordField(25);
    	b1=new JButton("Submit");
    	b2=new JButton("Cancel");
       p1= new JPanel(new GridLayout(3,2));
    	p1.add(l1);p1.add(t1);
    	p1.add(l2);p1.add(pf1);
    	p1.add(b1);p1.add(b2);
    	add(p1); 
    	b1.addActionListener(this);
    	b2.addActionListener(this);
    	setSize(300,300);
    	 setVisible(true);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     }
  
	public static void main(String[] args) 
	{
		SplashScreen s=new SplashScreen(1000);
		s.showSplashAndExit();
		Login l=new Login();

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String s1,s2;
		if(e.getSource()==b1)
		{
		   s1=t1.getText();
		   s2=pf1.getText();
		   if(s1.equals("prasadrao")&&s2.equals("prasad1997"))
		   {
			   MyMenu m=new MyMenu();
			  
		   }
		   else
			   JOptionPane.showMessageDialog(this, "Invalid User name or Pasword!!!","Error",JOptionPane.ERROR_MESSAGE);
		}
		if(e.getSource()==b2)
		{
			p1.setVisible(false);
			System.exit(0);
		}
	}

}



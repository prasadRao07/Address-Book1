package Ap;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

import javax.swing.*;


public class AddressBook extends JFrame implements ActionListener
{
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
	JTextField slno,name,ad1,ad2,cno,pin;
	JButton btnNew, btnSave,btnSearch,btnDelete,btnEdit,btnUpdate,btnFirst,btnNext,btnPrevious,btnLast,btnView;
	JPanel panelFields, panelButtons,panelMain,panelView;
	JList lst;
	JRadioButton rb1,rb2;
	Vector <Integer> results=new Vector <Integer>();
	 Vector <String> name1=new Vector <String>();
	JComboBox jcb;
	JTextArea tarea;
	Container c;
	Connection cn=null;
	Statement sm=null; Statement stn1=null;
	ResultSet rs=null;
	PreparedStatement ps=null;
	String n,n1="";
	String p,q;
	ResultSetMetaData rsmd=null;
	String[] cities={"Udupi","Cochin","Trichy"};
	public AddressBook()
	{
	super("Address Book");
	initializeCompoments();
	initializeDatabase();
	setSize(600,600);
	setVisible(true);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void dispose()
	{
		super.dispose();
		try
		{
			rs.close();
			sm.close();
			cn.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	
	}
	
	private void initializeCompoments() 
	{
	c=this.getContentPane();
	c.setLayout(new BorderLayout());
	panelMain=new JPanel();
	panelMain.setLayout(new BoxLayout(panelMain,BoxLayout.Y_AXIS));
	panelFields=new JPanel(new GridLayout(5,2,10,10));
	panelButtons=new JPanel(new GridLayout(2,5,10,10));
	l1=new JLabel("Serial Number",JLabel.RIGHT);
	l2=new JLabel("Name",JLabel.RIGHT);
	l3=new JLabel("Address 1",JLabel.RIGHT);
	l4=new JLabel("Address 2",JLabel.RIGHT);
	l5=new JLabel("City",JLabel.RIGHT);
	l6=new JLabel("State",JLabel.RIGHT);
	l7=new JLabel("Pin",JLabel.RIGHT);
	l8=new JLabel("Contact Number",JLabel.RIGHT);
	l9=new JLabel("Resident",JLabel.RIGHT);
	lst=new JList(new String[]{"Karnataka","TN","Ap"});
	slno=new JTextField(25);	
	name=new JTextField(25);	
	ad1=new JTextField(25);
	ad2=new JTextField(25);
	cno=new JTextField(25);
	pin=new JTextField(25);
	rb1=new JRadioButton("Non Indian");
	rb2=new JRadioButton("Indian");
	jcb=new JComboBox(cities);
	
	tarea=new JTextArea(10,50);
	panelView=new JPanel(new GridLayout(1,1,10,10));	
	
	btnNew=new JButton("New");	
	btnSave=new JButton("Save");
	btnUpdate=new JButton("Update");
	btnDelete=new JButton("Delete");
	btnEdit=new JButton("Edit");
	btnFirst=new JButton("First");	
	btnNext=new JButton("Next");
	btnPrevious=new JButton("Previous");	
	btnLast=new JButton("Last");
	btnView=new JButton("View");
	btnSearch=new JButton("Search");
	panelFields.add(l1);panelFields.add(slno);
	panelFields.add(l2);panelFields.add(name);
	panelFields.add(l3);panelFields.add(ad1);
	panelFields.add(l4);panelFields.add(ad2);
	panelFields.add(l5);panelFields.add(jcb);
	panelFields.add(l6);panelFields.add(lst);
	panelFields.add(l7);panelFields.add(pin);
	panelFields.add(l8);panelFields.add(cno);
	panelFields.add(l9);panelFields.add(rb1);panelFields.add(rb2);
	panelFields.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
	
	panelButtons.add(btnNew);
	panelButtons.add(btnSave);
	panelButtons.add(btnDelete);
	panelButtons.add(btnEdit);
	panelButtons.add(btnUpdate);
	panelButtons.add(btnFirst);
	panelButtons.add(btnNext);
	panelButtons.add(btnPrevious); 
	panelButtons.add(btnLast); 
	panelButtons.add(btnView);
	panelButtons.add(btnSearch);
	panelButtons.setBorder(BorderFactory.createLineBorder(Color.red, 3));
    panelView.add(tarea);
	btnNew.addActionListener(this); 
	btnSave.addActionListener(this);
	btnDelete.addActionListener(this);
	btnFirst.addActionListener(this);
	btnNext.addActionListener(this);
	btnPrevious.addActionListener(this);
	btnLast.addActionListener(this); 
	btnEdit.addActionListener(this); 
	btnUpdate.addActionListener(this);
	btnView.addActionListener(this);
	btnSearch.addActionListener(this);
	rb1.addActionListener(this);
	rb2.addActionListener(this);
	
	cno.addKeyListener(new KeyAdapter()//for typing
			{
				public void keyTyped(KeyEvent ke)
				{
				char c=ke.getKeyChar();
				if(!Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE)
				{ 
					ke.consume();
				}
				
				}
				
			});
		
	panelMain.add(panelFields);
	panelMain.add(panelButtons);
	panelMain.add(panelView);
	c.add(panelMain,"Center");
	c.add(new JLabel(""),"South");
	c.add(new JLabel(""),"North");
	c.add(new JLabel(""),"East");
	c.add(new JLabel(""),"West");
	btnUpdate.setEnabled(false);
	
}

	private void initializeDatabase() {
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		String cs="jdbc:mysql://localhost:3306/prasad rao";
		cn=DriverManager.getConnection(cs,"prasadrao","prasad1997");
		sm=cn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rs=sm.executeQuery("Select * from address");
		firstClick();

		
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}
		
}

	
	

	public static void main(String[] args) {
		new AddressBook();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String s=e.getActionCommand();
		if(s.equalsIgnoreCase("New"))
		{
			btnNew.setEnabled(false);
			newrec();
			resetFields();
		}
		if(s.equalsIgnoreCase("Save"))
		{
			saveClick();
		}
		if(s.equalsIgnoreCase("Delete"))
		{
			deleteClick();
		}
		if(s.equalsIgnoreCase("First"))
		{
			firstClick();
		}
		if(s.equalsIgnoreCase("Next"))
		{
			nextClick();
		}
		if(s.equalsIgnoreCase("Last"))
		{
			lastClick();
		}
		if(s.equalsIgnoreCase("Previous"))
		{
			previousClick();
		}
		if(s.equalsIgnoreCase("Edit"))
		{
			editClick();
		}
		if(s.equalsIgnoreCase("Update"))
		{
			updateClick(n,p,q);
		}
		if(s.equalsIgnoreCase("Search"))
		{
			searchClick();
		}
		if(s.equalsIgnoreCase("View"))
		{
			try {
				viewClick();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==rb1)
		{
			n1=rb1.getLabel();
		}
		if(e.getSource()==rb2)
		{
			n1=rb2.getLabel();
		}
	}




	private void searchClick() {
	}
		/*int x1=JOptionPane.showConfirmDialog(this,"Do You Want To  serarch? ", "Search",JOptionPane.YES_NO_OPTION);
		if(x1==JOptionPane.YES_OPTION)
		{
		try
		{
			String x2;
			x2=JOptionPane.showInputDialog("Enter Serial Number to search");
			String selectSQL1="SELECT * from address where slno=?";
			
			ps.setInt(1, 100);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				slno.setText(rs.getString("slno"));
				name.setText(rs.getString("name"));
				ad1.setText(rs.getString("addr1"));
				ad2.setText(rs.getString("addr2"));
				jcb.setToolTipText(rs.getString("city"));
				lst.setToolTipText(rs.getString("state"));
				pin.setText(rs.getString("pincode"));
				cno.setText(rs.getString("contno"));
				rb1.setText(rs.getString("resident"));
				
						
			}
		
		
	}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		}
	}*/

	private void viewClick() throws SQLException 
{
		
		rs= sm.executeQuery("Select * from address");		
		String s="slno \t name\t addr1\t addr2\t city\t state\t pincode\t contno\t resident\n";

		while(rs.next())
		{
	s= s+ rs.getString("slno")+"\t"+rs.getString("name")+"\t"+rs.getString("addr1")+"\t"+rs.getString("addr2")+"\t"+rs.getString("city")+"\t"+rs.getString("state")+"\t"+ rs.getString("pincode")+"\t"+rs.getString("contno")+ "\t"+rs.getString("resident")+"\n";
		}
		tarea.setText(s);
}
	

	private void firstClick() {
		try
		{
			if(rs.first())
			{
				fillFields();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	private void lastClick() {
		try
		{
			if(rs.last())
			{
				fillFields();
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}
	private void previousClick() {
		try
		{
			if(rs.previous())
			{
				fillFields();
			}
			if(rs.isBeforeFirst())
			{
				lastClick();return;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}

	

	private void nextClick() {
		try
		{
			if(rs.next())
			{
				fillFields();
			}
			if(rs.isAfterLast())
			{
				firstClick();return;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}

	private void deleteClick() {
		int x=JOptionPane.showConfirmDialog(this,"Do You Want To Delete? ", "Delete Record",JOptionPane.YES_NO_OPTION);
		if(x==JOptionPane.YES_OPTION)
		{
			try
			{
			PreparedStatement ps=cn.prepareStatement("Delete from address where slno=?");
			ps.setInt(1,Integer.parseInt(slno.getText()));
			ps.execute();
			resetFields();
			rs=sm.executeQuery("Select *from address");
			previousClick();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

	private void saveClick() 
	{
		try
		{
			if(btnNew.isEnabled())
				
			{
				slno.setEditable(false);
				name.requestFocus();
		       
			ps=cn.prepareStatement("Update address set name=?, " + 
					"add1=?,add2=?, city=?, state=?, pincode=?, contno=?, resident=?, where slno = ? ");
			
			ps.setString(1,(name.getText()));
			ps.setString(2,(ad1.getText()));
			ps.setString(3,(ad2.getText()));
			Object z=jcb.getSelectedItem();
			String s=z.toString();
			System.out.println(s);
		     ps.setString(4,s);
		     Object z1=lst.getSelectedValue();
				String s1=z1.toString();
			     
			ps.setString(5,(s1));
			ps.setDouble(6,Double.parseDouble(pin.getText()));
			ps.setInt(7,Integer.parseInt(cno.getText()));
			ps.setInt(9,Integer.parseInt(slno.getText()));
			ps.setString(8,n1);
			ps.execute();
			rs=sm.executeQuery("Select * from address");
			btnNew.setEnabled(true);
			}
		else
		{
			
			ps=cn.prepareStatement("Insert into address (slno,name, " + 
				"addr1,addr2,city,state,pincode,contno,resident) values(?,?,?,?,?,?,?,?,?)");
			ps.setInt(1,Integer.parseInt(slno.getText()));
			ps.setString(2,(name.getText()));
			ps.setString(3,(ad1.getText()));
			ps.setString(4,(ad2.getText()));
			Object z=jcb.getSelectedItem();
			String s=z.toString();
			System.out.println(s);
		     ps.setString(5,s);
		     Object z1=lst.getSelectedValue();
				String s1=z1.toString();
			     
			ps.setString(6,(s1));
			ps.setDouble(7,Double.parseDouble(pin.getText()));
			ps.setInt(8,Integer.parseInt(cno.getText()));
			ps.setString(9,n1);
	ps.execute();
		rs=sm.executeQuery("Select * from address");
		btnNew.setEnabled(true);
		slno.setEditable(false);
		}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
	}


	private void editClick() 
	{
		int x=JOptionPane.showConfirmDialog(this,"Do You Want To Edit The Record ? ", "Delete Record",JOptionPane.YES_NO_OPTION);
		if(x==JOptionPane.YES_OPTION)
		{
			btnSave.setEnabled(false);
		    btnUpdate.setEnabled(true);
			String x1,x2;
			x1=JOptionPane.showInputDialog("Enter Column number to edit - 1 for name, 2 for address 1 & 3 for address 2");
			n=name.getText();
			p=ad1.getText();
			q=ad2.getText();
			int z=Integer.parseInt(x1);
			if(z==1)
			{
				x2=JOptionPane.showInputDialog("Enter New Name");
				n=x2;
			}
			if(z==2)
			{	x2=JOptionPane.showInputDialog("Enter New Address 1");
					p=x2;
			}
			if(z==3)
			{
				x2=JOptionPane.showInputDialog("Enter New Address 2");
				q=x2;
			}
		}
		
		
	}
	private void updateClick(String n,String p,String  q) {
		
		try
		{
	
			ps=cn.prepareStatement("Update address set name=?, " + 
					"addr1=?,addr2=? where slno = ? ");
			ps.setInt(4,Integer.parseInt(slno.getText()));
			ps.setString(1,n);
			ps.setString(2,p);
			ps.setString(3,q);
		    ps.execute();
			rs=sm.executeQuery("Select * from address");
			btnNew.setEnabled(true);
			btnSave.setEnabled(true);
			btnUpdate.setEnabled(false);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
				
	}

	

	private void resetFields() 
	{
		//slno.setText("");
		name.setText("");
		ad1.setText("");
		ad2.setText("");
		//jcb.setToolTipText("");
		lst.setToolTipText("");
        //rb1.setText("");
        //rb2.setText("");
		cno.setText("");
		pin.setText("");
	}
	
	private void fillFields() 
	{
	try
	{
		slno.setText(rs.getString("slno"));
		name.setText(rs.getString("name"));
		ad1.setText(rs.getString("addr1"));
		ad2.setText(rs.getString("addr2"));
		jcb.setToolTipText(rs.getString("city"));
		lst.setToolTipText(rs.getString("state"));
		pin.setText(rs.getString("pincode"));
		cno.setText(rs.getString("contno"));
		rb1.setText(rs.getString("resident"));
	}
	catch(Exception e)
	{
		System.out.println(e.toString());
	}
		
	}
	public void newrec()
	{
		
		int n=0;
		 try
		 {
       	  Class.forName("com.mysql.jdbc.Driver");
		 }
		 catch(Exception ex)
		 {
		 System.out.println(ex.toString());
		 }
         try
		{
        	 String s="jdbc:mysql://localhost:3306/prasad rao";
		cn=DriverManager.getConnection(s,"Prasad Rao","prasad1997");
		 stn1=cn.createStatement();
		 String sq="Select max(slno) from address";
		 ResultSet res=stn1.executeQuery(sq);
		 while(res.next())
		 {
			  
			 n=res.getInt(1);
		 }
		
		 if(n==0)
		  n=1;
		  else
		  n=n+1;
		  slno.setText(""+n); 
		}
         catch(Exception e)
         {
        	 System.out.println(e.toString());
         }
	}

}

	
	


                          

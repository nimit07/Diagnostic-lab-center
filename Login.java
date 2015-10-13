//making login window

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.CheckboxGroup;
import java.sql.Connection;
import java.sql.DriverManager;
//login class implements ActionListener
class Login implements ActionListener
{// variable declarations
	JFrame jf;
	JButton loginbtn;
	JLabel headlbl,usernamelbl,passwordlbl;
	JPanel TP,centerPanel,bottomPanel;
	JTextField usernametf,passwordtf;

	PreparedStatement pstmt;
	ResultSet rset;
	GridBagConstraints c = new GridBagConstraints();

	boolean check=false;
	//login function
	Login()
	{
		jf = new JFrame("Login");
		//top panel
		TP = new TopPanel();
		TP.setLayout(new GridLayout(1,0));
		//center Panel
		headlbl = new JLabel("LOGIN");
		Font headFont = new Font("VERDANA",Font.BOLD,20);
		headlbl.setFont(headFont);
		usernamelbl = new JLabel("user name");
		passwordlbl = new JLabel("password");
		usernametf = new JTextField(20);
		passwordtf = new JTextField(20);
		//setting layout for center Panel and adding userna and password fields
		centerPanel = new JPanel(new GridBagLayout());
		c.insets = new Insets(10,10,10,10);
		c.gridx = 1;
		c.gridy = 0;
		centerPanel.add(headlbl,c);

		c.insets = new Insets(4,4,4,4);
		c.gridx = 0;
		c.gridy = 1;
		centerPanel.add(usernamelbl,c);
		c.gridx = 1;
		centerPanel.add(usernametf,c);

		c.gridy = 2;
		centerPanel.add(passwordtf,c);
		c.gridx = 0;
		centerPanel.add(passwordlbl,c);
		centerPanel.setBackground(new Color(105,105,105));

		//bottom panel
		bottomPanel = new JPanel();
		loginbtn = new JButton("Login");
		bottomPanel.add(loginbtn);
		loginbtn.addActionListener(this);
		bottomPanel.setBackground(new Color(0, 123, 167,100));

		//adding panels to Jframe
		jf.add(TP,BorderLayout.NORTH);
		jf.add(bottomPanel,BorderLayout.SOUTH);
		jf.add(centerPanel,BorderLayout.CENTER);

		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(503,570);
		jf.setVisible(true);
	}
	// implementing actionPerformed function for login button ActionListener
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			//loading driver for database connection
			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");//database uri with user name and password

			//checking for the validity of username
			pstmt = con.prepareStatement("select * from login");
			rset = pstmt.executeQuery();

			while(rset.next())
			{
				if(rset.getString("username").equals(usernametf.getText()))
				{
					System.out.println("user name found");
					check = true;
					pstmt = con.prepareStatement("select password from login where username=?");
					pstmt.setString(1,usernametf.getText());
					ResultSet r = pstmt.executeQuery();
					if(r.next())
					{
						if(r.getString("password").equals(passwordtf.getText()))
						{
							 new Administrator();
							System.out.println("Login Successfully");
							jf.dispose();

						}
						else
						{
							JOptionPane.showMessageDialog(null,"password is not valid");
						}
					}
				}


			}
			if(check == false)
			{
				System.out.println("user not found");
				JOptionPane.showMessageDialog(null,"Username is not valid");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/*public void dispose()
	{
		jf.dispose();
	}*/

	public static void main(String args[])
	{
	  new Login();
	}

	public boolean loginCheck()
	{
		if(check == true)
			return true;
		else return false;
	}
}

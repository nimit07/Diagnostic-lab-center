//this is doctor list window
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
//import java.awt.CheckboxGroup;
import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;

class DoctorList implements ActionListener
{
	JFrame Frame;
	JPanel TP,BottomPanel,CenterPanel;
	JLabel Headlbl,SrNolbl,DocNamelbl,ComPerlbl,Amountlbl,ASrNolbl,ADocNamelbl,AComPerlbl,AAmountlbl;
	JButton Backbtn;
	int SrNo = 1;
	
	GridBagConstraints c = new GridBagConstraints();
	PreparedStatement pstmt;
	ResultSet rset;
	
	
	DoctorList()
	{
		Frame = new JFrame("Doctor's List ");
		//top panel
		TP = new TopPanel();
		//center panel
		Headlbl = new JLabel("Doctor List");
		Font headFont = new Font("VERDANA",Font.BOLD,20);
		Headlbl.setFont(headFont);
		SrNolbl = new JLabel("Sr. No.");
		DocNamelbl = new JLabel("Doctor's Name");
		ComPerlbl = new JLabel("Com. Per.");
		Amountlbl = new JLabel("Amount");
		
		CenterPanel = new JPanel(new GridBagLayout());
		c.insets = new Insets(1,1,1,1);
		c.gridx = 2;
		c.gridy = 0;
		CenterPanel.add(Headlbl,c);
		c.gridx = 0;
		c.gridy = 1;
		CenterPanel.add(SrNolbl,c);
		c.gridx = 1;
		CenterPanel.add(DocNamelbl,c);
		c.gridx = 2;
		CenterPanel.add(ComPerlbl,c);
		c.gridx = 3;
		CenterPanel.add(Amountlbl,c);
		CenterPanel.setBackground(new Color(0,128,128,64));
		
		try{
		//now adding the list
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");
		pstmt = con.prepareStatement("select * from doctor");
		rset = pstmt.executeQuery();
		
		//bottom panel
		BottomPanel = new JPanel();
		Backbtn = new JButton("Back");
		BottomPanel.add(Backbtn);
		Backbtn.addActionListener(this);
		BottomPanel.setBackground(new Color(0, 146, 105,46));
		
		c.gridy ++;
		while(rset.next())
		{
			c.gridx = 0;
			ASrNolbl = new JLabel(""+SrNo);
			CenterPanel.add(ASrNolbl,c);
			SrNo++;
			c.gridx = 1;
			ADocNamelbl = new JLabel(rset.getString("doctor_name"));
			CenterPanel.add(ADocNamelbl,c);
			c.gridx = 2;
			AComPerlbl = new JLabel(rset.getString("COMISSION_PER"));
			CenterPanel.add(AComPerlbl,c);
			c.gridx = 3;
			pstmt = con.prepareStatement("select COMM_AMNT from commission where doctor_id=?");
			pstmt.setInt(1,Integer.parseInt(rset.getString("doctor_id")));
			ResultSet rset2;
			rset2 = pstmt.executeQuery();
			if(rset2.next())
			{
				AAmountlbl = new JLabel(rset2.getString("COMM_AMNT"));
				CenterPanel.add(AAmountlbl,c);
			}
			c.gridy++;
		
		}
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
		
		
		
		
		Frame.add(TP,BorderLayout.NORTH);
		Frame.add(BottomPanel,BorderLayout.SOUTH);
		Frame.add(CenterPanel,BorderLayout.CENTER);
		Frame.setSize(1366,700+(SrNo*10));
		Frame.setVisible(true);
		
		
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		Frame.dispose();
		new Administrator();
	}
	
/*	public static void main(String args[])
	{
		new DoctorList();
	}
	*/
}

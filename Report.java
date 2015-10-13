//report generator
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.CheckboxGroup;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;

class Report extends MouseAdapter implements ItemListener
{
	JFrame Frame;
	JLabel PatientNamelbl,APatientNamelbl,RegDatelbl,ARegDatelbl,Sexlbl,ASexlbl,Agelbl,AAgelbl,ReportHeadlbl,TestHeadlbl,Patient_idlbl;
	JLabel SerialNolbl,TestNamelbl,TestRatelbl,TestResultlbl,TotalAmountlbl,DocSignlbl,Totallbl;
	JPanel TP,BottomPanel,CenterPanel;
	JButton Backbtn;
	Choice PatientSelectChoice;
	int patient_id,Total=0;
	GridBagConstraints c1 = new GridBagConstraints();
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
	
	PreparedStatement pstmt;
	ResultSet rset;
	
	Report(int pat_id)
	{
		
		Frame = new JFrame("Patient Test's Report ");
		 //top panel 
		TP = new TopPanel();
		patient_id=pat_id;
		//Center Panel
		ReportHeadlbl = new JLabel("Medical Report");
		Font headFont = new Font("VERDANA",Font.BOLD,20);
		ReportHeadlbl.setFont(headFont);
		PatientNamelbl = new JLabel("Patient Name");
		Agelbl = new JLabel("Age");
		Sexlbl = new JLabel("Sex");
		RegDatelbl = new JLabel("Registration Date");
		

		
		TestHeadlbl = new JLabel("Patient Test Details");
		TestHeadlbl.setFont(headFont);
		SerialNolbl = new JLabel("Serial No.");
		TestNamelbl = new JLabel("Test Name");
		TestRatelbl = new JLabel("Test Rate");
		TestResultlbl = new JLabel("Test Result");
		TotalAmountlbl = new JLabel("Total Amount");
		Patient_idlbl=new JLabel("Patient id");
		CenterPanel = new JPanel(new GridBagLayout());
		c1.insets = new Insets(2,2,2,2);
		c1.gridx = 1;
		c1.gridy = 0;
		CenterPanel.add(ReportHeadlbl,c1);
		c1.gridx = 0;
		c1.gridy = 2;
		CenterPanel.add(PatientNamelbl,c1);
		c1.gridy = 4;
		CenterPanel.add(RegDatelbl,c1);
		c1.gridy = 6;
		CenterPanel.add(Agelbl,c1);
		c1.gridy = 8;
		CenterPanel.add(Sexlbl,c1);
		
			
		c1.gridx = 1;
		c1.gridy = 11;
		CenterPanel.add(TestHeadlbl,c1);
		c1.gridx = 0;
		c1.gridy = 13;
		CenterPanel.add(SerialNolbl,c1);
		c1.gridx = 1;
		CenterPanel.add(TestNamelbl,c1);
		c1.gridx = 2;
		CenterPanel.add(TestRatelbl,c1);
		c1.gridx = 3;
		CenterPanel.add(TestResultlbl,c1);
		CenterPanel.setBackground(new Color(105,105,105));
		
		//bottom panel
		BottomPanel = new JPanel();
		Backbtn = new JButton("Back");
		
		
	//	BottomPanel.add(Patient_idlbl);
		
		BottomPanel.add(Backbtn);
		
		Backbtn.addMouseListener(this);
	
		
		BottomPanel.setBackground(new Color(0, 123, 167,100));
		
		Frame.add(TP,BorderLayout.NORTH);
		Frame.add(CenterPanel,BorderLayout.CENTER);
		Frame.add(BottomPanel,BorderLayout.SOUTH);
		Frame.setSize(700,700);
		System.out.println("reached here");
		Frame.setVisible(true);
	
	
	
		try
		{
			//load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//making connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");
				System.out.println(con);
			
		
		
		pstmt = con.prepareStatement("select * from patient where patient_id=?");
		pstmt.setInt(1,patient_id);
		rset = pstmt.executeQuery();
		
		if(rset.next())
		{
			
			
			String name = ""+rset.getString("first_Name")+rset.getString("last_name");
			System.out.println("name of patient is : "+name);
			APatientNamelbl = new JLabel(name);
			c1.gridx = 2;
			c1.gridy = 2;
			CenterPanel.add(APatientNamelbl,c1);
			
			java.util.Date date = dateFormat.parse(rset.getString("registration_date"));
			ARegDatelbl = new JLabel(String.valueOf(date));
			c1.gridy = 4;
			CenterPanel.add(ARegDatelbl,c1);
			
			AAgelbl = new JLabel(rset.getString("age"));
			c1.gridy = 6;
			CenterPanel.add(AAgelbl,c1);
			
			ASexlbl = new JLabel(rset.getString("sex"));
			c1.gridy = 8;
			CenterPanel.add(ASexlbl,c1);
			
			Frame.setVisible(true);
		}
		
		PreparedStatement pstmt2;
		JLabel TestName ;
		int srno = 1;
		JLabel SerialNo;
		JLabel TestRate;
		JLabel TestResult;
		c1.gridx = 1;
		c1.gridy = 14;
		ResultSet rset2;
		pstmt = con.prepareStatement("select * from report where patient_id=?");
		
		pstmt.setInt(1,patient_id);
		rset = pstmt.executeQuery();
		while(rset.next())
		{
			
			pstmt2 = con.prepareStatement("select * from mytest where test_id=?");
			pstmt2.setInt(1,Integer.parseInt(rset.getString("test_id")));
			
			rset2 = pstmt2.executeQuery();
			System.out.println("REACHED");
			if(rset2.next())
			{
				TestName = new JLabel(rset2.getString("test_name"));
				c1.gridx = 1;
				CenterPanel.add(TestName,c1);
				
				
				
				
				TestRate = new JLabel(rset2.getString("test_rate"));
				c1.gridx = 2;
				CenterPanel.add(TestRate,c1);
				Frame.setVisible(true);
				
				System.out.println("reached here after test rate");
			}
			SerialNo = new JLabel(String.valueOf(srno));
			c1.gridx = 0;
			CenterPanel.add(SerialNo,c1);
			TestResult = new JLabel(rset.getString("result"));
			c1.gridx = 3;
			CenterPanel.add(TestResult,c1);
			
			c1.gridy++;
			Frame.setVisible(true);
		}
		
		Frame.setSize(1366,700+(srno*10));
		Frame.setVisible(true);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	Report()
	{
		
		Frame = new JFrame("Patient Test's Report");
		 //top panel 
		TP = new TopPanel();
		
		//Center Panel
		ReportHeadlbl = new JLabel("Medical Report");
		Font headFont = new Font("VERDANA",Font.BOLD,20);
		ReportHeadlbl.setFont(headFont);
		PatientNamelbl = new JLabel("Patient Name");
		Agelbl = new JLabel("Age");
		Sexlbl = new JLabel("Sex");
		RegDatelbl = new JLabel("Registration Date");
		

		
		TestHeadlbl = new JLabel("Patient Test Details");
		TestHeadlbl.setFont(headFont);
		SerialNolbl = new JLabel("Serial No.");
		TestNamelbl = new JLabel("Test Name");
		TestRatelbl = new JLabel("Test Rate");
		TestResultlbl = new JLabel("Test Result");
		TotalAmountlbl = new JLabel("Total Amount");
		Patient_idlbl=new JLabel("Patient id");
		
		CenterPanel = new JPanel(new GridBagLayout());
		CenterPanel.setBackground(new Color(105,105,105));
	
		//bottom panel
		BottomPanel = new JPanel();
		Backbtn = new JButton("Back");
		PatientSelectChoice = new Choice();
		try
		{
			//load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//making connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");
				
			pstmt = con.prepareStatement("select patient_id from patient");
			ResultSet rset = pstmt.executeQuery();
				
			while(rset.next())
			{
				PatientSelectChoice.add(rset.getString("patient_id"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			}
		BottomPanel.add(Patient_idlbl);
		BottomPanel.add(PatientSelectChoice);
		BottomPanel.add(Backbtn);
		
		Backbtn.addMouseListener(this);
		PatientSelectChoice.addItemListener(this);
		
		BottomPanel.setBackground(new Color(0, 123, 167,100));
		
		Frame.add(TP,BorderLayout.NORTH);
		Frame.add(CenterPanel,BorderLayout.CENTER);
		Frame.add(BottomPanel,BorderLayout.SOUTH);
		Frame.setSize(1366,730);
		System.out.println("reached here");
		Frame.setVisible(true);
	}
	
	public void mouseClicked(MouseEvent me)
	{
		Frame.dispose();
		new Administrator();
	}
	
	public void itemStateChanged(ItemEvent ie)
	{	
		
		Choice dummy = (Choice)ie.getSource();
		CenterPanel.removeAll();
		
		
		c1.insets = new Insets(2,2,2,2);
		c1.gridx = 1;
		c1.gridy = 0;
		CenterPanel.add(ReportHeadlbl,c1);
		c1.gridx = 0;
		c1.gridy = 2;
		CenterPanel.add(PatientNamelbl,c1);
		c1.gridy = 4;
		CenterPanel.add(RegDatelbl,c1);
		c1.gridy = 6;
		CenterPanel.add(Agelbl,c1);
		c1.gridy = 8;
		CenterPanel.add(Sexlbl,c1);
		
			
		c1.gridx = 1;
		c1.gridy = 11;
		CenterPanel.add(TestHeadlbl,c1);
		c1.gridx = 0;
		c1.gridy = 13;
		CenterPanel.add(SerialNolbl,c1);
		c1.gridx = 1;
		CenterPanel.add(TestNamelbl,c1);
		c1.gridx = 2;
		CenterPanel.add(TestRatelbl,c1);
		c1.gridx = 3;
		CenterPanel.add(TestResultlbl,c1);
		CenterPanel.setBackground(new Color(105,105,105));
		
		
		patient_id = Integer.parseInt((dummy.getSelectedItem()));
		System.out.println("selected patient is : "+patient_id);
		
		try
		{
			PatientSelectChoice.removeAll();
			//load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//making connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");
				System.out.println(con);
			pstmt = con.prepareStatement("select patient_id from patient");
			ResultSet rset = pstmt.executeQuery();
				
			while(rset.next())
			{
				PatientSelectChoice.add(rset.getString("patient_id"));
				System.out.println(patient_id);
			}
		
		
		pstmt = con.prepareStatement("select * from patient where patient_id=?");
		pstmt.setInt(1,patient_id);
		rset = pstmt.executeQuery();
		
		if(rset.next())
		{	
			String name = ""+rset.getString("first_Name")+rset.getString("last_name");
			System.out.println("name of patient is : "+name);
			APatientNamelbl = new JLabel(name);
			c1.gridx = 2;
			c1.gridy = 2;
			CenterPanel.add(APatientNamelbl,c1);
			
			java.util.Date date = dateFormat.parse(rset.getString("registration_date"));
			ARegDatelbl = new JLabel(String.valueOf(date));
			c1.gridy = 4;
			CenterPanel.add(ARegDatelbl,c1);
			
			AAgelbl = new JLabel(rset.getString("age"));
			c1.gridy = 6;
			CenterPanel.add(AAgelbl,c1);
			
			ASexlbl = new JLabel(rset.getString("sex"));
			c1.gridy = 8;
			CenterPanel.add(ASexlbl,c1);
			
			Frame.setVisible(true);
		}
		
		PreparedStatement pstmt2;
		JLabel TestName ;
		int srno = 1;
		JLabel SerialNo;
		JLabel TestRate;
		JLabel TestResult;
		c1.gridx = 1;
		c1.gridy = 14;
		ResultSet rset2;
		pstmt = con.prepareStatement("select * from report where patient_id=?");
		
		pstmt.setInt(1,patient_id);
		rset = pstmt.executeQuery();
		while(rset.next())
		{
			
			pstmt2 = con.prepareStatement("select * from mytest where test_id=?");
			pstmt2.setInt(1,Integer.parseInt(rset.getString("test_id")));
			
			rset2 = pstmt2.executeQuery();
			System.out.println("REACHED");
			
			int x=0;
			if(rset2.next())
			{
				TestName = new JLabel(rset2.getString("test_name"));
				c1.gridx = 1;
				CenterPanel.add(TestName,c1);
				
				x=Integer.parseInt(rset2.getString("test_rate"));
				Total+=x;
				
				TestRate = new JLabel(rset2.getString("test_rate"));
				c1.gridx = 2;
				CenterPanel.add(TestRate,c1);
				Frame.setVisible(true);
				
				System.out.println("reached here after test rate");
			}
			SerialNo = new JLabel(String.valueOf(srno));
			c1.gridx = 0;
			CenterPanel.add(SerialNo,c1);
			TestResult = new JLabel(rset.getString("result"));
			c1.gridx = 3;
			CenterPanel.add(TestResult,c1);
			
			c1.gridy++;
			Frame.setVisible(true);
		}
		
			
		c1.gridx=4;
			Totallbl=new JLabel("Total= "+Total);
			CenterPanel.add(Totallbl,c1);
		
		Frame.setSize(1366,(700+(srno*10)));
		Frame.setVisible(true);
		
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*public static void main(String args[])
	{
		 new Report();
	}
	*/
}

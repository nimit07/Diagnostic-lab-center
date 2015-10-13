//adding test results of patient's 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
class PatientResult extends WindowAdapter implements ActionListener,ItemListener
{
		JFrame jf;
		JLabel Headlbl,Patient_idlbl,Add_Test_Resultlbl,Test_resultlbl[];
		JTextField  Test_resulttf[];
		JButton Submitbtn,Backbtn;
		Choice Patient_idch;
		JPanel TP,CenterPnl,BottomPnl;

		String str;
		Connection con;
		GridBagConstraints c1 =new GridBagConstraints();
		//for dynamic form part
		JLabel TestNamelbl[];
		String Test_Name_str[];
		JTextField TestResulttf[];
		int Testid[];
		//JButton Submitbtn,Backbtn;
		ResultSet rset;
		//GridBagConstraints gbc = new GridBagConstraints();
		PreparedStatement pstmt;
		int no_Of_Test;
		int index = 0;

		PatientResult()
		{
				jf=new JFrame("Add Patient Results");

				Headlbl=new JLabel("Patient Result");
				Patient_idlbl= new JLabel("Choose Patient id");
				Add_Test_Resultlbl=new JLabel("Test Result");

				Patient_idch=new Choice();
				Patient_idch.add("patient id");
				Patient_idch.addItemListener(this);
				//panel
				CenterPnl=new JPanel(new GridBagLayout());

				TP=new TopPanel();
				BottomPnl=new JPanel();

				Submitbtn=new JButton("Submit");
				Backbtn=new JButton("Back");
				Submitbtn.addActionListener(this);
				Backbtn.addActionListener(this);



				//adding patient id name from database

				try
				{
					 //step 1 loding the database driver
					  Class.forName("oracle.jdbc.driver.OracleDriver");  //type 4 driver

					//step 2 creating connection from DriverManager by specfic database(mydatabase)
					   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");//connection
					  System.out.println(con);

					  str= "select patient_id  from patient";

					  pstmt=con.prepareStatement(str);

					  rset=pstmt.executeQuery();
					  while(rset.next())
					  {
							Patient_idch.add(rset.getString("patient_id"));

					  }
				}
				catch(Exception e)
				{
						e.printStackTrace();
    			}

				//centerpnl 	,Patient_idlbl,Add_Test_Resultlbl,

				c1.insets=new Insets(4,4,4,4);

				c1.gridx=1;
				c1.gridy=0;
				CenterPnl.add(Headlbl,c1);
				c1.gridx=0;
				c1.gridy=1;
				CenterPnl.add(Patient_idlbl,c1);
				c1.gridx=1;
				c1.gridy=1;
				CenterPnl.add(Patient_idch,c1);
				c1.gridx=1;
				c1.gridy=2;
				CenterPnl.add(Add_Test_Resultlbl,c1);
				CenterPnl.setBackground(new Color(105,105,105));
					//bottompanel
				BottomPnl.add(Submitbtn);
				BottomPnl.add(Backbtn);
				BottomPnl.setBackground(new Color(0, 123, 167,100));
				//adding panels to jframe
				jf.setLayout(new BorderLayout());
				jf.add(TP,BorderLayout.NORTH);
				jf.add(CenterPnl,BorderLayout.CENTER);

				jf.add(BottomPnl,BorderLayout.SOUTH);
				jf.setSize(1366,730);
				jf.setVisible(true);


		}

		public void dis()
		{
			jf.dispose();
			}




			public void actionPerformed(ActionEvent ae)
				{

						JButton jbtn=(JButton)ae.getSource();

						if(jbtn==Submitbtn)
						{
							try{
									Class.forName("oracle.jdbc.driver.OracleDriver");
									 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");
									//checking for previous entries
									pstmt = con.prepareStatement("select * from report where patient_id=?");
									pstmt.setInt(1,Integer.parseInt(Patient_idch.getSelectedItem()));
									rset = pstmt.executeQuery();
									if(rset.next())
									{
										System.out.println("patient report already exists");
										pstmt = con.prepareStatement("update report set result=? where patient_id=? AND test_id=?");
										for(int i=0;i<no_Of_Test;i++)
										{
											pstmt.setString(1,TestResulttf[i].getText());
											pstmt.setInt(2,Integer.parseInt(Patient_idch.getSelectedItem()));
											pstmt.setInt(3,Testid[i]);
											pstmt.addBatch();
										}
										int[] count = pstmt.executeBatch();
										System.out.println("test results are updated");

									JOptionPane.showMessageDialog(null,"Patient Information Successfully stored");




						}
				else
			{
				System.out.println("patient was not exists");
				pstmt = con.prepareStatement("insert into report values(?,?,?)");
				for(int i=0;i<no_Of_Test;i++)
				{
					pstmt.setInt(1,Integer.parseInt(Patient_idch.getSelectedItem()));
					pstmt.setInt(2,Testid[i]);
					pstmt.setString(3,TestResulttf[i].getText());
					pstmt.addBatch();
				}
				int[] count = pstmt.executeBatch();
				System.out.println("test results are inserted");
				JOptionPane.showMessageDialog(null,"Patient Information Successfully stored");

			}
		}
		catch(Exception e)
		{
			System.out.println("problem in submitButton");
			JOptionPane.showMessageDialog(null,"Something gone Wrong");
			e.printStackTrace();

		}
	}
						if (jbtn==Backbtn)
						{new Administrator();
						dis();}
	}


	public void itemStateChanged(ItemEvent ie)
	{
		Choice dummy = (Choice)ie.getSource();
		int patient_id = Integer.parseInt(dummy.getSelectedItem());
		try
		{
			//getting no of tests
			//load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//making connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");

			pstmt = con.prepareStatement("select no_of_test from patient where PATIENT_ID=?");
			pstmt.setInt(1,patient_id);
			rset = pstmt.executeQuery();
			if(rset.next())
			{
				no_Of_Test = Integer.parseInt(rset.getString("no_of_test"));
			}

			System.out.println("no of test for the selected patient is/are = "+no_Of_Test+"\n\n");



			c1.insets = new Insets(1,1,1,1);
			TestNamelbl = new JLabel[no_Of_Test];
			TestResulttf = new JTextField[no_Of_Test];
			Testid = new int[no_Of_Test];
			Test_Name_str = new String[no_Of_Test];

			pstmt = con.prepareStatement("select test_id from patient_test where patient_id=?");
			pstmt.setInt(1,patient_id);
			rset = pstmt.executeQuery();

			index = 0;
			while(rset.next())
			{
				pstmt = con.prepareStatement("select test_name from mytest where test_id=?");
				pstmt.setInt(1,Integer.parseInt(rset.getString("test_id")));

				ResultSet rsetTemp = pstmt.executeQuery();
			Testid[index] = Integer.parseInt(rset.getString("test_id"));

				if(rsetTemp.next())
				{

					TestNamelbl[index] = new JLabel(rsetTemp.getString("test_name"));
					Test_Name_str[index] = new String(rsetTemp.getString("test_name"));
					System.out.println("test names are:"+rsetTemp.getString("test_name"));
					TestResulttf[index] = new JTextField(20);
					c1.gridx = 0;
					c1.gridy+=2;
					CenterPnl.add(TestNamelbl[index],c1);
					c1.gridx = 1;
					CenterPnl.add(TestResulttf[index],c1);
					index++;


					}

				System.out.println("no of test from loop is : "+index);
			}


		jf.setSize(1366,700+index*10);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


		}
			/*	public static void main(String a[])
			{
					PatientResult pr= new PatientResult();



			}
			*/

}

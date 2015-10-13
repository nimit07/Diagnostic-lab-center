import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.sql.*;
//extends MouseAdapter
class PatientList extends MouseAdapter
{
	// variable declarations
	JFrame jf;
	JPanel TP,BottomPanel,CenterPanel;
	JLabel HeadLabel,SerialNumberlbl,PatientNamelbl,RegDatelbl,NoOfTestlbl,TotAmountlbl,ReferedBylbl,Reportlbl;
	JLabel ASerialNumberlbl,APatientNamelbl,ARegDatelbl,ANoOfTestlbl,ATotAmountlbl,AReferedBylbl,AReportlbl;
	JButton ShowReportbtn;
		GridBagConstraints c1 = new GridBagConstraints();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");

	int count=1;
	String str,doctorname;
		Connection con;
		PreparedStatement pstmt,pstmt2;
		int no_Of_Test;
		int index = 0;
		int doctorid,patientid,testid,totalamount;
		ResultSet rset,rset2;

		//default constructor
	PatientList()
	{
			jf=new JFrame("Patient's List");
		//panels
		TP=new TopPanel();
		CenterPanel=new JPanel(new GridBagLayout());

		HeadLabel=new JLabel("Patient List");
		SerialNumberlbl=new JLabel("S.no");
		PatientNamelbl=new JLabel("Patient Name");
		RegDatelbl=new JLabel("Registration Date");
		NoOfTestlbl=new JLabel("No. of Test");
		TotAmountlbl=new JLabel("Tot. Amount");
		ReferedBylbl=new JLabel("Refered By");
		Reportlbl=new JLabel("Report");


			c1.gridx=0;
				c1.gridy=0;
				CenterPanel.add(SerialNumberlbl,c1);
				c1.gridx=1;
				CenterPanel.add(PatientNamelbl,c1);
				c1.gridx=2;
				CenterPanel.add(RegDatelbl,c1);
				c1.gridx=3;
				CenterPanel.add(NoOfTestlbl,c1);
				c1.gridx=4;
				CenterPanel.add(TotAmountlbl,c1);
				c1.gridx=5;
				CenterPanel.add(ReferedBylbl,c1);
				c1.gridx=6;
				CenterPanel.add(Reportlbl,c1);
		//loding patient names
		try
		{
			//load driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//making connection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");//database uri with username and password

			pstmt = con.prepareStatement("select * from patient");
			ResultSet rset = pstmt.executeQuery();
			//generating patient List
			while(rset.next())
			{



				 doctorid=rset.getInt("doctor_id");
					patientid=rset.getInt("patient_id");
				 ASerialNumberlbl=new JLabel("."+count);

				 APatientNamelbl=new JLabel(rset.getString("first_name")+" "+rset.getString("last_name"));
				 java.util.Date date = dateFormat.parse(rset.getString("registration_date"));
				  ARegDatelbl=new JLabel(String.valueOf(date));
				 ANoOfTestlbl=new JLabel(String.valueOf(rset.getInt("no_of_test")));

				 pstmt2 = con.prepareStatement("select * from report where patient_id=?");
				 pstmt2.setInt(1,patientid);
				 rset2 = pstmt2.executeQuery();
				 if(rset2.next())
				 {
					 testid=rset2.getInt("test_id");
					 }

				/*	patientid=rset.getInt("patient_id");
				 	pstmt2 = con.prepareStatement("select test_id from patient_test where patient_id=(rset.getInt("patient_id");)");
					rset2 = pstmt2.executeQuery();
				if(rset2) */
				pstmt2 = con.prepareStatement("select * from mytest where test_id=?");
							pstmt2.setInt(1,testid);
							rset2 = pstmt2.executeQuery();
							if(rset2.next())
							{
								totalamount+=rset2.getInt("test_rate");
							}

				 ATotAmountlbl=new JLabel(String.valueOf(totalamount));
				  pstmt2 = con.prepareStatement("select doctor_name from doctor where doctor_id=?");
				pstmt2.setInt(1,doctorid);
				rset2 = pstmt2.executeQuery();
				if (rset2.next())
			{

				 AReferedBylbl=new JLabel(rset2.getString("doctor_name"));
			 }


				 AReportlbl=new JLabel("show report");
				count++;
				c1.gridx=0;
				c1.gridy=count;
				CenterPanel.add(ASerialNumberlbl,c1);
				c1.gridx=1;
				CenterPanel.add(APatientNamelbl,c1);
				c1.gridx=2;
				CenterPanel.add(ARegDatelbl,c1);
				c1.gridx=3;
				CenterPanel.add(ANoOfTestlbl,c1);
				c1.gridx=4;
				CenterPanel.add(ATotAmountlbl,c1);
				c1.gridx=5;
				CenterPanel.add(AReferedBylbl,c1);

				ShowReportbtn=new JButton("ShowReport,"+patientid);
				//adding MouseListener
				ShowReportbtn.addMouseListener(this);



				c1.gridx=6;
				CenterPanel.add(ShowReportbtn,c1);
				totalamount=0;

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			}


		CenterPanel.setBackground(new Color(0, 123, 167,100));
		jf.add(TP,BorderLayout.NORTH);
		jf.add(CenterPanel,BorderLayout.CENTER);
		jf.setVisible(true);
		jf.setSize(1366,730);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JScrollPane scroller = new JScrollPane(CenterPanel);
		jf.getContentPane().add(scroller, BorderLayout.CENTER);
		}
		//overriding mouseClicked function
		public void mouseClicked(MouseEvent me)
		{
			JButton dummy = (JButton)me.getSource();
			String a = dummy.getLabel();
			String b[] = a.split(",");
			int pat_id = Integer.parseInt(b[1]);
			new Report(pat_id);
			jf.dispose();


		}
	//no main function is needed unless you want to check it indivisually
/*	public static void main(String a[])
	{
		new PatientList();

		}
		 */

	}

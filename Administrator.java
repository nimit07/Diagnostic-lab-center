//Administrator functions
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

class Administrator extends WindowAdapter implements ActionListener
{
	//variable declarationss
	JPanel TP,HeadPnl;
	JPanel CenterPnl,CenterPnlEast,CenterPnlWest,CentermidPnl,CenterPnlNorth,CenterPnlSouth;
	JPanel BottomPanel;
	JLabel Centerlbl,Headlbl;
	JButton AddTestbtn,AddDoctorbtn,PatientReportbtn,PatientRegistrationbtn,PatientResultbtn,PatientListbtn,DoctorListbtn;
	JFrame jf;
	GridBagConstraints c1 =new GridBagConstraints();
	//default constructor
	Administrator()
	{		TP=new TopPanel();
			jf = new JFrame("ADMINISTRATOR WINDOW");
			jf.setLayout(new BorderLayout());
	//	TP=new TopPanel();
		HeadPnl= new JPanel();
	 CenterPnl= new JPanel(new BorderLayout());
	 CenterPnlEast= new JPanel(new GridLayout(1,0));
	 CenterPnlWest= new JPanel(new GridLayout(2,0));
	 CenterPnlNorth= new JPanel(new GridLayout(1,1));
	 CentermidPnl= new JPanel(new GridBagLayout());
	 CenterPnlSouth =new JPanel(new GridLayout(1,1));
	 BottomPanel= new JPanel();

		Headlbl=new JLabel("ADMINISTRATOR");

		HeadPnl.add(Headlbl,c1);



		AddTestbtn=new JButton("Add Test");
		AddTestbtn.addActionListener(this);

		AddDoctorbtn=new JButton("Add Doctor");
		AddDoctorbtn.addActionListener(this);
		PatientReportbtn=new JButton("Patient Report");
		PatientReportbtn.addActionListener(this);
		PatientRegistrationbtn=new JButton(" Patient Registretion");
		PatientRegistrationbtn.addActionListener(this);
		PatientResultbtn=new JButton("Patient result");
		PatientResultbtn.addActionListener(this);
		PatientListbtn=new JButton("Patient List");
		PatientListbtn.addActionListener(this);
		DoctorListbtn=new JButton("Doctor List");
		DoctorListbtn.addActionListener(this);


		HeadPnl.add(Headlbl,BorderLayout.SOUTH);


		CenterPnlNorth.add(AddDoctorbtn);
		CenterPnlNorth.add(AddTestbtn);
		CenterPnl.add(CenterPnlNorth,BorderLayout.NORTH);
		CentermidPnl.add(TP);

			CenterPnlWest.add(PatientReportbtn,BorderLayout.NORTH);
			CenterPnlWest.add(PatientResultbtn,BorderLayout.SOUTH);
			CenterPnl.add(CenterPnlWest,BorderLayout.WEST);

		CenterPnlEast.add(PatientRegistrationbtn);

		CenterPnl.add(CenterPnlEast,BorderLayout.EAST);
		CenterPnl.add(CentermidPnl,BorderLayout.CENTER);
		CenterPnlSouth.add(PatientListbtn);
			CenterPnlSouth.add(DoctorListbtn);
			CenterPnl.add(CenterPnlSouth,BorderLayout.SOUTH);

		jf.add(HeadPnl,BorderLayout.NORTH);

		 jf.add(CenterPnl,BorderLayout.CENTER);
		jf.setSize(750,440);
		jf.setVisible(true);



		}

	public void actionPerformed(ActionEvent ae)
	{
		JButton btn = (JButton)ae.getSource();
		if(btn == AddTestbtn)
		{
			new AddTest();
		}
		else if(btn == AddDoctorbtn)
		{
			 new AddDoctor();
		}
		else if(btn == PatientRegistrationbtn)
		{
			 new PatientRegistration();
		}
		else if(btn == PatientReportbtn)
		{
			new Report();
		}
		else if(btn == PatientListbtn)
		{
			new PatientList();
		}
		else if(btn == DoctorListbtn)
		{
			 new DoctorList();
		}
		else if(btn == PatientResultbtn)
		{
			 new PatientResult();
		}
	}

	/*public static void main(String a[])
	{
		new Administrator();
		}
	*/
	}

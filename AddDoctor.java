import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.sql.*;
// extending Jframe and implementing ActionListener
class AddDoctor extends JFrame implements ActionListener
{
	//variable declarations
	String str;
	PreparedStatement pstmt;
	ResultSet rset;
	JPanel CenterPnl,BottomPnl;
	TopPanel TP;
	JLabel Headlbl,DoctorNamelbl,Comissionperlbl;
	JButton Submitbtn,Backbtn;
	TextField DoctorNametf,Comissionpertf;
	GridBagConstraints g=new GridBagConstraints();
	//default constructor
	AddDoctor()
	{
		super("Add Doctor");
		Headlbl=new JLabel("Add Doctor");
		DoctorNamelbl=new JLabel("Doctor Name");
		Headlbl.setFont(new Font("Rockwell",Font.BOLD,30));
		Comissionperlbl=new JLabel("Comission Per. ");

		setLayout(new BorderLayout());
		//declarations of TopPanel, CenterPanel ,BottomPanel
		TP=new TopPanel();

		CenterPnl=new JPanel(new GridBagLayout());
		BottomPnl=new JPanel(new GridBagLayout());

       // BottomPnl=new JPanel();



		DoctorNametf=new TextField(30);
		Comissionpertf=new TextField(30);


		Submitbtn=new JButton("Submit");
		Backbtn=new JButton("Back");
		//adding to CenterPanel
		g.insets=new Insets(5,5,5,5);
		g.gridx=1;
		g.gridy=0;
		CenterPnl.add(Headlbl,g);
		g.gridx=0;
		g.gridy=2;
		CenterPnl.add (DoctorNamelbl,g);
		g.gridx=1;
		g.gridy=2;
		CenterPnl.add (DoctorNametf,g);
		g.gridx=0;
		g.gridy=3;
		CenterPnl.add (Comissionperlbl,g);
		g.gridx=1;
		g.gridy=3;
		CenterPnl.add (Comissionpertf,g);
		CenterPnl.setBackground(new Color(105,105,105));

		//bottomPanel
		BottomPnl.setBackground(new Color(0, 123, 167,100));
		BottomPnl.setForeground(Color.GRAY);
			//adding listeners
		Submitbtn.addActionListener(this);
		Backbtn.addActionListener(this);
		 //addWindowListener(this);

		BottomPnl.add(Submitbtn);
		BottomPnl.add(Backbtn);
		//adding panels
		add(TP,BorderLayout.NORTH);
		add(BottomPnl,BorderLayout.SOUTH);
		add(CenterPnl,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366,730);
		setVisible(true);

	}



	//implementing actionPerformed function
	public void actionPerformed(ActionEvent ae)
			{
				JButton btn=(JButton)ae.getSource();
				//System.out.println(btn);
				if(btn==Submitbtn)
				{

					try
					{

					   //step 1 loding the database driver
									Class.forName("oracle.jdbc.driver.OracleDriver");  //type 4 driver

								//step 2 creating connection from DriverManager by specfic database(mydatabase)
								  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");//connection
								  System.out.println(con);



					  str= "select *from doctor where doctor_name=?";
					  pstmt=con.prepareStatement(str);
					  pstmt.setString(1,DoctorNametf.getText());
				      //pstmt.setInt(2,Integer.parseInt(commtxt.getText()));

					  rset=pstmt.executeQuery();
					  if(rset.next())

					  {
						JOptionPane.showMessageDialog(null," Doctor already exists");
					  }
					  else
					  {
						str="insert into doctor values(doctor_id.NEXTVAL,?,?)";
						pstmt=con.prepareStatement(str);
					    pstmt.setString(1,DoctorNametf.getText());
				        pstmt.setFloat(2,Float.parseFloat(Comissionpertf.getText()));
						//pstmt.setFloat(3,Float.parseFloat(NormalValtf.getText()));

						int i=pstmt.executeUpdate();
						if(i>0)
						{
							 JOptionPane.showMessageDialog(null," Doctor added successfuly");
						}
					  }







					}
					catch(Exception ee)
					{
						ee.printStackTrace();
    				}
				}


				if(btn==Backbtn)
				{
					//System.out.println("The form has been  Back and going  to close");
					dispose();
					new Administrator();
				}


	}
		//no main function is needed unless you want to check it indivisually
/*
		public static void main(String[]args)
{
	new AddDoctor();

	}
	*/

			}

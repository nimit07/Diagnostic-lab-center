import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowEvent;
import javax.swing.*;
import java.sql.*;
//extending WindowAdapter and implementing ActionListener
class AddTest extends WindowAdapter implements ActionListener
{
	//variable declarations
	JFrame jf;
	String str;
	PreparedStatement pstmt;
	ResultSet rset;
	JPanel CenterPnl,BottomPnl;
	TopPanel TP;
	JLabel AddTestlbl,TestNamelbl,TestRatelbl,NormalVallbl;
	JButton Submitbtn,Backbtn;
	TextField TestNametf,TestRatetf,NormalValtf;
	GridBagConstraints g=new GridBagConstraints();

	//default constructor
	AddTest()
	{
		jf=new JFrame("Add Tests");
		jf.setLayout(new BorderLayout());
		AddTestlbl=new JLabel("Add Test");
		AddTestlbl.setFont(new Font("Rockwell",Font.BOLD,30));
		TestNamelbl=new JLabel("Test Name");
		TestRatelbl=new JLabel ("Test Rate");
		NormalVallbl=new JLabel("Normal Value");
		//panels
		TP=new TopPanel();
		CenterPnl=new JPanel(new GridBagLayout());
		//BottomPnl=new JPanel(new GridBagLayout());
		CenterPnl.setBackground(new Color(105,105,105));
    BottomPnl=new JPanel();
    BottomPnl.setForeground(Color.GRAY);
		BottomPnl.setBackground(new Color(0, 123, 167,100));

		TestNametf=new TextField(30);
		TestRatetf=new TextField(30);
		NormalValtf =new TextField(30);

		Submitbtn=new JButton("Submit");
		Backbtn=new JButton("Back");
		g.insets=new Insets(5,5,5,5);
		g.gridx=1;
		g.gridy=0;
		CenterPnl.add(AddTestlbl,g);
		g.gridx=0;
		g.gridy=2;
		CenterPnl.add (TestNamelbl,g);
		g.gridx=1;
		g.gridy=2;
		CenterPnl.add (TestNametf,g);
		g.gridx=0;
		g.gridy=3;
		CenterPnl.add (TestRatelbl,g);
		g.gridx=1;
		g.gridy=3;
		CenterPnl.add (TestRatetf,g);
		g.gridx=0;
		g.gridy=4;
		CenterPnl.add (NormalVallbl,g);
		g.gridx=1;
		g.gridy=4;
		CenterPnl.add (NormalValtf,g);
		jf.add(CenterPnl);
		//adding listeners
		Submitbtn.addActionListener(this);
		Backbtn.addActionListener(this);
		 //addWindowListener(this);

		BottomPnl.add(Submitbtn);
		BottomPnl.add(Backbtn);
		jf.add(TP,BorderLayout.NORTH);
		jf.add(BottomPnl,BorderLayout.SOUTH);
		jf.setSize(1366,730);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
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



					  str= "select *from mytest where test_name=?";
					  pstmt=con.prepareStatement(str);
					  pstmt.setString(1,TestNametf.getText());
				      //pstmt.setInt(2,Integer.parseInt(commtxt.getText()));

					  rset=pstmt.executeQuery();
					  if(rset.next())

					  {
						JOptionPane.showMessageDialog(null," test already exists");
					  }
					  else
					  {
						str="insert into mytest values(test_id.NEXTVAL,?,?,?)";
						pstmt=con.prepareStatement(str);
					    pstmt.setString(1,TestNametf.getText());
				        pstmt.setFloat(2,Float.parseFloat(TestRatetf.getText()));
						pstmt.setFloat(3,Float.parseFloat(NormalValtf.getText()));

						int i=pstmt.executeUpdate();
						if(i>0)
						{
							 JOptionPane.showMessageDialog(null," test added successfuly");
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
					System.out.println("The form has been  Cancel and going  to close");
					jf.dispose();
				}
			}

			public void windowClosing(WindowEvent we)
			{

				Frame f=(Frame)we.getSource();
				f.dispose();
		}





			}

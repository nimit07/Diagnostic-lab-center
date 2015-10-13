//registraion form for new patients
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
//implementing ActionListener and ItemListener
class PatientRegistration  implements ActionListener,ItemListener
{
//variable declarations
		String str;
		Connection con;
		PreparedStatement pstmt;
		ResultSet rset;
		JFrame jf;
		int doctor_id=0;


		JPanel TP,CenterPnl,BottomPnl,CenterPnl1;
		JLabel FNamelbl,LNamelbl,Reg_Datelbl,Doctorlbl,Tot_No_of_Testlbl,Agelbl,Sexlbl,Contact_nolbl,Addresslbl,testlbl[];
		JTextField FNametf,LNametf,Reg_Datetf,Agetf,Sextf,Contact_notf,Addresstf,test_nametf[];
		Choice Doctorch,Tot_No_of_Testch,sexch,testNameChoice[];
		JButton Submitbtn,Backbtn;
		GridBagConstraints c1 =new GridBagConstraints();
		GridBagConstraints c2 =new GridBagConstraints();
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd-mm-yyyy");

		int no_of_tests,patient_id=0;
		float commission_per,total_commission=0;
		float Tot_test_rate=0;
		float prcommission_amount=0;
		//constuctor coding started

		PatientRegistration()
		{
				//memory allocation

				jf=new JFrame("Patient Registration");
				FNametf=new JTextField(30);
				LNametf=new JTextField(30);
				Reg_Datetf =new JTextField(30);
				Agetf=new JTextField(30);
				Sextf=new JTextField(30);
				Contact_notf=new JTextField(30);
				Addresstf=new JTextField(30);

				//label
				FNamelbl=new JLabel("First Name");
				LNamelbl=new JLabel("Last Name");
				Reg_Datelbl=new JLabel("Registration Date");
				Doctorlbl=new JLabel("Refered By");
				Tot_No_of_Testlbl=new JLabel("Total No. Of Test");
				Agelbl=new JLabel ("Age");
				Sexlbl=new JLabel("Sex");
				Contact_nolbl=new JLabel("Contact Number");
				Addresslbl=new JLabel("Address");


				//panel
				CenterPnl=new JPanel(new GridBagLayout());
				CenterPnl1=new JPanel(new GridBagLayout());
				TP=new TopPanel();
				BottomPnl=new JPanel();

				//button
				Submitbtn=new JButton("Submit");
				Backbtn=new JButton("Back");
				Submitbtn.addActionListener(this);
				Backbtn.addActionListener(this);

				//choice
				Doctorch=new Choice();

				Doctorch.add("   Choose Doctor   ");

				//adding doctors name from database

				try
				{
					 //step 1 loding the database driver
					  Class.forName("oracle.jdbc.driver.OracleDriver");  //type 4 driver

					//step 2 creating connection from DriverManager by specfic database(mydatabase)
					  Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");//connection
					  System.out.println(con);

					  str= "select doctor_name from doctor";

					  pstmt=con.prepareStatement(str);

					  rset=pstmt.executeQuery();
					  while(rset.next())
					  {
							Doctorch.add(rset.getString("doctor_name"));

					  }
				}
				catch(Exception e)
				{
						e.printStackTrace();
    			}


					Tot_No_of_Testch=new Choice();
					Tot_No_of_Testch.add ("  Number of Tests  ");
					Tot_No_of_Testch.add("1");
					Tot_No_of_Testch.add("2");
					Tot_No_of_Testch.add("3");
					Tot_No_of_Testch.add("4");
					Tot_No_of_Testch.add("5");
					Tot_No_of_Testch.add("6");
					Tot_No_of_Testch.add("7");
					Tot_No_of_Testch.add("8");
					Tot_No_of_Testch.add("9");
					Tot_No_of_Testch.add("10");
					Tot_No_of_Testch.addItemListener(this);

					sexch=new Choice();
					sexch.add("       sex       ");
					sexch.add("male");
					sexch.add("female");
				//	sexch.addActionListener(this);

					//centerpnl

				c1.insets=new Insets(4,4,4,4);

				c1.gridx=0;
				c1.gridy=1;
				CenterPnl.add(FNamelbl,c1);
				c1.gridx=1;
				c1.gridy=1;
				CenterPnl.add(FNametf,c1);
				c1.gridx=0;
				c1.gridy=2;
				CenterPnl.add(LNamelbl,c1);
				c1.gridx=1;
				c1.gridy=2;
				CenterPnl.add(LNametf,c1);
				c1.gridx=0;
				c1.gridy=3;
				CenterPnl.add(Agelbl,c1);

				c1.gridx=1;
				c1.gridy=3;
				CenterPnl.add(Agetf,c1);
				c1.gridx=0;
				c1.gridy=4;

				CenterPnl.add(Sexlbl,c1);
				c1.gridx=1;
				c1.gridy=4;
				CenterPnl.add(sexch,c1);
				c1.gridx=0;
				c1.gridy=5;
				CenterPnl.add(Reg_Datelbl,c1);
				c1.gridx=1;
				c1.gridy=5;

				CenterPnl.add(Reg_Datetf,c1);
				c1.gridx=0;
				c1.gridy=6;
				CenterPnl.add(Contact_nolbl,c1);
				c1.gridx=1;
				c1.gridy=6;
				CenterPnl.add(Contact_notf,c1);
				c1.gridx=0;
				c1.gridy=7;
				CenterPnl.add(Addresslbl,c1);
				c1.gridx=1;
				c1.gridy=7;
				CenterPnl.add(Addresstf,c1);
				c1.gridx=0;
				c1.gridy=8;
				CenterPnl.add(Doctorlbl,c1);
				c1.gridx=1;
				c1.gridy=8;
				CenterPnl.add(Doctorch,c1);
				c1.gridx=0;
				c1.gridy=9;
				CenterPnl.add(Tot_No_of_Testlbl,c1);
				c1.gridx=1;
				c1.gridy=9;

				CenterPnl.add(Tot_No_of_Testch,c1);
				CenterPnl.setBackground(new Color(105,105,105));

				//bottompanel
				BottomPnl.add(Submitbtn);
				BottomPnl.add(Backbtn);
				BottomPnl.setBackground(new Color(0, 123, 167,100));


				//adding panels to jframe
				jf.setLayout(new BorderLayout());
				jf.add(TP,BorderLayout.NORTH);
				jf.add(CenterPnl,BorderLayout.CENTER);
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jf.add(BottomPnl,BorderLayout.SOUTH);
			  jf.setSize(1366,730);
			  jf.setVisible(true);
			}


			public void windowClosing(WindowEvent we)
			{

				Frame f=(Frame)we.getSource();
				jf.dispose();
			}

			public void actionPerformed(ActionEvent ae)
				{
					try
					{
						JButton jbtn=(JButton)ae.getSource();

						if(jbtn==Submitbtn)
						{

						//step 1 loding the database driver
						Class.forName("oracle.jdbc.driver.OracleDriver");  //type 4 driver

						//step 2 creating connection from DriverManager by specfic database(mydatabase)
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");//connection
						System.out.println(con);

						String str="select doctor_id from doctor where doctor_name=?";
						pstmt= con.prepareStatement(str);
						pstmt.setString(1,String.valueOf(Doctorch.getSelectedItem()));
						rset=pstmt.executeQuery();

						if(rset.next())
						{
							doctor_id=rset.getInt("doctor_id");
					    }

						str="select * from patient where first_name=? AND last_name=? AND age= ?";
						pstmt= con.prepareStatement(str);
						pstmt.setString(1,FNametf.getText());
						pstmt.setString(2,LNametf.getText());
						pstmt.setInt(3,Integer.parseInt(Agetf.getText()));
						rset=pstmt.executeQuery();
						if(rset.next())
						{
						   System.out.println("Patient is already exits");
						}
					   else
						{

						 java.util.Date d=dateFormat.parse(Reg_Datetf.getText());
						 long t=d.getTime();
						 java.sql.Date date=new java.sql.Date(t);
						 str="insert into patient(patient_id,first_name,last_name,registration_date,age,sex,no_of_test,contact_no,address,doctor_id) values(patient_id.NEXTVAL,?,?,?,?,?,?,?,?,?)";
						 pstmt= con.prepareStatement(str);


						 pstmt.setString(1,FNametf.getText());
						 pstmt.setString(2,LNametf.getText());

						 pstmt.setDate(3,date);

						 pstmt.setInt(4,Integer.parseInt(Agetf.getText()));
						 pstmt.setString(5,sexch.getSelectedItem());
						 pstmt.setInt(6,Integer.parseInt(Tot_No_of_Testch.getSelectedItem()));
						 pstmt.setString(7,Contact_notf.getText());
						 pstmt.setString(8,Addresstf.getText());
						 pstmt.setInt(9,doctor_id);
							System.out.println("reached here\n\n\n");
						int result = pstmt.executeUpdate();
							if(result>0)
							{
									JOptionPane.showMessageDialog(null,"Patient Information Successfully stored");
							}
							else
							{
									JOptionPane.showMessageDialog(null,"Something gone Wrong");
							}




								//local declearetion
									int test_id[]= new int [no_of_tests];
									String test_name[]=new String[no_of_tests];
									//int patient_id;


									for (int i=0;i<no_of_tests;i++)
									{
										 //test_nametf[i].getText()
										test_name[i]=test_nametf[i].getText();



									}
										for (int i=0;i<no_of_tests;i++)
										{		str="select test_id from mytest where test_name=?";
												pstmt=con.prepareStatement(str);
												pstmt.setString(1,test_name[i]);
												rset = pstmt.executeQuery();
												if(rset.next())
													{
														test_id[i]=Integer.parseInt(rset.getString("test_id"));
														System.out.println(test_id[i]);
													}

										}


																		//getting patient_id
										pstmt=con.prepareStatement("select patient_id from patient where first_name=?");
										pstmt.setString(1,FNametf.getText());
										rset=pstmt.executeQuery();
										if(rset.next())
											{
												patient_id = rset.getInt("patient_id");
											}

																	//checking for patient_id
										System.out.println("patient_id is "+patient_id+"\n\n");

												//now entering in the patient_test database
										for(int l=0;l<no_of_tests;l++)
										{
												System.out.println("The patient id is "+patient_id);
												System.out.println("The test id is"+test_id[l]);
												pstmt = con.prepareStatement("insert into patient_test values(?,?)");
												pstmt.setInt(1,patient_id);
												pstmt.setInt(2,test_id[l]);

												int confirm = pstmt.executeUpdate();
												if(confirm>0)
												{
													System.out.println("successfully entered in patient_test");
												}
												else
												{
													System.out.println("ERROR : not successfully added in patient_test");
												}
										}

													//now entering data into commission database
										pstmt = con.prepareStatement("select COMISSION_PER from DOCTOR where doctor_id=?");
										pstmt.setInt(1,doctor_id);
										rset = pstmt.executeQuery();
										if(rset.next())
										{
											commission_per = rset.getFloat("COMISSION_PER");
										}

										System.out.println("commission_per of doctor is : "+commission_per);

										//now calculating commission and entering in the COMMISSION database
										//step1 : i'm getting the existing commission in doctors account
										total_commission = 0.0f;
										pstmt = con.prepareStatement("select * from COMMISSION where doctor_id=?");
										pstmt.setInt(1,doctor_id);
										rset = pstmt.executeQuery();

										if (rset.next())
										{
												prcommission_amount=rset.getFloat("COMM_AMNT");
												System.out.println(prcommission_amount);
										}
										for(int l=0;l<no_of_tests;l++)
										{
											pstmt = con.prepareStatement("select TEST_RATE from mytest where test_id=?");
											pstmt.setInt(1,test_id[l]);
											System.out.println("ur test_id is"+test_id[l]);
											rset = pstmt.executeQuery();
											if(rset.next())
												{
													Tot_test_rate += rset.getFloat("TEST_RATE");
													System.out.println("rate of test "+test_id[l]+" is :"+Tot_test_rate+"\n\n");
													total_commission = total_commission + (Tot_test_rate*commission_per/100);
												}
										}

							System.out.println("privious commission wass : "+prcommission_amount+"\n\n");
							System.out.println("new comission is "+(total_commission+prcommission_amount)+"\n ");
							pstmt = con.prepareStatement("select * from commission where DOCTOR_ID=?");
							pstmt.setInt(1,doctor_id);
							rset=pstmt.executeQuery();


							if(rset.next())
							{
								System.out.println("pre existing doctor commission entry");
								pstmt = con.prepareStatement("update commission set comm_amnt=? where doctor_id=?");
								pstmt.setFloat(1,total_commission);
								System.out.println("doctor id is : "+doctor_id+"\n\n");
								pstmt.setInt(2,doctor_id);

								int checking = pstmt.executeUpdate();
								if(checking>0)
								{
									System.out.println("commission successfully update");
								}
								else
								{
									JOptionPane.showMessageDialog(null,"Error in updating Commission");
								}
							}
							else
							{
								System.out.println("new commission entry");
								pstmt = con.prepareStatement("insert into commission values(?,?)");
								pstmt.setInt(1,doctor_id);
								pstmt.setFloat(2,total_commission);

								int checking = pstmt.executeUpdate();
								if(checking >0)
								{
									System.out.println("commission successfully update");
								}
								else
								{
									JOptionPane.showMessageDialog(null,"Error in updating Commission");
								}
							}

														}



							}








						else if (jbtn==Backbtn)
						{

								System.out.println("out");
								jf.dispose();
								new Administrator();

						}
					}
					catch(Exception e)
					{
						 e.printStackTrace();
					}

				}

					public void itemStateChanged(ItemEvent ie)

				{
						//locally intialization(and coding for test names)

							CenterPnl1.removeAll();
							Choice dummy = (Choice)ie.getSource();
							String myCheck = dummy.getSelectedItem().trim();
							int p = Integer.parseInt(myCheck);
						//dummy.removeAll();
							JLabel TestNameDetaillbl=new JLabel ("Test Name Detail ");
							c2.gridx = 1;
											c2.gridy = 1;
							CenterPnl1.add(TestNameDetaillbl,c2);

							testlbl = new JLabel[p];
							//jtestNametf = new JTextField[p];
							testNameChoice = new Choice[p];

							//debugging test statement
							System.out.println("reached here and p is :"+p);

							for(int i=0;i<p;i++)
							{

								String st = "Test no. "+(i+1);
								testlbl[i]=new JLabel(st);

								testNameChoice[i]=new Choice();


								try
								{
									Class.forName("oracle.jdbc.driver.OracleDriver");
									Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","nimit","NIMIT");
									PreparedStatement pstmt = con.prepareStatement("select test_name from mytest");
									ResultSet rset2 = pstmt.executeQuery();
									while(rset2.next())
									{
										testNameChoice[i].add(rset2.getString("test_name"));
									}
								}
								catch(Exception e)
								{
									e.printStackTrace();
								}

								c2.gridx = 0;
								c2.gridy = (i+2);
								CenterPnl1.add(testlbl[i],c2);
								c2.gridx = 1;
								CenterPnl1.add(testNameChoice[i],c2);
								c1.gridy=10;
								CenterPnl.add(CenterPnl1,c1);
								CenterPnl1.setBackground(new Color(105,105,105));
							}



							jf.setSize(1366,730);
							jf.setVisible(true);
				}
				//no main function is needed unless you want to check it indivisually
				/*
				 	public static void main(String a[])
			{
					new PatientRegistration();

			}
				 */

							}

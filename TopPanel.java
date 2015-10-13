
//top panel
import java.awt.*;
//import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
class TopPanel extends JPanel
{
	JLabel LabNamelbl;
	ImageIcon Logoicon;
	TopPanel ()
	{
    setLayout(new GridLayout(1,0));
    oin = new ImageIcon("logo.jpg");
    LabNamelbl=new JLabel(Logoicon,JLabel.CENTER);
    setBackground(new Color(189,203,204));
    add(LNalbl);
	}

package watermarking;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.List;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class watermarking extends JFrame {

	private JPanel contentPane;
	JLabel lbl_selectframe;
	JLabel lbl_EmbededFrame;
	 JLabel lblprograss;
	List list;
	static String selfrme;
	String SelectedFrame,senderIp,senderName,EmpMessage,VideoPath,Receivcer,FrameNo;
	int senderPort;
	ArrayList res=new ArrayList();
	JProgressBar js;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					watermarking frame = new watermarking(selfrme);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
void getEmpmsg()
{
Socket s;
ArrayList al=new ArrayList();

al.add("GetEmpMessage");
try {
	s=new Socket("localhost",10001);
	ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
    out.writeObject(al);
    ObjectInputStream oin=new ObjectInputStream(s.getInputStream());
   res=(ArrayList )oin.readObject();
   		senderIp=res.get(0).toString();
   		senderPort=Integer.parseInt(res.get(1).toString());
   		senderName=res.get(2).toString();
   		EmpMessage=res.get(3).toString();
   		VideoPath=res.get(4).toString();
   		Receivcer=res.get(5).toString();
   list.add(EmpMessage);
    js.setVisible(false);
    lbl_selectframe.setIcon(new ImageIcon(SelectedFrame));
} catch (UnknownHostException e) {
	e.printStackTrace();
} catch (IOException e) {
	e.printStackTrace();
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}
}
	 
private void callProgress() {
	// TODO Auto-generated method stub
	class MyThread extends Thread{
		public void run() {
			int i = 0;
			while(i<=100){
				js.setValue(i);
				lblprograss.setText("Pls Wait RDH-EI implemention is going on !!!.... "+i+"%");
				lblprograss.setForeground (Color.red);
				
				i++;
				try {
					Thread.sleep(350);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			JOptionPane.showMessageDialog(rootPane, "Successfully Implemented RDH-EI algo...!!!");
			js.setVisible(false);
		this.stop();
		
		}
	}
	new MyThread().start();

}

	public watermarking(String frame) {
		
		SelectedFrame=frame;
		File f= new File(frame);
		FrameNo=f.getName();
		System.out.println("selectde frame  "+frame);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 645, 733);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 619, 701);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblname = new JLabel("User Selected Frame");
		lblname.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblname.setForeground(Color.BLUE);
		lblname.setBounds(46, 43, 146, 32);
		panel.add(lblname);
		
		  lbl_selectframe = new JLabel(" ");
		
	 
		lbl_selectframe.setBounds(21, 86, 341, 232);
		panel.add(lbl_selectframe);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 0, 0), 3, true));
		panel_1.setBackground(new Color(216, 191, 216));
		panel_1.setForeground(new Color(218, 112, 214));
		panel_1.setBounds(412, 86, 154, 219);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		list = new List();
		list.setBounds(23, 10, 110, 177);
		panel_1.add(list);
		
		JLabel lblEmbededMessage = new JLabel("Encrypted Message");
		lblEmbededMessage.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEmbededMessage.setForeground(Color.BLUE);
		lblEmbededMessage.setBounds(433, 47, 121, 32);
		panel.add(lblEmbededMessage);
		
		JLabel lblWaterMarking = new JLabel("RDH-EI Implementation");
		lblWaterMarking.setForeground(new Color(255, 0, 0));
		lblWaterMarking.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		lblWaterMarking.setBounds(230, 11, 146, 32);
		panel.add(lblWaterMarking);
		
		btnNewButton= new JButton("Embeded");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String sr=null;
				 BufferedImage image;
				try {
					 	image = ImageIO.read(new File(SelectedFrame));
					 	System.out.println(" frame "+SelectedFrame);
					 	Graphics g = image.getGraphics();
				 	    g.setFont(g.getFont().deriveFont(30f));
				 	    g.drawString(EmpMessage, 50, 150);
				 	    g.dispose();
				 	    sr="D:\\Receiver\\Frames\\video\\FrameNo";
				 	    ImageIO.write(image, "jpg", new File(sr));
				 	    System.out.println("encrypt frame "+sr);
				 	   ImageIO.write(image, "jpg", new File(SelectedFrame));
						Thread.sleep(4000);
				} catch (InterruptedException e1) {
							e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				   lbl_EmbededFrame.setIcon(new ImageIcon(sr));
				 	    
			}
		});
		btnNewButton.setBounds(191, 329, 104, 32);
		panel.add(btnNewButton);
		
		JButton btnsend = new JButton("Send Message");
		btnsend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Socket s;
				try {
				File fi=new File(VideoPath);
				FileInputStream fis=new FileInputStream(fi);
				byte[] b=new byte[fis.available()];
				fis.read(b);
				System.out.println("file size  "+fis.available());
				ArrayList al=new ArrayList();
				al.add(senderName);
				al.add(Receivcer);
				al.add(EmpMessage);
				al.add(b);
				al.add(FrameNo);
			 System.out.println("sending details..!!!! "+al);
				
			 System.out.println("before socket sender ip and port  "+senderIp+" "+senderPort);
					s=new Socket(senderIp,
t);
					System.out.println("sender ip and port  "+senderIp+" "+senderPort);
					ObjectOutputStream out=new ObjectOutputStream(s.getOutputStream());
				    out.writeObject(al);
				    JOptionPane.showMessageDialog(rootPane, "Your Message Send Successfully...!!!");
				    }catch(Exception e1)
				    {
				    	e1.printStackTrace();
				    }
				}  
			 
		});
		btnsend.setBounds(485, 445, 121, 32);
		panel.add(btnsend);
		
		JButton btn_holevideo = new JButton("Merge Video");
		btn_holevideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				js.setVisible(true);
				 
				callProgress();
				//JOptionPane.showMessageDialog(rootPane, "The video  successfully merged!!!....");
			}
		});
		btn_holevideo.setBounds(485, 372, 121, 32);
		panel.add(btn_holevideo);
	 
	  lblprograss = new JLabel(" ");
	  lblprograss.setFont(new Font("Times New Roman", Font.BOLD, 14));
	 lblprograss.setForeground(new Color(255, 255, 255));
	 lblprograss.setBounds(103, 474, 330, 25);
	 panel.add(lblprograss);
	 
	 JPanel panel_2 = new JPanel();
	 panel_2.setBorder(new LineBorder(new Color(255, 0, 255), 4, true));
	 panel_2.setBounds(10, 369, 434, 306);
	 panel.add(panel_2);
	 panel_2.setLayout(null);
	 
	  lbl_EmbededFrame = new JLabel(" ");
	 lbl_EmbededFrame.setBounds(10, 11, 414, 284);
	 panel_2.add(lbl_EmbededFrame);
	 
	 js = new JProgressBar();
	 js.setBounds(162, 73, 173, 25);
	 js.setStringPainted(true);
	 js.setForeground(Color.blue);
	 js.setString("Algorithm Implementing");
	 panel_2.add(js);
		
		getEmpmsg();
	}
	JButton btnNewButton;
}

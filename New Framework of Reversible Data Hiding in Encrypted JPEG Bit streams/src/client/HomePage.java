package client;

import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;

import ABE.Encryption;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;
import java.awt.Button;

public class HomePage extends JFrame implements Runnable{

	private JPanel contentPane;
	private static JTextField txtser_ip;
	private static JTextField txtser_port;
	private  JTextField txtclient_ip;
	private   JTextField txtclient_Name;
	private JTextField txt_receiver;
	private JTextField txt_video;
	
	ServerSocket ss;
	Socket s;
JPanel panel_Encer;
JPanel panel;
JPanel panel_2;
JLabel upvideo;
List list_encrp;
List onlineuser;

String client_ip=null;
String server_ip,server_port;
int client_port;
 static String user; 
String uname,ReceiverName,filename,path,uploadlocation;


 ArrayList ipport=new ArrayList();
 ArrayList res=new ArrayList();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage(user);
					frame.callThread();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void callThread()
	{
		
		server_ip=JOptionPane.showInputDialog("Enter the Server Ipaddres....!!!!");
		server_port=JOptionPane.showInputDialog("Enter the Server Port....!!!!");
		
		try {
			Inet4Address ipadd = (Inet4Address) Inet4Address.getLocalHost();
			client_ip=ipadd.getHostAddress();
			Random r=new Random();
			client_port=r.nextInt(1000);
			        ipport.add("ipinformation");
				    ipport.add( uname);
				    ipport.add( client_ip);
				    ipport.add(String.valueOf(client_port));
		   System.out.println("MY Name, IP and PortNo is...!!!   "+uname+"   "+client_ip+"  "+client_port);
		   	txtser_ip.setText(server_ip);
			txtser_port.setText(server_port);
			txtclient_ip.setText(client_ip);
			txtclient_Name.setText(uname); 
				s=new Socket(server_ip,Integer.parseInt(server_port));
			    ObjectOutputStream os=new ObjectOutputStream(s.getOutputStream());
	            os.writeObject(ipport);
		}catch (NumberFormatException e) {
				e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  	 upvideo.setVisible(false);
	         panel_Encer.setVisible(false);
		
		Thread t=new Thread(this);
		t.start();
		  
	}
	
	public void run() {
	
		try{
			ss =new ServerSocket(client_port);
			while(true)
			{
			  System.out.println(" Am waiting to receive Data ........!!!"); 
			  s = ss.accept();
		       System.out.println(" Server Running ........!!!\nData Received...!!!"); 
               ObjectInputStream out=new ObjectInputStream(s.getInputStream());
               res=(ArrayList)out.readObject();
               if(res.size()!=0 || res.size()>0)
               {
            	   JLabel msg=new JLabel();
            	   msg.setText("recever");
            	   msg.setIcon(new ImageIcon("D:\\2018 Major Work Space\\VTJIM02-2018\\images\\imagesmsg.jpg"));
            	   msg.setBounds(39, 421, 200, 65);
            	   panel.add(msg);
               JButton MsgReceive=new JButton();
               MsgReceive.addActionListener(new ActionListener() {
       			public void actionPerformed(ActionEvent e) {
       				Receiver frame = new Receiver(res);
					frame.setVisible(true);
       			}
       		});
               MsgReceive.setText(" Show Message");
               MsgReceive.setBounds(39, 491, 150, 20);
       		   panel.add(MsgReceive);
               }
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	
	   
      
	}
	 
	public HomePage(String name) {
		uname=name;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		  panel = new JPanel();
		panel.setBounds(10, 11, 658, 518);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.MAGENTA, 3, true));
		panel_1.setBackground(new Color(255, 222, 173));
		panel_1.setBounds(10, 65, 146, 314);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblWelcnmeToThe = new JLabel("Server Port :");
		lblWelcnmeToThe.setBounds(18, 94, 111, 22);
		panel_1.add(lblWelcnmeToThe);
		lblWelcnmeToThe.setForeground(new Color(139, 0, 0));
		lblWelcnmeToThe.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		
		JLabel label_1 = new JLabel("Server IP :");
		label_1.setForeground(new Color(139, 0, 0));
		label_1.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		label_1.setBounds(18, 37, 93, 22);
		panel_1.add(label_1);
		
		txtser_ip = new JTextField();
		txtser_ip.setBounds(10, 58, 111, 25);
		panel_1.add(txtser_ip);
		txtser_ip.setColumns(10);
		
		txtser_port = new JTextField();
		txtser_port.setColumns(10);
		txtser_port.setBounds(10, 114, 111, 25);
		panel_1.add(txtser_port);
		
		JLabel lblClientIp = new JLabel("Client Name:");
		lblClientIp.setForeground(new Color(139, 0, 0));
		lblClientIp.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblClientIp.setBounds(18, 160, 111, 22);
		panel_1.add(lblClientIp);
		
		JLabel lblClientPort = new JLabel("Client IP :");
		lblClientPort.setForeground(new Color(139, 0, 0));
		lblClientPort.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblClientPort.setBounds(18, 227, 93, 22);
		panel_1.add(lblClientPort);
		
		txtclient_ip = new JTextField();
		txtclient_ip.setColumns(10);
		txtclient_ip.setBounds(10, 248, 111, 25);
		panel_1.add(txtclient_ip);
		
		txtclient_Name = new JTextField();
		txtclient_Name.setColumns(10);
		txtclient_Name.setBounds(10, 181, 111, 25);
		panel_1.add(txtclient_Name);
		
		  panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(Color.MAGENTA, 3, true));
		panel_2.setBackground(new Color(255, 222, 173));
		panel_2.setBounds(166, 65, 358, 314);
		panel.add(panel_2);
		
		JLabel lblEnterTheMessage = new JLabel("Enter the Message:");
		lblEnterTheMessage.setForeground(new Color(139, 0, 0));
		lblEnterTheMessage.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblEnterTheMessage.setBounds(10, 87, 148, 22);
		panel_2.add(lblEnterTheMessage);
		
		JLabel lblReceive = new JLabel("Receiver Name");
		lblReceive.setForeground(new Color(139, 0, 0));
		lblReceive.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblReceive.setBounds(18, 11, 111, 22);
		panel_2.add(lblReceive);
		
		txt_receiver = new JTextField();
		txt_receiver.setColumns(10);
		txt_receiver.setBounds(158, 12, 168, 25);
		panel_2.add(txt_receiver);
		
		JLabel lblChooseTheVideo = new JLabel("Choose the Video:");
		lblChooseTheVideo.setForeground(new Color(139, 0, 0));
		lblChooseTheVideo.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblChooseTheVideo.setBounds(10, 170, 137, 22);
		panel_2.add(lblChooseTheVideo);
		
		txt_video = new JTextField();
		txt_video.setColumns(10);
		txt_video.setBounds(158, 171, 111, 25);
		panel_2.add(txt_video);
		
		final JTextPane txtar_msg = new JTextPane();
		txtar_msg.setBounds(158, 54, 168, 91);
		panel_2.add(txtar_msg);
		
		JButton browse = new JButton("Browse");
		browse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 
				
				JFileChooser jf=new JFileChooser();
				jf.showOpenDialog(null);
				
				File f=jf.getSelectedFile();   
			    filename=f.getName();
				path=f.getPath();
				txt_video.setText(path);
				System.out.println("Video Name is  ::  "+filename+"\nPATH is  ::  "+path);
			
				
				byte b[]=null;
				try
				{
				FileInputStream fis=new FileInputStream(path);   
				b = new byte[fis.available()];
				fis.read(b);
				File choose_videopath=new File("D:\\Robust video\\"+filename);
				FileOutputStream fout=new FileOutputStream(choose_videopath);
				fout.write(b);
				uploadlocation=choose_videopath.getPath();
				System.out.println("upload video location  "+uploadlocation);
				URL videoUrl=null;
				videoUrl = jf.getSelectedFile().toURI().toURL();
				System.out.println("videoUrl  is  ::"+videoUrl);
				MediaPlayer mediaPanel = new MediaPlayer();
				mediaPanel.playVideo(videoUrl);
				upvideo.setVisible(true);
				}
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		browse.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		browse.setForeground(new Color(0, 0, 255));
		browse.setBounds(270, 170, 78, 25);
		panel_2.add(browse);
		
		  upvideo = new JLabel("Video Upload Successfully");
		upvideo.setForeground(Color.BLUE);
		upvideo.setFont(new Font("Vijaya", Font.ITALIC, 20));
		upvideo.setBounds(68, 207, 232, 22);
		panel_2.add(upvideo);
		
		JButton btn_split = new JButton("Split_Video");
		btn_split.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowSplited_video frame = new ShowSplited_video("D:\\Robust video\\"+filename );
				frame.call();
				frame.setVisible(true);
				
			}
		});
		btn_split.setForeground(Color.BLUE);
		btn_split.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btn_split.setBounds(18, 240, 111, 31);
		panel_2.add(btn_split);
		
		JButton btnEncrypttext = new JButton("Encrypt_Text");
		btnEncrypttext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  String Msg=txtar_msg.getText();
				try {
					System.out.println("MEssage  :  "+txtar_msg.getText());
					String encrpt=Encryption.encrypt(txtar_msg.getText());
					System.out.println("Encrypted MEssage  :  "+ encrpt);
				    list_encrp.add("Encrypted Message ::");
				    list_encrp.add("====================");
					list_encrp.add(encrpt);
					panel_Encer.setVisible(true);
			          
					 Socket s;
					  ArrayList al=new ArrayList();
					  al.add("Message");
					  al.add(uname);
					  al.add(uploadlocation);
					  al.add(Msg);
					  al.add(encrpt);
					  al.add(ReceiverName);
					
					  	System.out.println("ip::"+server_ip+"------"+server_port);
						s=new Socket(server_ip,Integer.parseInt(server_port));
						 ObjectOutputStream os=new ObjectOutputStream(s.getOutputStream());
				          os.writeObject(al);
				 
				} catch (Exception e1) {
					 
					e1.printStackTrace();
				}
			}
		});
		btnEncrypttext.setForeground(Color.BLUE);
		btnEncrypttext.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnEncrypttext.setBounds(168, 240, 111, 31);
		panel_2.add(btnEncrypttext);
		
		JButton online_Status = new JButton("Online_Client");
		online_Status.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList loginstatus=new ArrayList();
				ArrayList result=new ArrayList();
				loginstatus.add("onlinestatus");
				Socket s;
				
				try {
					s = new Socket( server_ip,Integer.parseInt(server_port));
					 ObjectOutputStream os=new ObjectOutputStream(s.getOutputStream());
		                os.writeObject(loginstatus);
		                
		        ObjectInputStream oin=new ObjectInputStream(s.getInputStream());
		        result=(ArrayList) oin.readObject();
               
		        System.out.println(result);
		        System.out.println(result.size());
		                for(int i=0;i<result.size();i++)
		                {
		                	System.out.println(result.get(i));
		                	onlineuser.add((String) result.get(i));
		                	
		                }
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		online_Status.setForeground(Color.BLUE);
		online_Status.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		online_Status.setBounds(534, 65, 114, 31);
		panel.add(online_Status);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(138, 43, 226));
		panel_3.setBounds(534, 107, 114, 207);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		 onlineuser = new List();
		 onlineuser.setForeground(new Color(255, 0, 0));
		 onlineuser.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		  ReceiverName=onlineuser.getSelectedItem();
		 		  txt_receiver.setText(ReceiverName);
		 		  
		 	}
		 });
		 onlineuser.setBounds(10, 20, 95, 176);
		 panel_3.add(onlineuser);
		 
		 JLabel label = new JLabel("Reversible Data Hiding in Encrypted JPEG Bitstreams");
		
		 label.setBounds(125, 11, 430, 22);
		 panel.add(label);
		 label.setForeground(Color.BLUE);
		 label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		 
		  panel_Encer = new JPanel();
		  panel_Encer.setBorder(new LineBorder(new Color(255, 0, 255), 4, true));
		  panel_Encer.setBounds(282, 390, 242, 101);
		  panel.add(panel_Encer);
		  panel_Encer.setLayout(null);
		  panel_Encer.setBackground(new Color(255, 228, 181));
		  
		   list_encrp = new List();
		   list_encrp.setBounds(20, 20, 201, 66);
		   panel_Encer.add(list_encrp);
		txtser_ip.enable(false);
		txtser_port.enable(false);
		
				
	}
}

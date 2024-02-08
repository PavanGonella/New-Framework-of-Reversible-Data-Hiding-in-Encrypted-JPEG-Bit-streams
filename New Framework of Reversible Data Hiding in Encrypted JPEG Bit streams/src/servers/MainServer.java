package servers;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import databaseConnect.DbConnection;

public class MainServer extends JFrame implements Runnable {

	private JPanel contentPane;
 String ipadd,videopath, Empmsg,user,receiver;
 String  svpath=null;
 Socket s;
 ServerSocket ss;
 ArrayList Online_user=new ArrayList(); 

 public void run() {
		try
		{
			InetAddress ip=InetAddress.getLocalHost();
			  ipadd=ip.getHostAddress();
			ss =new ServerSocket(10001);
			while(true)
			{
				System.out.println("New Framework of Reversible Data Hiding in Encrypted JPEG Bitstreams ---> Server waiting.......!!!\nServer Ip Address :  "+ipadd+"    Server Prot No : 10001\n");		        
		        s = ss.accept();
		       
		       System.out.println(" Server Running ........!!!"); 
		      
		       	InputStream in=s.getInputStream();
		        ObjectInputStream out=new ObjectInputStream(in);
		        ArrayList li=(ArrayList)out.readObject();
		        Iterator ite= li.iterator();                                                                                                     
		       
		        while(ite.hasNext())
		        {
		        	String service=ite.next().toString().trim();
		        	 
		         if(service.equals("Login"))
		         {
		        	 String uname=ite.next().toString().trim();
		        	 String pass=ite.next().toString().trim();
		        	
		        	 String result=DbConnection.loginValid(uname,pass);
		        	 ObjectOutputStream op=new ObjectOutputStream(s.getOutputStream());
		        	 op.writeObject(result);
		        	 if(result.equalsIgnoreCase("Valid"))
		        	 {
		        		Online_user.add(uname); 
		        	 }
		         }
		         else if(service.equals("Register"))
		        	{
		        	String  name= ite.next().toString().trim();
		        	String pass=ite.next().toString().trim();
					String gender=ite.next().toString().trim();
		        	String phno=ite.next().toString().trim();
		        	String email=ite.next().toString().trim();
		        	System.out.println(name+pass+email+phno+gender);
		        	 DbConnection db=new DbConnection();
					int res=db.InsertValue(name,pass ,gender,phno,email );
					ObjectOutputStream op=new ObjectOutputStream(s.getOutputStream());
					op.write(res)	;	
					}
		         else if(service.equalsIgnoreCase("onlinestatus"))
		         {
		        	 ObjectOutputStream op=new ObjectOutputStream(s.getOutputStream());
		        	 op.writeObject(Online_user);
		         }
		         
		         else if(service.equalsIgnoreCase("ipinformation"))
		         {
		        		String  cname= ite.next().toString().trim();
			        	String cip=ite.next().toString().trim();
						String cport=ite.next().toString().trim();
						DbConnection db=new DbConnection();
						int i=db.insertIpPort(cname, cip, cport);
						System.out.println("mainserver value updated database  "+i);
			         
		         }
		         else if(service.equalsIgnoreCase("SplitVPath"))
		         {
		        	 svpath= ite.next().toString().trim();
			        	
						DbConnection db=new DbConnection();
						int i=db.InsertSplitVPath(svpath);
		         }
		         else if(service.equalsIgnoreCase("GetSVPath"))
		         {
		        	 ObjectOutputStream op=new ObjectOutputStream(s.getOutputStream());
		        	 op.writeObject(svpath);
		        	 System.out.println("Split vidoe paht in serverside  "+svpath);
		         }
		         
		         else if(service.equalsIgnoreCase("message"))
		         {
		        	     user= ite.next().toString().trim();
		        	    videopath= ite.next().toString().trim();
		        	    String  msg= ite.next().toString().trim();
			        	 Empmsg=ite.next().toString().trim();
			        	 receiver= ite.next().toString().trim();
						DbConnection db=new DbConnection();
					int i=db.message(user,videopath,msg,Empmsg,receiver);
						//System.out.println("mainserver value updated database  "+i);
			         
		         }
		         else if(service.equalsIgnoreCase("GetEmpMessage"))
		         {
		        	 
		        	 ArrayList empmsg=new ArrayList();
		        	 empmsg=DbConnection.getIPaddPort(receiver);
		        	 empmsg.add(user);
		        	 empmsg.add(Empmsg);
		        	 empmsg.add(videopath);
		        	 empmsg.add(receiver);
		        	
		        	 ObjectOutputStream op=new ObjectOutputStream(s.getOutputStream());
		        	 op.writeObject(empmsg);
		        	 System.out.println("MainServer Side al value in GemEmpMess me3htod.."+empmsg);
		        	 
		         }
		         
		         else
		         {
		        	 JOptionPane.showMessageDialog(rootPane, "Does not match any one of the service");
		         }
		        	
		         
		        }
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainServer frame = new MainServer();
					frame.setVisible(false);
					frame.callThread();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void callThread() {
		Thread t=new Thread(this);
		t.start();
		
	}

	/**
	 * Create the frame.
	 */
	public MainServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 424, 251);
		contentPane.add(panel);
	}
	
	
}

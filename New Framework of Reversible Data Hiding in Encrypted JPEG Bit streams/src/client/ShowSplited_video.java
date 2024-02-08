package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowSplited_video extends JFrame {

	private JPanel contentPane;
	JPanel panel_splitvideo;
	JLabel video[]=new JLabel[20];
	static String VideoPath;
	 String svpath=null,framepath;
	String vpath=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ShowSplited_video frame = new ShowSplited_video(VideoPath);
					frame.call();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void split( )
	{ 
	}
public void call()
{
	panel_splitvideo.setVisible(false);
	
}
	/**
	 * Create the frame.
	 * @param videopath 
	 */
	public ShowSplited_video(String videopath) {
		vpath=videopath;
		JOptionPane.showMessageDialog(rootPane, vpath);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,900, 568);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 218, 185));
		panel.setBounds(0, 0, 884, 530);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 92, 242, 291);
		panel_1.setBorder(new LineBorder(new Color(255, 0, 0), 3));
		panel_1.setBackground(new Color(221, 160, 221));
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("hi...................");
				URL videoUrl=null;
				File choose_videopath=new File(vpath);
				try {
					videoUrl = choose_videopath.toURI().toURL();
					MediaPlayer mp=new MediaPlayer();
					mp.playVideo(videoUrl);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		});
		lblNewLabel.setIcon(new ImageIcon("D:\\2018 Major Work Space\\VTJIM02-2018\\images\\full video.jpg"));
		lblNewLabel.setBounds(10, 56, 208, 186);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("PLAY FULL VIDEO");
		lblNewLabel_1.setForeground(new Color(173, 255, 47));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setBounds(38, 11, 165, 36);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblSplit = new JLabel("Split");
		lblSplit.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
			panel_splitvideo.setVisible(true);
			 Video_Split vs=new Video_Split(vpath);
			 System.out.println("video Split Successfully..!!!!"+vs);
		
			 Socket client; 
	         ArrayList al=new ArrayList();
	         al.add("GetSVPath");
	         
	         try {
				client=new Socket("localhost",10001);
				 ObjectOutputStream out=new ObjectOutputStream(client.getOutputStream());
		         out.writeObject(al);
		         ObjectInputStream oin=new ObjectInputStream(client.getInputStream());
		          svpath=oin.readObject().toString();
		         System.out.println("SplitVideoPath"+svpath);
			} catch (UnknownHostException e2) {
				e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
			
			int x=50,y=50;
			for(int i=1;i<=6;i++)
		{
				JLabel lbl=new JLabel(new ImageIcon("D:\\2018 Major Work Space\\VTJIM02-2018\\images\\Splited video SM.jpg"));
				video[i]=lbl;
				video[i].setText("Video_"+i);
				final int j=i;
				System.out.println("path----------------"+svpath);
				
				final String SVPath=svpath;
				//final String SVPath="D:\\Robust video\\Splited video\\MS";
				//svpath="D:\\Robust video\\Splited video\\MS";
				video[i].addMouseListener(new MouseAdapter() {
				
					public void mouseClicked(MouseEvent e) {
						System.out.println("mouse"+j);
						URL videoUrl=null;
						File choose_videopath=new File(SVPath+"\\video"+j+".mpg");
						try {
							videoUrl = choose_videopath.toURI().toURL();
							MediaPlayer mp=new MediaPlayer();
							mp.playVideo(videoUrl);
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
					 
				video[i].setBounds( x,y, 208, 186);
				video[i].setVisible(true);
				panel_splitvideo.add(video[i]);
				
				System.out.println("dsfdsfdsf");
				x=x+150;
				if(i==3)
				{
				x=50;
				y=y+200;
				}
				
		 }
				
			}
		});
		lblSplit.setForeground(new Color(173, 255, 47));
		lblSplit.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblSplit.setBounds(88, 244, 64, 36);
		panel_1.add(lblSplit);
		
	 panel_splitvideo = new JPanel();
	 panel_splitvideo.setBounds(293, 92, 560, 427);
		panel_splitvideo.setBorder(new LineBorder(new Color(128, 0, 128), 3, true));
		panel.add(panel_splitvideo);
		panel_splitvideo.setLayout(null);
		
		JLabel lblSplitedVideos = new JLabel("Splited Video's");
		lblSplitedVideos.setForeground(Color.MAGENTA);
		lblSplitedVideos.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		lblSplitedVideos.setBounds(210, 11, 164, 36);
		panel_splitvideo.add(lblSplitedVideos);
		
		JLabel lblNewLabel_2 = new JLabel("Choose Video to Split Frame");
		lblNewLabel_2.setBounds(10, 420, 155, 32);
		panel.add(lblNewLabel_2);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectItem=comboBox.getSelectedItem().toString();
				
				  framepath=svpath+"\\"+selectItem+".mpg";
				  System.out.println("selected svideo"+framepath);
				 
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "video1", "video2", "video3", "video4", "video5", "video6"}));
		comboBox.setBounds(179, 426, 73, 20);
		panel.add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Video_to_Frames vf=new Video_to_Frames( framepath);
				Video_to_Frames vf=new Video_to_Frames(framepath);
				
			}
		});
		btnSubmit.setBounds(76, 463, 89, 23);
		panel.add(btnSubmit);
		
		 
	}
}




















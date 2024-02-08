package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.List;
import javax.swing.JButton;

import ABE.Encryption;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JTextField;
import java.awt.Button;

public class Receiver extends JFrame {

	private JPanel contentPane;
	JPanel panel_1;
	JLabel lblreceiver;
	JLabel lblFrameNo;
	JLabel lblselimg;
	JButton btn_Encrpt;
	Button btn_decrypt;
	List list_frames;
	List list_msg;
	 String VstroePath=null;
	 String frameNo;
	 String EmpMsg;
static ArrayList al;
ArrayList result=new ArrayList();
private JTextField txt_enmsg;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receiver frame = new Receiver(al);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void getValue() {
		String sender=result.get(0).toString();
		String receiver=result.get(1).toString();
		  EmpMsg=result.get(2).toString();
		 byte[] Video=(byte[])result.get(3); 
		 frameNo=result.get(4).toString();
		VstroePath="D:\\Receiver\\video.mpg";
	File F=new File(VstroePath);
	try {
		 FileOutputStream fos=new FileOutputStream(F);		 
		fos.write(Video);
		fos.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	lblreceiver.setText(sender);
	lblFrameNo.setText(frameNo);
	btn_Encrpt.setVisible(false);
	btn_decrypt.setVisible(false);
	panel_1.setVisible(false);
	}
	
	/**
	 * Create the frame.
	 * @param res 
	 */
	public Receiver(ArrayList res) {
		result=res;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 714);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Received From ::");
		lblNewLabel.setForeground(new Color(0, 0, 255));
		lblNewLabel.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		lblNewLabel.setBounds(114, 28, 173, 35);
		contentPane.add(lblNewLabel);
		
		  lblreceiver = new JLabel(" ");
		lblreceiver.setForeground(Color.BLUE);
		lblreceiver.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		lblreceiver.setBounds(271, 28, 173, 35);
		contentPane.add(lblreceiver);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(128, 0, 0), 3));
		panel.setBounds(46, 86, 658, 251);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\2018 Major Work Space\\VTJIM02-2018\\images\\Splited video SM.jpg"));
		lblNewLabel_1.setBounds(33, 57, 121, 120);
		panel.add(lblNewLabel_1);
		
		JLabel lblYourMessage = new JLabel("Your Message");
		lblYourMessage.setForeground(new Color(255, 0, 255));
		lblYourMessage.setFont(new Font("Monotype Corsiva", Font.ITALIC, 24));
		lblYourMessage.setBounds(213, 11, 173, 35);
		panel.add(lblYourMessage);
		
		list_frames = new List();
		list_frames.addMouseListener(new MouseAdapter() {
		
			public void mouseClicked(MouseEvent e) {
				
				 BufferedImage image;
				 String path="D:\\Receiver\\Frames\\video\\"+frameNo;
					 
						 	try {
								image = ImageIO.read(new File(path));
								Graphics g = image.getGraphics();
						 	    g.setFont(g.getFont().deriveFont(15f));
						 	    g.drawString(EmpMsg, 50, 150);
						 	    g.dispose();
						 	    ImageIO.write(image, "jpg", new File(path));
						 	   
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						 	
						 	 lblselimg.setIcon(new ImageIcon( path));
								 
				 btn_Encrpt.setVisible(true);
				 
				
			}
		});
		list_frames.setBounds(434, 39, 173, 202);
		panel.add(list_frames);
		
		JLabel lblFrames = new JLabel("Total Frames ::");
		lblFrames.setForeground(Color.BLUE);
		lblFrames.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		lblFrames.setBounds(446, 11, 173, 35);
		panel.add(lblFrames);
		
		JButton btnNewButton = new JButton("Play Video");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				URL videoUrl=null;
				File choose_videopath=new File( VstroePath);
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
		btnNewButton.setForeground(new Color(0, 0, 255));
		btnNewButton.setBounds(183, 58, 101, 35);
		panel.add(btnNewButton);
		
		JButton btnGenerateFrame = new JButton("Split Frame");
		btnGenerateFrame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 Receiver_splitVideo rs=new Receiver_splitVideo(VstroePath);
				for(int i=1;i< 1021;i++)
				{
					list_frames.addItem("frame_"+i+".jpg");
				}
			}
		});
		btnGenerateFrame.setForeground(Color.BLUE);
		btnGenerateFrame.setBounds(183, 115, 101, 35);
		panel.add(btnGenerateFrame);
		
		JLabel lblEncryptedFramenoIs = new JLabel("Encrypted FrameNo is::");
		lblEncryptedFramenoIs.setForeground(Color.BLUE);
		lblEncryptedFramenoIs.setFont(new Font("Monotype Corsiva", Font.PLAIN, 18));
		lblEncryptedFramenoIs.setBounds(33, 188, 173, 41);
		panel.add(lblEncryptedFramenoIs);
		
	    lblFrameNo = new JLabel(" ");
		lblFrameNo.setForeground(Color.BLUE);
		lblFrameNo.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		lblFrameNo.setBounds(193, 188, 111, 35);
		panel.add(lblFrameNo);
		
		lblselimg = new JLabel(" ");
		lblselimg.setBounds(10, 369, 328, 265);
		contentPane.add(lblselimg);
		
		btn_Encrpt = new JButton("Get Encrpt_Mesg");
		btn_Encrpt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 btn_decrypt.setVisible(true);
				 panel_1.setVisible(true);
				 txt_enmsg.setText(EmpMsg);
				
			}
		});
		btn_Encrpt.setBounds(348, 380, 115, 35);
		contentPane.add(btn_Encrpt);
		
	    panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(128, 0, 0), 3, true));
		panel_1.setBounds(492, 369, 212, 251);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEncryptedMessage = new JLabel("Encrypted Message");
		lblEncryptedMessage.setForeground(Color.BLUE);
		lblEncryptedMessage.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		lblEncryptedMessage.setBounds(10, 11, 173, 35);
		panel_1.add(lblEncryptedMessage);
		
		txt_enmsg = new JTextField();
		txt_enmsg.setBounds(10, 57, 163, 35);
		panel_1.add(txt_enmsg);
		txt_enmsg.setColumns(10);
		
		JLabel lblOriginalMessage = new JLabel("Original Message");
		lblOriginalMessage.setForeground(Color.BLUE);
		lblOriginalMessage.setFont(new Font("Monotype Corsiva", Font.PLAIN, 24));
		lblOriginalMessage.setBounds(10, 108, 173, 35);
		panel_1.add(lblOriginalMessage);
		
		list_msg = new List();
		list_msg.setBounds(20, 144, 173, 97);
		panel_1.add(list_msg);
		
		btn_decrypt = new Button("Reterive");
		btn_decrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
						String decrypt=Encryption.decrypt(EmpMsg);
						list_msg.add(decrypt);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
		btn_decrypt.setBounds(348, 449, 115, 35);
		contentPane.add(btn_decrypt);
		JOptionPane.showMessageDialog(rootPane, "Message received!!!");
		getValue();
		
	}
}

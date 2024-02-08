package client;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeSet;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.List;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import watermarking.watermarking;


public class Show_Frames extends JFrame {

	private JPanel contentPane;
	int i=1;
	JLabel lbl_img;
	String path;
	int count_frames;
	List list;
	List list_1 ;
	String selectFrameno;
	/**
	 * File fpath,int nof;
	 * 
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					/*Show_Frames frame = new Show_Frames();
					frame.setVisible(true);*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	 
	String s_name;
	public Show_Frames(File fpath,int nof) 
	{
		path=fpath.toString();
		System.out.println("The path is"+path);
		s_name=fpath.getName();
		count_frames=nof;
		System.out.println("frame path is ::: "+path+"Total frames  :: "+count_frames);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 676, 672);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 235, 205));
		panel.setBounds(10, 11, 645, 629);
		contentPane.add(panel);
		
		lbl_img= new JLabel("");
		lbl_img.setBounds(49, 43, 542, 304);
		lbl_img.setIcon(new ImageIcon(path+"\\frame_"+i+".jpg"));
		lbl_img.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		panel.setLayout(null);
		panel.add(lbl_img);
		
		JButton btnNewButton = new JButton("Previous");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBounds(321, 347, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(i>1)
		        {
		            i--;
		            lbl_img.setIcon(new ImageIcon(path+"\\frame_"+i+".jpg"));
		        }
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNext.setBounds(472, 347, 89, 23);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(i<count_frames)
		        {
		            i++;
		            lbl_img.setIcon(new ImageIcon(path+"\\frame_"+i+".jpg"));
		        }
			}
		});
		panel.add(btnNext);
		
		JLabel lblNewLabel = new JLabel("Total Frames");
		lblNewLabel.setBounds(198, 11, 184, 34);
		lblNewLabel.setFont(new Font("Vani", Font.BOLD, 26));
		lblNewLabel.setForeground(new Color(0, 100, 0));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(255, 0, 0), 4, true));
		panel_1.setBackground(new Color(255, 228, 225));
		panel_1.setBounds(10, 381, 172, 237);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		 list= new List();
		 list.setBounds(22, 58, 125, 156);
		 panel_1.add(list);
		 list.setFont(new Font("Times New Roman", Font.BOLD, 12));
		 list.setForeground(new Color(218, 165, 32));
		 list.setBackground(new Color(255, 250, 250));
		 
		 JLabel lblListOfFrames = new JLabel("List of Frames");
		 lblListOfFrames.setForeground(new Color(0, 0, 255));
		 lblListOfFrames.setFont(new Font("Times New Roman", Font.BOLD, 16));
		 lblListOfFrames.setBounds(22, 23, 125, 29);
		 panel_1.add(lblListOfFrames);
		 
		 JPanel panel_2 = new JPanel();
		 panel_2.setLayout(null);
		 panel_2.setBorder(new LineBorder(new Color(255, 0, 0), 4, true));
		 panel_2.setBackground(new Color(255, 228, 225));
		 panel_2.setBounds(344, 381, 197, 237);
		 panel.add(panel_2);
		 
		 list_1= new List();
		 list_1.addMouseListener(new MouseAdapter() {
		 	
		 	public void mouseClicked(MouseEvent e) {
		 		selectFrameno=list_1.getSelectedItem();
		 		System.out.println("choosed frame no "+selectFrameno);
		 	}
		 });
		 list_1.setBounds(42, 61, 120, 115);
		 panel_2.add(list_1);
		 list_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		 list_1.setForeground(new Color(0, 0, 255));
		 list_1.setBackground(new Color(255, 255, 255));
		 
		 JButton EmbText = new JButton("Embeded Text");
		 EmbText.setBounds(42, 182, 120, 40);
		 panel_2.add(EmbText);
		 EmbText.setFont(new Font("Times New Roman", Font.BOLD, 14));
		 
		 JLabel lblNewLabel_1 = new JLabel("User Selected Frames");
		 lblNewLabel_1.setBounds(24, 23, 163, 22);
		 panel_2.add(lblNewLabel_1);
		 lblNewLabel_1.setForeground(new Color(72, 61, 139));
		 lblNewLabel_1.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 16));
		 EmbText.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		
		 		watermarking frame = new watermarking(path+"\\"+selectFrameno);
				frame.setVisible(true);
		 	}
		 });
		 list_1.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
		 String frame_1=list_1.getSelectedItem();
		 //JOptionPane.showInternalConfirmDialog(null,"please choose one", "information",	JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		 list_1.remove(frame_1);
		 }});
		 list.addActionListener(new ActionListener() {
		 public void actionPerformed(ActionEvent e) {
		 String frame=list.getSelectedItem();
		 list_1.addItem(frame);
		 lbl_img.setIcon(new ImageIcon(path+"\\"+frame));
		 }
		 });
		for(int i=1;i<count_frames;i++)
		{
			list.addItem("ff.jpg");
		}
	}
}


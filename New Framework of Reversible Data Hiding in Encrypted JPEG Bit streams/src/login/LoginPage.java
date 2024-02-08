package login;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import  client.HomePage;
public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField txt_uname;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 464, 325);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 24));
		lblNewLabel.setBounds(171, 23, 140, 36);
		panel.add(lblNewLabel);
		
		JLabel lblEnterUserName = new JLabel("Enter User Name ::");
		lblEnterUserName.setBackground(Color.WHITE);
		lblEnterUserName.setForeground(new Color(51, 255, 51));
		lblEnterUserName.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 18));
		lblEnterUserName.setBounds(34, 93, 210, 36);
		panel.add(lblEnterUserName);
		
		JLabel lblEnterPassword = new JLabel("Enter Password ::");
		lblEnterPassword.setBackground(Color.MAGENTA);
		lblEnterPassword.setForeground(new Color(51, 255, 51));
		lblEnterPassword.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 18));
		lblEnterPassword.setBounds(44, 140, 181, 36);
		panel.add(lblEnterPassword);
		
		txt_uname = new JTextField();
		txt_uname.setBounds(274, 100, 128, 26);
		panel.add(txt_uname);
		txt_uname.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList al =new ArrayList();
				Socket s;
				String Result;
				String uname=txt_uname.getText();
				String pass=passwordField.getText();
				al.add("Login");
				al.add(uname);
				al.add(pass);
				try{
					s=new Socket("localhost",10001);
					ObjectOutputStream ob=new ObjectOutputStream(s.getOutputStream());
					ob.writeObject(al);
					
					ObjectInputStream oin=new ObjectInputStream(s.getInputStream());
					Result=(String) oin.readObject().toString().trim();
					System.out.println("result   "+Result);
					
					if(Result.equalsIgnoreCase("valid"))
					{
						JOptionPane.showMessageDialog(rootPane, "Valid User");
						HomePage frame = new HomePage(uname);
						frame.callThread();
						frame.setVisible(true);
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(rootPane, "InValid User");
					}
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				System.out.println("UserName and Password     "+uname+"    "+pass);
			}
		});
		btnSubmit.setForeground(Color.BLUE);
		btnSubmit.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 16));
		btnSubmit.setBounds(151, 197, 94, 36);
		panel.add(btnSubmit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(274, 147, 128, 26);
		panel.add(passwordField);
		
		JLabel lblNewUserPls = new JLabel("New User Pls Register Here ....!!!   ");
		lblNewUserPls.setForeground(new Color(51, 255, 51));
		lblNewUserPls.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 18));
		lblNewUserPls.setBackground(Color.MAGENTA);
		lblNewUserPls.setBounds(34, 244, 277, 36);
		panel.add(lblNewUserPls);
		
		JButton btnSignup = new JButton("SignUp");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register frame = new Register();
				frame.setVisible(true);
			}
		});
		btnSignup.setForeground(Color.BLUE);
		btnSignup.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 16));
		btnSignup.setBounds(313, 247, 89, 32);
		panel.add(btnSignup);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_uname.setText("");
				passwordField.setText("");
			}
		});
		btnCancel.setForeground(Color.BLUE);
		btnCancel.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 16));
		btnCancel.setBounds(274, 197, 94, 36);
		panel.add(btnCancel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\2018 Major Work Space\\VTJIM02-2018\\images\\images (2).jpg"));
		lblNewLabel_1.setBounds(0, 0, 478, 325);
		panel.add(lblNewLabel_1);
	}
}

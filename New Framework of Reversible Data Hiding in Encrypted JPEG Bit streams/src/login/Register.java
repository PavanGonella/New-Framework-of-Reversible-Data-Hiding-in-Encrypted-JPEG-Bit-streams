package login;

 

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class Register extends JFrame {
	JRadioButton Female = new JRadioButton("Female");
	JRadioButton Male = new JRadioButton("Male");
	private JPanel contentPane;
	private JTextField txtname;
	private JPasswordField txtpass;
	private JPasswordField txtcpass;
	private JTextField txtemail;
	private JTextField txtphno;
	String gender;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 389, 575);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 182, 193));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 228, 225));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 379, 675);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setForeground(Color.BLUE);
		lblName.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblName.setBounds(41, 134, 82, 24);
		panel.add(lblName);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblPassword.setBounds(27, 189, 82, 24);
		panel.add(lblPassword);
		
		JLabel lblConformPassword = new JLabel("Conform Password :");
		lblConformPassword.setForeground(Color.BLUE);
		lblConformPassword.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblConformPassword.setBounds(27, 244, 113, 24);
		panel.add(lblConformPassword);
		
		JLabel lblSex = new JLabel("Gender :");
		lblSex.setForeground(Color.BLUE);
		lblSex.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblSex.setBounds(27, 296, 113, 24);
		panel.add(lblSex);
		
		JLabel lblEmailId = new JLabel("Email Id:");
		lblEmailId.setForeground(Color.BLUE);
		lblEmailId.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblEmailId.setBounds(27, 355, 113, 24);
		panel.add(lblEmailId);
		
		JLabel lblPhNo = new JLabel("Ph No :");
		lblPhNo.setForeground(Color.BLUE);
		lblPhNo.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		lblPhNo.setBounds(27, 405, 113, 24);
		panel.add(lblPhNo);
		
	
		JButton Submit = new JButton("SignUp");
		Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,pass,cpass, email,phno,gen  ;
			 
				name=txtname.getText();
				pass=txtpass.getText();
				cpass=txtcpass.getText();
				email=txtemail.getText();
				phno=txtphno.getText();
				
				if(Male.isSelected())
				{
					gender="Male";
				}
				else if(Female.isSelected())
				{
					gender="Female";
				}
				gen=gender;
				System.out.println(name+pass+email+phno+gen);
			
				if(name.equals("")||pass.equals("")||cpass.equals("") ||email.equals("")||phno.equals(""))
			{
				JOptionPane.showMessageDialog(rootPane, "All Field are Mandatory pls Enter Value");
			}
			else
			{
				ArrayList al =new ArrayList();
				al.add("Register");
				al.add(name);
				al.add(pass);
				al.add(gen);
				al.add(phno);
				al.add(email);				
				 
				
				Socket s;
				Integer result;
				try {
					s = new Socket("localhost",10001);
					 ObjectOutputStream os=new ObjectOutputStream(s.getOutputStream());
		                os.writeObject(al);
		                ObjectInputStream oin=new ObjectInputStream(s.getInputStream());
		                result=(Integer)oin.readObject();
					if(result.equals(new Integer(1)))
					{
						JOptionPane.showMessageDialog(rootPane, "Register Successfully");
						dispose();
						LoginPage frame = new LoginPage();
						frame.setVisible(true);
					 
					}
					else if(result.equals(new Integer(0)))
					{
						JOptionPane.showMessageDialog(rootPane, "Register Failed pls tryagain");
					}
				}  catch (Exception e1) {
					e1.printStackTrace();
				}
			}
				
			}
		});
		Submit.setForeground(Color.BLUE);
		Submit.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		Submit.setBounds(27, 473, 113, 44);
		panel.add(Submit);
		
		txtname = new JTextField();
		txtname.setBounds(178, 135, 113, 22);
		panel.add(txtname);
		txtname.setColumns(10);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(178, 190, 113, 22);
		panel.add(txtpass);
		
		txtcpass = new JPasswordField();
		txtcpass.setBounds(178, 245, 113, 22);
		panel.add(txtcpass);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(178, 355, 113, 22);
		panel.add(txtemail);
		
		txtphno = new JTextField();
		txtphno.setColumns(10);
		txtphno.setBounds(178, 406, 113, 22);
		panel.add(txtphno);
		
		
		Male.setHorizontalAlignment(SwingConstants.LEFT);
		Male.setBounds(178, 297, 76, 23);
		panel.add(Male);
		
		
		Female.setHorizontalAlignment(SwingConstants.LEFT);
		Female.setBounds(264, 297, 98, 23);
		panel.add(Female);
		ButtonGroup bg=new ButtonGroup();
		bg.add(Male);
		bg.add(Female);
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\2018 Major Work Space\\VTJIM02-2018\\images\\reg.jpg"));
		lblNewLabel.setBounds(27, 11, 335, 111);
		panel.add(lblNewLabel);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtname.setText("");
				txtpass.setText("");
				txtcpass.setText("");
				txtphno.setText("");
				txtemail.setText("");
			}
		});
		btnClear.setForeground(Color.BLUE);
		btnClear.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 14));
		btnClear.setBounds(178, 473, 113, 44);
		panel.add(btnClear);
	}
}

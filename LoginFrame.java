package bookshetlter.pack;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	// Declaring components
	private ImageIcon icon;
	private GridLayout layout;
	private JPanel panel;
	
	private JLabel label_username;
	private JTextField field_username;
	private JLabel label_password;
	private JPasswordField field_password;
	private static JButton button_login;
	private static JButton button_signup;
	private static JLabel label_warning;
	
	// Initializing components
	public LoginFrame() {
		
		icon = new ImageIcon("C:\\Users\\vitor\\eclipse-workspace\\BookShelter Desktop\\src\\bookshetlter\\pack\\Logo.png");
		
		layout = new GridLayout(9, 1, 0, 2);
		panel = new JPanel(layout);
		
		
		label_username = new JLabel("Username:", SwingConstants.CENTER);
		field_username = new JTextField();
		label_password = new JLabel("Password:", SwingConstants.CENTER);
		field_password = new JPasswordField();
		button_login = new JButton("Login");
		button_signup = new JButton("Sign up");
		label_warning = new JLabel("", SwingConstants.CENTER);
		
	}
	
	// Customizing components, set buttons action and adding components to the frame
	public void initLoginFrame() {
		
		button_login.setBackground(Color.WHITE);
		button_signup.setBackground(Color.WHITE);
		
		// Login button action
		button_login.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ClickActions.loginClick(LoginFrame.this, field_username, field_password);
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		
		// Sign up button action
		button_signup.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ClickActions.signUpClick(field_username, field_password);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		panel.setBorder(new EmptyBorder(0, 4, 0, 4));
		
		
		// Adding components to the frame
		panel.add(new JLabel()); // Empty label
		panel.add(label_username);
		panel.add(field_username);
		panel.add(label_password);
		panel.add(field_password);
		panel.add(new JLabel()); // Empty label
		panel.add(button_login);
		panel.add(button_signup);
		panel.add(label_warning);
		
		// Set frame properties
		
		setTitle("Bookshelter - Log in");
		setIconImage(icon.getImage());
		setResizable(false);
		setContentPane(panel);
		setSize(300, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public JLabel getLabel_username() {
		return label_username;
	}
	public void setLabel_username(JLabel label_username) {
		this.label_username = label_username;
	}
	public JTextField getField_username() {
		return field_username;
	}
	public void setField_username(JTextField field_username) {
		this.field_username = field_username;
	}
	public JLabel getLabel_password() {
		return label_password;
	}
	public void setLabel_password(JLabel labelA_password) {
		this.label_password = labelA_password;
	}
	public JPasswordField getField_password() {
		return field_password;
	}
	public void setField_password(JPasswordField field_password) {
		this.field_password = field_password;
	}
	public static JButton getButton_login() {
		return button_login;
	}
	public void setButton_login(JButton button_login) {
		LoginFrame.button_login = button_login;
	}

	public JLabel getLabel_warning() {
		return label_warning;
	}
	public
	void setLabel_warning(JLabel label_warning) {
		LoginFrame.label_warning = label_warning;
	}
	

}

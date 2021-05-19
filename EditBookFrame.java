package bookshetlter.pack;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class EditBookFrame extends JFrame {
	
	// Declaring components
	private ImageIcon icon;
	private GridLayout layout;
	private JPanel panel;
	
	private static JLabel label_book_name;
	private static JTextField field_book_name;
	private static JLabel label_book_author;
	private static JTextField field_book_author;
	private static JLabel label_tot_pages;
	private static JTextField field_tot_pages;
	private static JLabel label_current_page;
	private static JTextField field_current_page;
	private static JLabel label_note;
	private static JTextField field_note;
	private static JButton button_done;
	private static JButton button_cancel;
	
	
	// Initializes components
	public EditBookFrame() {
		
		icon = new ImageIcon("C:\\Users\\vitor\\eclipse-workspace\\BookShelter Desktop\\src\\bookshetlter\\pack\\Logo.png");
		
		layout = new GridLayout(6, 2, 3, 3);
		panel = new JPanel(layout);
		
		label_book_name = new JLabel("Title:", SwingConstants.CENTER);
		field_book_name = new JTextField();
		label_book_author = new JLabel("Author:", SwingConstants.CENTER);
		field_book_author = new JTextField();
		label_tot_pages = new JLabel("Total of pages:", SwingConstants.CENTER);
		field_tot_pages = new JTextField();
		label_current_page = new JLabel("Current page:", SwingConstants.CENTER);
		field_current_page = new JTextField();
		label_note = new JLabel("Note:", SwingConstants.CENTER);
		field_note = new JTextField();
		button_done = new JButton("Done");
		button_cancel = new JButton("Cancel");
	}
	
	// Customizes components, sets buttons action and adds components to the frame
	public void initEditBookFrame() {
		
		// Sets Done button action
		button_done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ClickActions.editBookClickDone(field_book_name, field_book_author, field_tot_pages, field_current_page, field_note);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// Sets Cancel button action
		button_cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				EditBookFrame.this.setVisible(false);
				Main.appframe.setEnabled(true);
				Main.appframe.setVisible(true);
				
			}
		});
		
		
		
		// Adds components to the frame
		panel.add(label_book_name);
		panel.add(field_book_name);
		panel.add(label_book_author);
		panel.add(field_book_author);
		panel.add(label_tot_pages);
		panel.add(field_tot_pages);
		panel.add(label_current_page);
		panel.add(field_current_page);
		panel.add(label_note);
		panel.add(field_note);
		panel.add(button_done);
		panel.add(button_cancel);

		// Sets frame properties
		setTitle("Edit Book");
		setIconImage(icon.getImage());
		setResizable(false);
		setContentPane(panel);
		setSize(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);
	}
	
	// Getters and Setters
	public static JTextField getField_book_name() {
		return field_book_name;
	}
	public static JTextField getField_book_author() {
		return field_book_author;
	}
	public static JTextField getField_tot_pages() {
		return field_tot_pages;
	}

	public static JTextField getField_current_page() {
		return field_current_page;
	}

	public static JTextField getField_note() {
		return field_note;
	}
	

}

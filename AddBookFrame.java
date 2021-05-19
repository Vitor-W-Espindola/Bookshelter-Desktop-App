package bookshetlter.pack;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

@SuppressWarnings("serial")
public class AddBookFrame extends JFrame {
	
	// Declaring components
	private ImageIcon icon;
	private GridLayout layout;
	private JPanel panel;
	
	private JLabel label_book_name;
	private JTextField field_book_name;
	private JLabel label_book_author;
	private JTextField field_book_author;
	private JLabel label_tot_pages;
	private JTextField field_tot_pages;
	private JLabel label_current_page;
	private JTextField field_current_page;
	private JLabel label_note;
	private JTextField field_note;
	private JButton button_done;
	private JButton button_cancel;
	
	// Initializes components
	public AddBookFrame() {
		
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
	public void initAddBookFrame() {
		
		// Clears fields before display frame
		field_book_name.setText("");
		field_book_author.setText("");
		field_tot_pages.setText("");
		field_current_page.setText("");
		field_note.setText("");
		
		// Sets Done button action
		button_done.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ClickActions.addBookClickDone(field_book_name, field_book_author, field_tot_pages, field_current_page, field_note);
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
				AddBookFrame.this.setVisible(false);
				Main.appframe.setEnabled(true);
				Main.appframe.setVisible(true);
			}
		});
		
		
		
		// Adding components to the frame
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
		setTitle("Add Book");
		setIconImage(icon.getImage());
		setResizable(false);
		setContentPane(panel);
		setSize(300, 300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(false);
		
	}

}

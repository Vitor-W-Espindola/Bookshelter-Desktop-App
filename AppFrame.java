package bookshetlter.pack;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class AppFrame extends JFrame {
	
	// Declaring components
	private ImageIcon icon;
	private GridLayout layout_book_info;
	private JPanel panel_book_info;
	private GridLayout layout_control;
	private JPanel panel_control;
	private GridLayout layout;
	private JPanel panel;
	
	private static JLabel label_book_name;
	private static JLabel label_book_author;
	private static JLabel label_tot_pages;
	private static JLabel label_current_page;
	
	private static JLabel label_progress;
	private static JLabel label_note;
	private static JLabel label_index;
	
	private static JButton button_next;
	private static JButton button_back;
	private static JButton button_add;
	private static JButton button_edit;
	private static JButton button_remove;
	
	
	// Initializing components
	public AppFrame() {
		
		icon = new ImageIcon("C:\\Users\\vitor\\eclipse-workspace\\BookShelter Desktop\\src\\bookshetlter\\pack\\Logo.png");
		
		layout_book_info = new GridLayout(7, 1);
		panel_book_info = new JPanel(layout_book_info);
		layout_control = new GridLayout(5, 1);
		panel_control = new JPanel(layout_control);
		layout = new GridLayout(2, 1);
		panel = new JPanel(layout);

		label_book_name = new JLabel("Title: ", SwingConstants.CENTER);
		label_book_author = new JLabel("Author: ", SwingConstants.CENTER);
		label_tot_pages = new JLabel("Total of pages: ", SwingConstants.CENTER);
		label_current_page = new JLabel("Current page: ", SwingConstants.CENTER);
		label_progress = new JLabel("Progress: ", SwingConstants.CENTER);
		label_note = new JLabel("Note: ", SwingConstants.CENTER);
		label_index = new JLabel("Book 0 of 0", SwingConstants.CENTER);
		
		button_next = new JButton("Next");
		button_back = new JButton("Back");
		button_add = new JButton("Add");
		button_edit = new JButton("Edit");
		button_remove = new JButton("Remove");

		button_next.setBackground(Color.WHITE);
		button_back.setBackground(Color.WHITE);
		button_add.setBackground(Color.WHITE);
		button_edit.setBackground(Color.WHITE);
		button_remove.setBackground(Color.WHITE);
		
	}
	
	// Customizing components, set buttons action and adding components to the frame
	public void initAppFrame() {
		
		
		
		// Next button action
		button_next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ClickActions.updateAppFrame("+", label_book_name, label_book_author, label_tot_pages, label_current_page, label_progress, label_note, label_index);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// Back button action
		button_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ClickActions.updateAppFrame("-", label_book_name, label_book_author, label_tot_pages, label_current_page, label_progress, label_note, label_index);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		// Add button action
		button_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AppFrame.this.setEnabled(false);
				Main.addbookframe.setVisible(true);
			}
		});
		
		// Edit button action
		button_edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AppFrame.this.setEnabled(false);
				Main.editbookframe.setVisible(true);
			}
		});
		
		// Remove button action
		button_remove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ClickActions.removeClick();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		layout.setVgap(2);
		panel_book_info.setBackground(Color.white);
		panel_book_info.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Book Info"));
		panel_control.setBackground(Color.white);
		panel.setBackground(Color.white);
		
		
		// Adds components to the frame
		panel_book_info.add(label_book_name);
		panel_book_info.add(label_book_author);
		panel_book_info.add(label_tot_pages);
		panel_book_info.add(label_current_page);
		panel_book_info.add(label_progress);
		panel_book_info.add(label_note);
		panel_book_info.add(label_index);
		
		panel_control.add(button_next);
		panel_control.add(button_back);
		panel_control.add(button_add);
		panel_control.add(button_edit);
		panel_control.add(button_remove);

		
		panel.setBorder(new EmptyBorder(4, 4, 4, 4));
		panel.add(panel_book_info);
		panel.add(panel_control);
		

		// Sets frame properties
		setTitle("Bookshelter Desktop");
		setIconImage(icon.getImage());
		setResizable(false);
		setContentPane(panel);
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		
		// Updating book info displayed on frame
		try {
			ClickActions.updateAppFrame("+", label_book_name, label_book_author, label_tot_pages, label_current_page, label_progress, label_note, label_index);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Disable next, back, edit and remove button if there is no book stored
		if(Data.getBook_index() == 0 || Data.getBook_quantity() == 0) {
			AppFrame.getButton_next().setEnabled(false);
			AppFrame.getButton_back().setEnabled(false);
			AppFrame.getButton_edit().setEnabled(false);
			AppFrame.getButton_remove().setEnabled(false);
		}
		

	}
	
	// Getters and Setters
	public static JLabel getLabel_book_name() {
		return label_book_name;
	}
	
	public static JLabel getLabel_book_author() {
		return label_book_author;
	}

	public static JLabel getLabel_tot_pages() {
		return label_tot_pages;
	}

	public static JLabel getLabel_current_page() {
		return label_current_page;
	}

	public static JLabel getLabel_progress() {
		return label_progress;
	}

	public static JLabel getLabel_note() {
		return label_note;
	}

	public static JLabel getLabel_index() {
		return label_index;
	}

	public static JButton getButton_next() {
		return button_next;
	}

	public static void setButton_next(JButton button_next) {
		AppFrame.button_next = button_next;
	}

	public static JButton getButton_back() {
		return button_back;
	}

	public static void setButton_back(JButton button_back) {
		AppFrame.button_back = button_back;
	}

	public static JButton getButton_add() {
		return button_add;
	}

	public static void setButton_add(JButton button_add) {
		AppFrame.button_add = button_add;
	}

	public static JButton getButton_edit() {
		return button_edit;
	}

	public static void setButton_edit(JButton button_edit) {
		AppFrame.button_edit = button_edit;
	}

	public static JButton getButton_remove() {
		return button_remove;
	}

	public static void setButton_remove(JButton button_remove) {
		AppFrame.button_remove = button_remove;
	}

	
	
}

	
	


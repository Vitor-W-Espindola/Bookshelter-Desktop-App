package bookshetlter.pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

/*
 * ClickActions store all methods called when user click some button.
 * 
 * Methods:
 * 
 * 			loginClick() -> Checks if database contains, in a same row of table users, username and password.
 *
 * 			signupClick() -> Adds a row to database, containing id, username and password.
 * 
 * 			updateAppFrame() -> Updates Data.book_quantity by query against database, increases 1 at Data.book_index,
 * 								executes query against database again and update book info on frame. Also checks
 * 								whether next, back, edit or remove button got clicked, using 'direction' parameter.
 * 
 * 			addBookClickDone() -> Executes query against database and insert new row with AppFrame fields values,
 * 								set Data.book_index == Data.book_quantity displaying last book, and also calls 
 * 								updateAppFrame() with "+" parameter in order to display the added book on frame.
 * 
 * 			editBookClickDone() -> Executes UPDATE clause against database and modify the current row 
 * 								changing old values for EditBookFrame fields values. Also calls 
 * 								updateAppFrame() using "" parameter to update info displayed 
 * 								without changing Data.book_index.
 * 
 * 			removeBookClick() -> This method works with some conditions.
 * 								RemoveBookConditions FlowChart explains it well.
 * 								
 */

public class ClickActions {
	
	public static void loginClick(JFrame frame, JTextField field_login, JPasswordField field_password) throws SQLException {
		
		Connection conn;
		Statement stt;
		ResultSet rs;
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelter", "root", "");
		stt = conn.createStatement();
		
		String username = field_login.getText();
		String password = String.valueOf(field_password.getPassword());
		String query;
		query = String.format("SELECT * FROM users WHERE username = '%s' AND password = '%s'", username, password);
		
		rs = stt.executeQuery(query);
		
		if(rs.next()) {
			Data.setData_user_id(rs.getInt(1));
			Data.setData_username(field_login.getText());
			Data.setData_password(String.valueOf(field_password.getPassword()));
			
			Main.appframe.initAppFrame();
			frame.dispose();
			
		} else {
			Main.loginframe.getLabel_warning().setText("Credentials not found.");
		}
		
		stt.close();
		conn.close();
		
	}
	
	public static void signUpClick(JTextField field_login, JPasswordField field_password) throws SQLException {
		
		Connection conn;
		Statement stt;
		ResultSet rs;
		
		
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelter", "root", "");
		stt = conn.createStatement();
		
		String username = field_login.getText();
		String password = String.valueOf(field_password.getPassword());
		String query;
		
		query = String.format("SELECT * FROM users WHERE username = \"%s\"", username);
		rs = stt.executeQuery(query);
		
		// Checks if username is already registered
		if(!rs.next()) {
		
		query = String.format("INSERT INTO users (username, password) VALUES ('%s', '%s')", username, password);
		
		stt.executeUpdate(query);

		stt.close();
		conn.close();
		
		Main.loginframe.getLabel_warning().setText("User registered.");
		
		} else {
			Main.loginframe.getLabel_warning().setText("User already registered.");
		}
	}
	
	
	
	public static void updateAppFrame(String direction, JLabel label_book_name, JLabel label_book_author, JLabel label_tot_pages, JLabel label_current_page,
	JLabel label_progress, JLabel label_note, JLabel label_index) throws SQLException {
		
		Connection conn;
		Statement stt;
		ResultSet rs;
		
		// Sets Data.book_quantity
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelter", "root", "");
		stt = conn.createStatement();
		String username = Data.getData_username();
		String query;
		query = String.format("SELECT COUNT(*) FROM books WHERE owner = '%s'", username);
		
		rs = stt.executeQuery(query);
		
		if(rs.next()) {
			Data.setBook_quantity(rs.getInt("COUNT(*)"));
		}
		
		
		// Checks whether next, back, edit or remove button got clicked
		
		// Next button
		if(direction.equalsIgnoreCase("+")) { 
			if(Data.getBook_index() != Data.getBook_quantity()) {
				Data.setBook_index(Data.getBook_index() + 1);
				query = String.format("SELECT * FROM books WHERE owner = '%s' LIMIT %d OFFSET %d", username, Data.getBook_index(), Data.getBook_index() - 1);
				
			} else { return; }
		}
		
		// Back button
		if (direction.equalsIgnoreCase("-")) {
			if(Data.getBook_index() > 1) {
				Data.setBook_index(Data.getBook_index() - 1);
				query = String.format("SELECT * FROM books WHERE owner = '%s' LIMIT %d OFFSET %d", username, Data.getBook_index(), Data.getBook_index() - 1);
				
			} else { return; }
		}
		
		// Edit button
		if (direction.equalsIgnoreCase("")) { 
			query = String.format("SELECT * FROM books WHERE owner = '%s' LIMIT %d OFFSET %d", username, Data.getBook_index(), Data.getBook_index() - 1);
		}
		
		// Remove button
		if (direction.equalsIgnoreCase("rm")) { 
			if(Data.getBook_index() >= 1 && Data.getBook_quantity() > 1) { // Query will pick the book row in database
				query = String.format("SELECT * FROM books WHERE owner = '%s' LIMIT %d OFFSET %d", username, Data.getBook_index(), Data.getBook_index() - 1);
			} else { // In case there is only one book stored
				query = String.format("SELECT * FROM books WHERE owner = '%s'", username, Data.getBook_index());
			}
		}
		
		rs = stt.executeQuery(query);
		
		if(rs.next()) {
			AppFrame.getButton_next().setEnabled(true);
			AppFrame.getButton_back().setEnabled(true);
			AppFrame.getButton_add().setEnabled(true);
			AppFrame.getButton_edit().setEnabled(true);
			AppFrame.getButton_remove().setEnabled(true);
			Data.setData_book_id(rs.getInt("book_id"));
			Data.setData_book_owner(rs.getString("owner"));
			Data.setData_book_name(rs.getString("name"));
			Data.setData_book_author(rs.getString("author"));
			Data.setData_book_tot_pages(rs.getInt("tot_pages"));
			Data.setData_book_current_page(rs.getInt("current_page"));
			Data.setData_book_progress(rs.getInt("progress"));
			Data.setData_book_note(rs.getString("note"));
			
		} else if (rs.next() == false) {
			// Disable next, back, edit and remove button if there is no book stored
			AppFrame.getButton_next().setEnabled(false);
			AppFrame.getButton_back().setEnabled(false);
			AppFrame.getButton_edit().setEnabled(false);
			AppFrame.getButton_remove().setEnabled(false);
			Data.setData_book_id(0);
			Data.setData_book_owner("");
			Data.setData_book_author("");
			Data.setData_book_name("");
			Data.setData_book_tot_pages(0);
			Data.setData_book_current_page(0);
			Data.setData_book_progress(0);
			Data.setData_book_note("");
		}

		label_book_name.setText(String.format("Title: %s", Data.getData_book_name()));
		label_book_author.setText(String.format("Author: %s", Data.getData_book_author()));
		label_tot_pages.setText(String.format("Total of pages: %d", Data.getData_book_tot_pages()));
		label_current_page.setText(String.format("Current page: %d", Data.getData_book_current_page()));
		label_progress.setText(String.format("Progress: %d %%", Data.getData_book_progress()));
		label_note.setText(String.format("Note: %s", Data.getData_book_note()));
		label_index.setText(String.format("Book %s of %s", Data.getBook_index(), Data.getBook_quantity()));
		
		// Updates EditBookFrame for when users click on edit, edit frame fields auto-complete with current book info
		EditBookFrame.getField_book_name().setText(Data.getData_book_name());
		EditBookFrame.getField_book_author().setText(Data.getData_book_author());
		EditBookFrame.getField_tot_pages().setText(String.format("%d", Data.getData_book_tot_pages()));
		EditBookFrame.getField_current_page().setText(String.format("%d", Data.getData_book_current_page()));
		EditBookFrame.getField_note().setText(Data.getData_book_note());
		
		stt.close();
		conn.close();
		
	}
	
	public static void addBookClickDone(JTextField field_book_name, JTextField field_book_author, JTextField field_tot_pages, 
			JTextField field_current_page, JTextField field_note) throws SQLException {
		
		
		
		Connection conn;
		Statement stt;
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelter", "root", "");
		stt = conn.createStatement();
		String query;
		
		Data.setData_book_progress((int) ((Float.parseFloat(field_current_page.getText())/Float.parseFloat(field_tot_pages.getText()))*100));
		
		query = String.format("INSERT INTO books (owner, name, author, tot_pages, current_page, progress, note) VALUES (\"%s\", \"%s\", \"%s\", \"%d\", \"%d\", \"%d\", \"%s\")",
				Data.getData_username(),
				field_book_name.getText(),
				field_book_author.getText(),
				Integer.parseInt(field_tot_pages.getText()),
				Integer.parseInt(field_current_page.getText()),
				Data.getData_book_progress(),
				field_note.getText());
		
		stt.executeUpdate(query);
		
		
		
		Main.addbookframe.setVisible(false);
		Main.appframe.setEnabled(true);
		Main.appframe.setVisible(true);
		
		
		Data.setBook_index(Data.getBook_quantity());
		ClickActions.updateAppFrame("+", AppFrame.getLabel_book_name(), AppFrame.getLabel_book_author(), AppFrame.getLabel_tot_pages(), AppFrame.getLabel_current_page(),
				AppFrame.getLabel_progress(), AppFrame.getLabel_note(), AppFrame.getLabel_index());
		
		field_book_name.setText("");
		field_book_author.setText("");
		field_tot_pages.setText("");
		field_current_page.setText("");
		field_note.setText("");
		
		stt.close();
		conn.close();
	}
	
	public static void editBookClickDone(JTextField field_book_name, JTextField field_book_author, JTextField field_tot_pages, 
			JTextField field_current_page, JTextField field_note) throws SQLException {
		
		
		
		Connection conn;
		Statement stt;
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelter", "root", "");
		stt = conn.createStatement();
		String query;
		
		int progress = (int) ((Float.parseFloat(field_current_page.getText())/Float.parseFloat(field_tot_pages.getText()))*100);
		
		
		query = String.format("UPDATE books SET name=\"%s\", author=\"%s\", tot_pages=\"%d\", current_page=\"%d\", progress=\"%d\", note=\"%s\" "
				+ "WHERE book_id=\"%d\" AND owner=\"%s\" AND name=\"%s\" AND author=\"%s\" AND tot_pages=\"%d\" AND current_page=\"%d\" AND progress=\"%d\" AND note=\"%s\"",
				field_book_name.getText(),
				field_book_author.getText(),
				Integer.parseInt(field_tot_pages.getText()),
				Integer.parseInt(field_current_page.getText()),
				progress,
				field_note.getText(),
				Data.getData_book_id(),
				Data.getData_book_owner(),
				Data.getData_book_name(),
				Data.getData_book_author(),
				Data.getData_book_tot_pages(),
				Data.getData_book_current_page(),
				Data.getData_book_progress(),
				Data.getData_book_note());
		
		stt.executeUpdate(query);
		
		
		Main.editbookframe.setVisible(false);
		Main.appframe.setEnabled(true);
		Main.appframe.setVisible(true);
		
		ClickActions.updateAppFrame("", AppFrame.getLabel_book_name(), AppFrame.getLabel_book_author(), AppFrame.getLabel_tot_pages(), AppFrame.getLabel_current_page(),
				AppFrame.getLabel_progress(), AppFrame.getLabel_note(), AppFrame.getLabel_index());

		stt.close();
		conn.close();
		
	}
	
	public static void removeClick() throws SQLException {

		if(Data.getBook_index() == 0 && Data.getBook_quantity() == 0) { return; } /* Book 0 of 0, return */
		else if(Data.getBook_index() == 1 && Data.getBook_quantity() > 1) {  } /* Book 1 of 2, do nothing, keep Data.book_index at 1 */
		else { Data.setBook_index(Data.getBook_index() - 1); }  /* Book 2 of 3, decreases Data.book_index by 1*/ 
		
		Connection conn;
		Statement stt;
		
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshelter", "root", "");
		stt = conn.createStatement();
		String query;
		query = String.format("DELETE FROM books "
				+ "WHERE owner = \"%s\" AND name = \"%s\" AND tot_pages = %d AND current_page = %d"
				+ " AND progress = %d AND note = \"%s\"", Data.getData_book_owner(), Data.getData_book_name(),
				Data.getData_book_tot_pages(), Data.getData_book_current_page(), Data.getData_book_progress(), Data.getData_book_note());
		
		stt.executeUpdate(query);
		
		ClickActions.updateAppFrame("rm", AppFrame.getLabel_book_name(), AppFrame.getLabel_book_author(), AppFrame.getLabel_tot_pages(), AppFrame.getLabel_current_page(),
				AppFrame.getLabel_progress(), AppFrame.getLabel_note(), AppFrame.getLabel_index());
		
		stt.close();
		conn.close();
	}
	
}

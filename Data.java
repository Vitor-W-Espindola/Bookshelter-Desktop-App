package bookshetlter.pack;

public class Data {
	
	
	
	// User info
	private static int data_user_id;
	private static String data_username;
	private static String data_password;
	
	// Current Book info
	private static int data_book_id;
	private static String data_book_owner;
	private static String data_book_name;
	private static String data_book_author;
	private static int data_book_tot_pages;
	private static int data_book_current_page;
	private static int data_book_progress;
	private static String data_book_note;
	
	// Control variables
	private static int book_index = 0;
	private static int book_quantity;
	
	// Getters and Setters
	public static int getData_user_id() {
		return data_user_id;
	}
	public static void setData_user_id(int data_user_id) {
		Data.data_user_id = data_user_id;
	}
	public static String getData_username() {
		return data_username;
	}
	public static void setData_username(String data_username) {
		Data.data_username = data_username;
	}
	public static String getData_password() {
		return data_password;
	}
	public static void setData_password(String data_password) {
		Data.data_password = data_password;
	}
	public static int getData_book_id() {
		return data_book_id;
	}
	public static void setData_book_id(int data_book_id) {
		Data.data_book_id = data_book_id;
	}
	public static String getData_book_owner() {
		return data_book_owner;
	}
	public static void setData_book_owner(String data_book_owner) {
		Data.data_book_owner = data_book_owner;
	}
	public static String getData_book_name() {
		return data_book_name;
	}
	public static void setData_book_name(String data_book_name) {
		Data.data_book_name = data_book_name;
	}
	public static String getData_book_author() {
		return data_book_author;
	}
	public static void setData_book_author(String data_book_author) {
		Data.data_book_author = data_book_author;
	}
	public static int getData_book_tot_pages() {
		return data_book_tot_pages;
	}
	public static void setData_book_tot_pages(int data_book_tot_pages) {
		Data.data_book_tot_pages = data_book_tot_pages;
	}
	public static int getData_book_current_page() {
		return data_book_current_page;
	}
	public static void setData_book_current_page(int data_book_current_page) {
		Data.data_book_current_page = data_book_current_page;
	}
	public static int getData_book_progress() {
		return data_book_progress;
	}
	public static void setData_book_progress(int data_book_progress) {
		Data.data_book_progress = data_book_progress;
	}
	public static String getData_book_note() {
		return data_book_note;
	}
	public static void setData_book_note(String data_book_note) {
		Data.data_book_note = data_book_note;
	}
	public static int getBook_index() {
		return book_index;
	}
	public static void setBook_index(int book_index) {
		Data.book_index = book_index;
	}
	public static int getBook_quantity() {
		return book_quantity;
	}
	public static void setBook_quantity(int book_quantity) {
		Data.book_quantity = book_quantity;
	}
	
	
	
	
	
}
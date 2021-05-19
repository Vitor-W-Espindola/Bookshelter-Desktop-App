package bookshetlter.pack;

public class Main {
	
	// All application frames
	public static LoginFrame loginframe = new LoginFrame();
	public static AppFrame appframe = new AppFrame();
	public static AddBookFrame addbookframe = new AddBookFrame();
	public static EditBookFrame editbookframe = new EditBookFrame();
	
	public static void main(String[] args) {
		
		loginframe.initLoginFrame();
		addbookframe.initAddBookFrame();
		editbookframe.initEditBookFrame();
		
	}
	

}

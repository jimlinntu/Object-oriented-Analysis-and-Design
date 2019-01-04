package controller;

import boundary.MainPage; 
import javafx.stage.*;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import boundary.BookTicketUI;
import javafx.collections.*;

public class BookTicket {
	
	private MainPage main_page;
	private BookTicketUI book_ticket_ui;
	
	public BookTicket(MainPage main_page) {
		this.main_page = main_page;
		book_ticket_ui = new BookTicketUI(this);
		book_ticket_ui.startInterface(main_page.getScene());
	}
	
	public void goToMenu() {
		this.main_page.restorePane();
	}
}

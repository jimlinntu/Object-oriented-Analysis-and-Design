package controller;
import javafx.stage.*;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import boundary.BookTicketUI;
import javafx.collections.*;

public class BookTicket {
	
	private BookTicketUI book_ticket_ui;
	
	public BookTicket(Scene scene) {
		book_ticket_ui = new BookTicketUI();
		book_ticket_ui.startInterface(scene);
	}
}

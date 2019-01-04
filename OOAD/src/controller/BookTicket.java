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
	private DAO data_object ;
	private GenerateTicket generate_ticket ;
	private ArrayList<Ticket> tickets ;
	private Order order ;
	private Info info = null ;
	
	public BookTicket(Scene scene, GenerateTicket generate_ticket) {
		book_ticket_ui = new BookTicketUI();
		book_ticket_ui.startInterface(scene);
		this.generate_ticket = generate_ticket ;
	}
	
	public void inputTrainInfo() {
		tickets = generate_ticket.generate() ;
		order = new Order(tickets) ;
		
		// is it?
		data_object.writeOrder() ;
		
		ui.showOrder(order) ;
	}
	
	public void confirmOrder() {
		return ;
	}
	
	public void rejectOrder() {
		order.release() ;
	}
}

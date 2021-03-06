package controller;

import java.util.ArrayList;

import boundary.MainPage; 
import boundary.BookTicketUI;
import controller.GenerateTicket;
import entity.*;
import javafx.stage.*;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.collections.*;
import javafx.util.Pair;

public class BookTicket {
	
	private MainPage main_page;
	private BookTicketUI book_ticket_ui;
	private GenerateTicket generate_ticket_controller ;
	private Order order ;
	
	public BookTicket(MainPage main_page) {
		this.main_page = main_page;
		this.generate_ticket_controller = new GenerateTicket();
		this.book_ticket_ui = new BookTicketUI(this, main_page.getRootPane());
		
		try{
			this.book_ticket_ui.startInterface();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public Pair<Order, String> inputTrainInfo(Info info) {
		Pair<Order, String> ret = this.generate_ticket_controller.generate(info) ;
		Order order = ret.getKey();
		// or empty string?
		if (order != null) {
			boolean success = false;
			try {
				success = DataAccessObject.writeOrder(order);
			}catch(Exception e) {
				e.printStackTrace();
			}
			// fail to writeOrder
			if (success == false) {
				this.error("Insert order to database");
			}

			System.out.println(order);
		}
		
		return ret;
	}
	
	public void confirmOrder() {
		System.out.println("[*] Order confirmed.");
		return ;
	}
	
	public void rejectOrder(Order order) {
		order.release() ;
		System.out.println("[*] Order cancelled.");
	}
	
	public void goToMenu() {
		this.main_page.goToMenu();
	}
	
	public void error(String error_message) {
		System.out.println("Fail: " + error_message);
		goToMenu();
	}
}

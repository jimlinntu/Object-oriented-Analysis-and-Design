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

public class BookTicket {
	
	private MainPage main_page;
	private BookTicketUI book_ticket_ui;
	private GenerateTicket generate_ticket_controller ;
	private Order order ;
	private DataAccessObject dao;
	
	public BookTicket(MainPage main_page, DataAccessObject dao) {
		this.main_page = main_page;
		this.generate_ticket_controller = new GenerateTicket(main_page, dao) ;
		this.book_ticket_ui = new BookTicketUI(this, main_page.getRootPane());
		this.dao = dao;
		
		try{
			this.book_ticket_ui.startInterface();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	public Order inputTrainInfo(Info info) {
		// TODO: disabled temporarily
		//order = this.generate_ticket_controller.generate(info) ;
		order = dao.getOrder("","");
				
		// is it?
		if (!dao.writeOrder(order)) {
			this.error("Insert order to database");
		} ;

		System.out.println(order);
		return order;
	}
	
	public void confirmOrder() {
		return ;
	}
	
	public void rejectOrder() {
		order.release() ;
	}
	
	public void goToMenu() {
		this.main_page.goToMenu();
	}
	
	public void error(String error_message) {
		System.out.println("Fail: " + error_message);
		goToMenu();
	}
}

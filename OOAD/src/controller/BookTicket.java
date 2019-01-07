package controller;

import java.util.ArrayList;

import boundary.MainPage; 
import boundary.BookTicketUI;
import controller.GenerateTicket;
import entity.Ticket;
import entity.DataAccessObject;
import entity.Info;

import javafx.stage.*;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.collections.*;

public class BookTicket {
	
	private MainPage main_page;
	private BookTicketUI book_ticket_ui;
	//private DAO data_object ;
	private GenerateTicket generate_ticket_controller ;
	//private Order order ;
	private DataAccessObject dao;
	
	public BookTicket(MainPage main_page, DataAccessObject dao) {
		this.main_page = main_page;
		this.generate_ticket_controller = new GenerateTicket() ;
		this.book_ticket_ui = new BookTicketUI(this, main_page.getRootPane());
		
		try{
			this.book_ticket_ui.startInterface();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void inputTrainInfo(Info info) {
		order = generate_ticket_controller.generate(info) ;
		
		// is it?
		data_object.writeOrder(order) ;
		
		book_ticket_ui.showOrder(order) ;
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
}

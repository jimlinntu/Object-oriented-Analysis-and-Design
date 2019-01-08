/**
 * 
 */
package controller;

import entity.*;
import javafx.scene.control.Dialog;
import boundary.GenerateTicketUI;
import boundary.MainPage;
import boundary.SearchBookIDUI;

import java.util.*;



/**
 * @author jimlin
 *
 */
public class GenerateTicket {

	private MainPage main_page;
	private GenerateTicketUI generate_ticket_ui;
	private DataAccessObject dao;
	
	public GenerateTicket(MainPage main_page, DataAccessObject dao) {
		this.main_page = main_page;
		this.generate_ticket_ui = new GenerateTicketUI(this, main_page.getRootPane());
		this.dao = dao;
	}
	/**
	 * This function will change ServiceAnchorPane and let
	 * user select which train it want to take.
	 * @author jimlin
	 * @return Order
	 */
	public Order generate(Info info) {
		ArrayList<Train> train_list = this.dao.listTrains();
		// a blocking startInterface function
		if(train_list.size() == 0) {
			// there is no train to select
			return null;
		}else {
			int trainID = this.generate_ticket_ui.selectTrain(train_list);
			// 
			System.out.println("你選擇了: " + trainID + "車次");
			return null; //
		}
	}
	public boolean inputTrainID(int train_id) {
		// Use train_id to retrieve train
		Train train = this.dao.getTrain(train_id);
		// 
		ArrayList<Seat> seats = train.getAvailableSeats();
		// if there is no enough seat
		if(seats.size() < this.info.ticket_num) {
			
		}
		// Create Ticket
		Ticket ticket = new Ticket(seat);
		// After 
		this.generate_ticket_ui.showOrder();
	}
}

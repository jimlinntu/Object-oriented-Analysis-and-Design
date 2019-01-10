/**
 * 
 */
package controller;

import entity.*;
import javafx.scene.control.Dialog;
import javafx.util.Pair;
import boundary.GenerateTicketUI;
import boundary.MainPage;
import boundary.SearchBookIDUI;

import java.util.*;
import javafx.util.Pair;



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
		this.dao = dao;
	}
	/**
	 * This function will change ServiceAnchorPane and let
	 * user select which train it want to take.
	 * @author jimlin
	 * @return Pair<Order, String(Error Message)>
	 */
	public Pair<Order, String> generate(Info info) {
		// Set up UI interface 
		this.generate_ticket_ui = new GenerateTicketUI(this, main_page.getRootPane());
		// 
		List<TrainTime> train_time = this.dao.listTrains(info);
		// a blocking startInterface function
		if(train_list.size() == 0) {
			// there is no train to select
			return new Pair<Order, String>(null, "目前沒有車次符合您的需求"); 
		}else {
			// Let user choose which train he wants
			Train selectedTrain = this.generate_ticket_ui.selectTrain(train_time[0].trainId);
			System.out.println("你選擇了: " + trainID + "車次");
			// Let user confirm
			List<Seat> seat_list = this.dao.getAvailableSeat(selectedTrain);
			List<Ticket> ticket_list = new //
			ticket
			Order order = new Order(); //
			
			return null; // TODO: Fix it
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

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
import java.util.stream.IntStream;
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
		Map<String, List<TrainTime>> train_times = (Map)this.dao.listTrains(info);
		// a blocking startInterface function
		if(train_times.size() == 0) {
			// there is no train to select
			return new Pair<Order, String>(null, "目前沒有車次符合您的需求"); 
		}else {
			// Let user choose which train he wants
			Map<String, TrainTime> selectedTrainTime = (Map)this.generate_ticket_ui.selectTrain(train_times);
			
			System.out.println("你選擇了: " + selectedTrainTime.trainId + "車次");
			// 
			List<Seat> seat_list = this.dao.getAvailableSeat(selectedTrainTime.get("go"));
			List<Seat> back_seat_list = null;
			if(info.buyBack == true) {
				back_seat_list = this.dao.getAvailableSeat(selectedTrainTiem.get("back"));
			}
			List<Seat> candidate_seats = new ArrayList<Seat>();
			List<Seat> back_candidate_seats = new ArrayList<Seat>();
			int totalTicketNum = IntStream.of(info.ticketNum).sum();
			// Search go part
			for(Seat seat: seat_list) {
				if(seat.carType == info.cartype) {
					candidate_seats.add(seat);
				}
				if(candidate_seats.size() == totalTicketNum) {
					break;
				}
			}
			if(candidate_seats.size() < totalTicketNum) {
				return new Pair<Order, String>(null, "您所選擇的車次目前沒有足夠的車位");
			}
			// Search back part
			if(info.buyBack == true) {
				for(Seat seat: ) {
					
				}
			}
			
			// Create a list of tickets
			List<Ticket> ticket_list = new ArrayList<Ticket>();
			for(Seat seat: candidate_seats) {
				ticket_list.add(new Ticket(seat, Station.chineseToIndex.get(info.origin), 
						Station.chineseToIndex.get(info.destination), ));
			}
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

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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.IntStream;
import javafx.util.Pair;


/**
 * @author jimlin
 *
 */
public class GenerateTicket {
	static Random orderIdRand = new Random();
	private GenerateTicketUI generate_ticket_ui;
	
	public GenerateTicket() {
	}
	/**
	 * This function will change ServiceAnchorPane and let
	 * user select which train it want to take.
	 * @author jimlin
	 * 
	 * Workflow:
	 * 1. List train times by Info 
	 * 2. Let the user choose train times
	 * 3. Get available seats given user choosen train time
	 * 4. Create a list of tickets
	 * 5. Create a Order by a list of tickets
	 * 
	 * @return Pair<Order, String(Error Message)>
	 */
	public Pair<Order, String> generate(Info info) {
		List<Ticket> ticket_list = new ArrayList<Ticket>();
		int totalTicketNum = IntStream.of(info.ticketNum).sum();
		// Set up UI interface 
		this.generate_ticket_ui = new GenerateTicketUI(this);
		// Get available train times (go) 
		List<TrainTime> train_times = null;
		try{
			train_times = DataAccessObject.listTrains(info.goDate, info.from, info.to);
		}catch(Exception e) {	
			e.printStackTrace();
		}
		// Ticket Type List
		List<TicketType> ticket_type_list = new ArrayList<TicketType>();
		// i: TicketType iterator index
		for(int i = 0; i < info.ticketNum.length; i++) {
			for(int j = 0; j < info.ticketNum[i]; j++) {
				ticket_type_list.add(TicketType.indexToTicketType(i)); 
			}
		}
		if(train_times.size() == 0) {
			// there is no train to select
			return new Pair<Order, String>(null, "目前沒有車次符合您的需求，請更換去程的時間"); 
		}else {
			// Let user choose which train he wants
			
			TrainTime selectedTrainTime = null;
			if(info.selectByTime) {
				selectedTrainTime = this.generate_ticket_ui.selectTrain(train_times, "去程");
			}else {
				// goSelector is trainId here (See Info.java)
				int trainId = Integer.parseInt(info.goSelector) ;
				for(TrainTime train_time: train_times) {
					if(train_time.trainId == trainId) {
						selectedTrainTime = train_time;
						break;
					}
				}
				if(selectedTrainTime == null) {
					return new Pair<Order, String>(null, "同學，你是不是打錯 去程 車次號碼了呀？");
				}
			}
			System.out.println("[去程] 你選擇了: " + selectedTrainTime.trainId + "車次");
			// Get Available Seats(go and back)
			List<Seat> seat_list = null;
			try {
				seat_list = DataAccessObject.getAvailableSeats(selectedTrainTime);
			}catch(Exception e) {
				e.printStackTrace();
			}
				
			List<Seat> candidate_seats = new ArrayList<Seat>();
			
			// Search(Find the first match)
			for(Seat seat: seat_list) {
				if(seat.carType == info.cartype) {
					candidate_seats.add(seat);
					if(candidate_seats.size() == totalTicketNum) {
						break;
					}
				}
			}
			if(candidate_seats.size() < totalTicketNum) {
				return new Pair<Order, String>(null, "您所選擇的去程車次目前沒有足夠的車位");
			}
			// Assertion
			assert ticket_type_list.size() == candidate_seats.size() && candidate_seats.size() == totalTicketNum;
			// Create a list of Tickets
			for(int i = 0; i < totalTicketNum; i++) {
				Seat seat = candidate_seats.get(i);
				TicketType ticketType = ticket_type_list.get(i);
				// Go
				ticket_list.add(new Ticket(seat, info.from, info.to, ticketType));
			}
		}
		// if buy back
		if(info.buyBack == true) {
			// Get available train times (back)
			List<TrainTime> backTrain_times = null; 
			try{
				// Search "back"
				backTrain_times = DataAccessObject.listTrains(info.backDate, info.to, info.from);
			}catch(Exception e){
				e.printStackTrace();
			}
			// there is no back train to select
			if(backTrain_times.size() == 0) {
				return new Pair<Order, String>(null, "目前沒有回程的車次符合您的需求，請更換回程的時間");
			}
			TrainTime backSelectedTrainTime = null;
			
			if(info.selectByTime) {
				backSelectedTrainTime = this.generate_ticket_ui.selectTrain(backTrain_times, "回程");
			}else {
				// backSelector is trainId here (See Info.java)
				int trainId = Integer.parseInt(info.backSelector);
				for(TrainTime train_time: backTrain_times) {
					if(train_time.trainId == trainId) {
						backSelectedTrainTime = train_time;
						break;
					}
				}
				// If we did not match a train id
				if(backSelectedTrainTime == null) {
					return new Pair<Order, String>(null, "同學，你是不是打錯 回程 車次號碼了呀？");
				}
			}
			System.out.println("[回程] 你選擇了" + backSelectedTrainTime.trainId + "車次");
			List<Seat> back_seat_list = null;
			try {
				back_seat_list = DataAccessObject.getAvailableSeats(backSelectedTrainTime);
			}catch(Exception e) {
				e.printStackTrace();
			}
				
			List<Seat> back_candidate_seats = new ArrayList<Seat>();
			// Search back part
			for(Seat seat: back_seat_list) {
				if(seat.carType == info.cartype) {
					back_candidate_seats.add(seat);
					if(back_candidate_seats.size() == totalTicketNum) {
						break;
					}
				}
				
			}
			if(back_candidate_seats.size() < totalTicketNum) {
				return new Pair<Order, String>(null, "您選擇的回程車次目前沒有足夠的車位");
			}
			assert ticket_type_list.size() == back_candidate_seats.size() && back_candidate_seats.size() == totalTicketNum;
			
			for(int i = 0; i < totalTicketNum; i++) {
				Seat seat = back_candidate_seats.get(i);
				TicketType ticketType = ticket_type_list.get(i);
				ticket_list.add(new Ticket(seat, info.to, info.from, ticketType));
			}
		}
		// 
		Order order = new Order(GenerateTicket.orderIdRand.nextInt(2184723), info.userID, ticket_list); 
		return new Pair<Order, String>(order, null);
	}
}

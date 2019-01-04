/**
 * 
 */
package controller;

import entity.*;
import boundary.GenerateTicketUI;
import java.util.*;



/**
 * @author jimlin
 *
 */
public class GenerateTicket {

	private GenerateTicketUI generate_ticket_ui;
	private DataAccessObject dao;
	private Info info = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public Order generate(Info info) {
		Order order;
		// start generate ticket ui interface
		this.generate_ticket_ui.startInterface();
		// wait until UI dead?
		
		//
		assert this.info == null;
		this.info = info;
	
		return order; //
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

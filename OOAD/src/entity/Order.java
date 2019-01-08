package entity;

import java.util.ArrayList;

public class Order {
	public String orderID;
	public String userID;
	public String origin;
	public String dest;
	public String goTime;
	public String arriveTime;
	public int adultTicket;
	public int childTicket;
	public int disableTicket;
	public int seniorTicket;
	public int price;
	public ArrayList<Ticket> ticketList;
	
	public Order() {
		
	}
}

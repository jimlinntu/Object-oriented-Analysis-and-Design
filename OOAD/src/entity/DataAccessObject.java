package entity;
import java.util.*;
import entity.Order;
import entity.Ticket;

public class DataAccessObject {
	
	public ArrayList<Integer> getOrderID(String userID, String origin, String dest, int date, int trainID){
		return new ArrayList<Integer>(){
			{
				add(Integer.valueOf(03530771));
				add(Integer.valueOf(78899381));
			}
		};
	}
	
	public ArrayList<Train> listTrains(){
		return new ArrayList<Train>(){
			{
				add(new Train(1823));
				add(new Train(1293));
			}
		};
	}
	
	public Order getOrder(String userID, String orderID) {
		Order order = new Order();
		order.adultTicket = 1;
		order.childTicket = 2;
		order.disableTicket = 0;
		order.seniorTicket = 1;
		order.price = 6900;
		order.origin = "台北";
		order.dest = "高雄";
		order.goTime = "12:12";
		order.arriveTime = "14:41";
		order.orderID = "1314520";
		order.userID = "C87639487";
		ArrayList<Ticket> tickets = new ArrayList<Ticket>() {
			{
				for(int i=1;i<5;i++) {
					String seat = "8車"+i+"F";
					Ticket ticket = new Ticket(seat);
					add(ticket);
				}
			}			
		};
		order.ticketList = tickets;
		return order;
	}
}

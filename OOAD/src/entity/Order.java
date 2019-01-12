package entity;
import java.time.LocalTime;
import java.util.* ;

import javafx.util.Pair;

public class Order {
    int orderId;
    String userId;
    public List<Ticket> tickets;
    public Order(int orderId, String userId, List<Ticket> tickets) {
        this.orderId = orderId;
        this.userId = userId;
        this.tickets = tickets;
    }
    /**
     * @author jimlin
     * Check if this order is a round trip(來回票)
     */
    public boolean checkRoundTrip() {
    	assert this.tickets.size() > 0: "Order.tickets.size() should be greater than 0";
    	boolean isRoundTrip = false;
    	int from = this.tickets.get(0).from;
    	for(Ticket ticket: this.tickets) {
    		if(ticket.from != from) {
    			isRoundTrip = true;
    			break;
    		}
    	}
    	return isRoundTrip;
    }
    
    /**
     * @author jimlin
     * @return int[] (0: adult, 1: child, 2: disable, 3: senior)
     */
    public int[] getTicketNum(){
    	int[] ticket_num = new int[TicketType.getLength()];
    	int from = this.tickets.get(0).from;
    	if(this.checkRoundTrip()) {
    		assert this.tickets.size() % 2 == 0: "Round trip order should have even number of tickets";
    		for(Ticket ticket: this.tickets) {
    			// only count one direction
    			if(ticket.from == from) {
    				int ticketTypeIndex = ticket.getTicketType().getIndex();
    				ticket_num[ticketTypeIndex] += 1;
    			}
    		}
    		
    	}else {
    		for(Ticket ticket: this.tickets) {
    			int ticketTypeIndex = ticket.getTicketType().getIndex();
				ticket_num[ticketTypeIndex] += 1;
    		}
    	}
    	return ticket_num;
    }
    /**
     * @author jimlin
     */
    public double getCost() {
    	double cost = 0;
    	for(Ticket ticket: this.tickets) {
    		cost += ticket.getCost();
    	}
    	return cost;
    }
    /**
     * @author jimlin
     * @return int[2](from to), LocalTime[4]= {goDepart, goArrival , backDepart, backArrival}
     */
    public Pair<int[], LocalTime[]> getFromTo() {
    	assert this.tickets.size() > 0;
    	// check which date is before, then its from is what we want
    	int from = -1, to = -1;
    	LocalTime goDepart = null, goArrival = null, backDepart = null, backArrival = null;
    	if(this.checkRoundTrip()) {
    		// Search seat from go or back train
    		Seat seat_1 = tickets.get(0).seat; 
    		Seat seat_2 = null;
    		int tmp_from = tickets.get(0).from, tmp_to = tickets.get(0).to;
    		for(Ticket ticket: this.tickets) {
    			if(ticket.from != tmp_from) {
    				seat_2 = ticket.seat;
    			}
    		}
    		// Get TrainTime
    		TrainTime train_time_1 = seat_1.train.getTrainTime(tmp_from, tmp_to);
    		TrainTime train_time_2 = seat_2.train.getTrainTime(tmp_to, tmp_from);
    		// Check which datetime is the first
    		if(train_time_1.date.isBefore(train_time_2.date)) {
    			// train_time_1 is before train_time2, so train_time_1's from is 'true' from
    			from = tmp_from;
    			to = tmp_to;
    			goDepart = train_time_1.fromTime;
    			goArrival = train_time_1.toTime;
    			backDepart = train_time_2.fromTime;
    			backArrival = train_time_2.toTime;
    		}else if(train_time_1.date.isEqual(train_time_2.date)) {
    			if(train_time_1.fromTime.isBefore(train_time_2.fromTime)) {
    				from = tmp_from;
    				to = tmp_to;
    				goDepart = train_time_1.fromTime;
        			goArrival = train_time_1.toTime;
        			backDepart = train_time_2.fromTime;
        			backArrival = train_time_2.toTime;
    			}else if(train_time_1.fromTime.equals(train_time_2.fromTime)) {
    				assert false: "go and back will not be the same train";
    			}else {
    				from = tmp_to;
    				to = tmp_from;
    				goDepart = train_time_2.fromTime;
    				goArrival = train_time_2.toTime;
    				backDepart = train_time_1.fromTime;
    				backArrival = train_time_1.toTime;
    			}
    		}else {
    			from = tmp_to;
    			to = tmp_from;
    			goDepart = train_time_2.fromTime;
				goArrival = train_time_2.toTime;
				backDepart = train_time_1.fromTime;
				backArrival = train_time_1.toTime;
    		}
    	}else {
    		from = this.tickets.get(0).from;
    		to = this.tickets.get(0).to;
    		goDepart = this.tickets.get(0).seat.train.getTrainTime(from, to).fromTime;
    		goArrival = this.tickets.get(0).seat.train.getTrainTime(from, to).toTime;
    	} 
    	int[] fromto = {from, to};    	
    	LocalTime[] times = {goDepart, goArrival, backDepart, backArrival};
    	return new Pair<int[], LocalTime[]>(fromto, times);
    }
    
    public int getOrderId() {
    	return this.orderId;
    }
    public String getUserId() {
    	return this.userId;
    }
    
    /**
     * @author jimlin
     * Release order from database(via DataAccessObject)
     */
    public void release(){
    	try{
    		DataAccessObject.removeOrder(this.orderId);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}

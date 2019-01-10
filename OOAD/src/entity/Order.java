package entity;
import java.util.* ;

public class Order {
    List<Ticket> tickets;
    String userId;
    String orderId;
    Order(List<Ticket> tickets, String userId, String orderId) {
        this.tickets = tickets;
        this.userId = userId;
        this.orderId = orderId;
    }
    public Order() {
    	
    }
}

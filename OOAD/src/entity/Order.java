package entity;
import java.util.* ;

public class Order {
    int orderId;
    String userId;
    List<Ticket> tickets;
    Order(int orderId, String userId, List<Ticket> tickets) {
        this.orderId = orderId;
        this.userId = userId;
        this.tickets = tickets;
    }
}

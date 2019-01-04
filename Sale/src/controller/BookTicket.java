package controller ;

import entity.* ;

public class BookTicket {
	private DAO data_object ;
	private GenerateTicket generate_ticket ;
	private BookTicketUI ui ;
	private ArrayList<Ticket> tickets ;
	private Order order ;
	private Info info = null ;
	
	public BookTicket(GenerateTicket generate_ticket) {
		this.generate_ticket = generate_ticket ;
	}
	
	public void inputTrainInfo() {
		tickets = generate_ticket.generate() ;
		order = new Order(tickets) ;
		
		// is it?
		data_object.writeOrder() ;
		
		ui.showOrder(order) ;
	}
	
	public void confirmOrder() {
		return ;
	}
	
	public void rejectOrder() {
		order.release() ;
	}
}

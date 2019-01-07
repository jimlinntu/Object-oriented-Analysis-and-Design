package boundary;

import java.util.ArrayList;

import controller.ShowOrder_reserve;
import entity.Order;
import entity.Ticket;
import javafx.collections.FXCollections;
import javafx.scene.layout.Pane;

public class ShowOrder_reserveUI extends BaseUI<ShowOrder_reserveUIFXMLController>{
	private ShowOrder_reserve showorder_reserve_controller;
	
	public ShowOrder_reserveUI(ShowOrder_reserve showorder_reserve_controller, Pane root_pane) {
		// Set controller association
		this.showorder_reserve_controller = showorder_reserve_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/ShowOrder_reserve.fxml");
		this.prepareActions();
	}
	public void showOrder(Order order) {
		this.fxml_controller.adultTicket.setText(String.valueOf(order.adultTicket));
		this.fxml_controller.childTicket.setText(String.valueOf(order.childTicket));
		this.fxml_controller.disableTicket.setText(String.valueOf(order.disableTicket));
		this.fxml_controller.seniorTicket.setText(String.valueOf(order.seniorTicket));
		this.fxml_controller.price.setText(String.valueOf(order.price));
		this.fxml_controller.origin.setText(order.origin);
		this.fxml_controller.dest.setText(order.dest);
		this.fxml_controller.goTime.setText(order.goTime);
		this.fxml_controller.arriveTime.setText(order.arriveTime);
		this.fxml_controller.orderid.setText(order.orderID);
		this.fxml_controller.userid.setText(order.userID);
		
		ArrayList<String>seats = new ArrayList<String>();
		for(Ticket t : order.ticketList) {
			seats.add(t.seat);
		}
		this.fxml_controller.seats.setItems(FXCollections.observableArrayList(seats));
		this.startInterface();
	}
	@Override
	protected void prepareActions() {
		// TODO Auto-generated method stub
		
	}
}
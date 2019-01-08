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
		this.fxml_controller.setOrder(order);
		this.startInterface();
	}
	@Override
	protected void prepareActions() {
		// TODO Auto-generated method stub
		
	}
}
package boundary;

import controller.ReviseReserve;
import entity.Order;
import javafx.scene.layout.Pane;

public class ReviseReserveUI extends BaseUI<BaseFXMLController> {
	private ReviseReserve revise_reserve_controller;
	
	
	public ReviseReserveUI(ReviseReserve revise_reserve_controller, Pane root_pane) {
		// Set controller association
		this.revise_reserve_controller = revise_reserve_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/ReviseReserve.fxml");
		this.prepareActions();
	}
	protected void prepareActions() {
		
	}
	public boolean showFixedInfo(Order order) {
		ReviseReserveUIFXMLController tmp = (ReviseReserveUIFXMLController)this.fxml_controller;
		tmp.setInfo(order);
		return true;
	}
}
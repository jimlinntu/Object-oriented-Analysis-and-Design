package boundary;

import java.io.FileInputStream;
import java.util.ArrayList;

import controller.SearchReserve;
import controller.ShowOrder;
import entity.Order;
import entity.Ticket;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;

public class SearchReserveUI extends BaseUI<SearchReserveUIFXMLController>{
	private SearchReserve search_reserve_controller;
	
	
	public SearchReserveUI(SearchReserve search_reserve_controller, Pane root_pane) {
		// Set controller association
		this.search_reserve_controller = search_reserve_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/SearchReserve.fxml");
		this.prepareActions();
	}
	// return error message
	private String checkIfValid() {
		if(this.fxml_controller.userID.getCharacters().toString().equals("")) {
			return "身分證字號不可留空";
		}
		if(this.fxml_controller.bookid.getCharacters().toString().equals("")) {
			return "訂位代號不可留空";
		}
		return null;
	}
	
	protected void prepareActions() {
		EventHandler<ActionEvent> searchbyOrderID = (event) -> {
			String errMessage = this.checkIfValid();
			if(errMessage == null) {
				String userID = this.fxml_controller.userID.getCharacters().toString();
				String orderID = this.fxml_controller.bookid.getCharacters().toString();
				System.out.println("ID loaded!");
				this.search_reserve_controller.searchOrder(userID, orderID);
			}
			// else show error
			else {
				this.fxml_controller.errorMessage.setVisible(true);
				this.fxml_controller.errorMessage.setText(errMessage);
			}
		};
		this.fxml_controller.loginSearch.setOnAction(searchbyOrderID);
	}
}

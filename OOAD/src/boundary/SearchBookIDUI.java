package boundary;

import java.io.FileInputStream;

import controller.SearchBookID;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.event.*;

public class SearchBookIDUI extends BaseUI<SearchBookIDUIFXMLController>{
	private SearchBookID search_bookid_controller;
	
	public SearchBookIDUI(SearchBookID search_bookid_controller) {
		// Set controller association
		this.search_bookid_controller = search_bookid_controller;
		// Load page
		this.loadView("fxml/SearchBookID.fxml");
		this.prepareActions();
	}
	protected void prepareActions() {
		// 
		EventHandler<ActionEvent> searchbyUserID = (event) -> {
			ArrayList<Order> order_list = this.search_bookid_controller.searchOrderID("A17834234");
			this.showOrder(order_list);
		};
		// 
		this.fxml_controller.startSearch.setOnAction(searchbyUserID);
	}
	
	public void showOrder() {
		
		
	}
}

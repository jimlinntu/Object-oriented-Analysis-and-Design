package boundary;

import java.io.FileInputStream;

import controller.SearchReserve;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SearchReserveUI extends BaseUI<SearchReserveUIFXMLController>{
	private SearchReserve search_reserve_controller;
	
	
	public SearchReserveUI(SearchReserve search_reserve_controller) {
		// Set controller association
		this.search_reserve_controller = search_reserve_controller;
		// Load page
		this.loadView("fxml/SearchReserve.fxml");
	}
	protected void prepareActions() {
		
	}
}

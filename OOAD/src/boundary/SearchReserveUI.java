package boundary;

import java.io.FileInputStream;

import controller.SearchReserve;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SearchReserveUI extends BaseUI<SearchReserveUIFXMLController>{
	private SearchReserve search_reserve_controller;
	
	
	public SearchReserveUI(SearchReserve search_reserve_controller, Pane root_pane) {
		// Set controller association
		this.search_reserve_controller = search_reserve_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/SearchReserve.fxml");
	}
	protected void prepareActions() {
		
	}
}

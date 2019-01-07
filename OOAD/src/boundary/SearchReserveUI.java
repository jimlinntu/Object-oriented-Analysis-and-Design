package boundary;

import java.io.FileInputStream;

import controller.SearchReserve;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SearchReserveUI extends BaseUI{
	private SearchReserveUIFXMLController fxml_controller;
	private SearchReserve search_reserve_controller;
	
	
	public SearchReserveUI(SearchReserve search_reserve_controller) {
		// Set controller association
		this.search_reserve_controller = search_reserve_controller;
		// Load fxml
		FXMLLoader fxmlloader = new FXMLLoader();
		try{
			this.service_pane = fxmlloader.load(new FileInputStream("fxml/SearchReserve.fxml"));
			// Get service pane
			this.service_pane = (Pane)((Node)this.service_pane).lookup("#ServiceAnchorPane"); // Only get Service Anchor Pane
		}catch(Exception e) {
			e.printStackTrace();
		}
		// Load fxml controller
		this.fxml_controller = fxmlloader.getController();
	}
	protected void prepareActions() {
		
	}
}

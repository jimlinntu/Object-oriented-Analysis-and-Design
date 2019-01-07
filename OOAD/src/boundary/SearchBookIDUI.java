package boundary;

import java.io.FileInputStream;

import controller.SearchBookID;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SearchBookIDUI extends BaseUI{
	private SearchBookIDUIFXMLController fxml_controller;
	private SearchBookID search_bookid_controller;
	
	
	public SearchBookIDUI(SearchBookID search_bookid_controller) {
		// Set controller association
		this.search_bookid_controller = search_bookid_controller;
		// Load fxml
		FXMLLoader fxmlloader = new FXMLLoader();
		try{
			this.service_pane = fxmlloader.load(new FileInputStream("fxml/SearchBookID.fxml"));
			// Get service pane
			this.service_pane = (Pane)((Node)this.service_pane).lookup("#ServiceAnchorPane"); // Only get Service Anchor Pane
		}catch(Exception e) {
			e.printStackTrace();
		}
		// Load fxml controller
		this.fxml_controller = fxmlloader.getController();
	}
	protected void prepareActions() {
		//this.fxml_controller.startSearch
	}
}

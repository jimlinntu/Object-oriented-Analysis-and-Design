package boundary;

import java.io.FileInputStream;

import controller.SearchTrain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SearchTrainUI {
	private SearchTrainUIFXMLController fxml_controller;
	private SearchTrain search_train_controller;
	private Pane service_pane;
	
	public SearchTrainUI(SearchTrain search_train_controller) {
		// Set controller association
		this.search_train_controller = search_train_controller;		
		// Load fxml
		FXMLLoader fxmlloader = new FXMLLoader();
		try{
			this.service_pane = fxmlloader.load(new FileInputStream("fxml/SearchTrain.fxml"));
			// Get service pane
			this.service_pane = (Pane)((Node)this.service_pane).lookup("#ServiceAnchorPane"); // Only get Service Anchor Pane
		}catch(Exception e) {
			e.printStackTrace();
		}
		// Load fxml controller
		this.fxml_controller = fxmlloader.getController();
	}

	public void startInterface(Pane root_pane) {
		// Remove Original UI ServiceAnchorPane
		root_pane.getChildren().remove(root_pane.lookup("#ServiceAnchorPane"));
		// Insert BookTicketUI ServiceAnchorPane
		root_pane.getChildren().add(this.service_pane);
	}
}

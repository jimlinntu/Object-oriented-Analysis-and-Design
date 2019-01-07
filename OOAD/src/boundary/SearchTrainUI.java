package boundary;

import java.io.FileInputStream;

import controller.SearchTrain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SearchTrainUI extends BaseUI<SearchTrainUIFXMLController>{
	private SearchTrain search_train_controller;
	
	public SearchTrainUI(SearchTrain search_train_controller) {
		// Set controller association
		this.search_train_controller = search_train_controller;		
		// Load page
		this.loadView("fxml/SearchTrain.fxml");
	}
	
	protected void prepareActions() {
		
	}
}

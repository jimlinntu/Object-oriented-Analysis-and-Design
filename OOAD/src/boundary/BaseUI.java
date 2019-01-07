package boundary;

import java.io.FileInputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class BaseUI<T> { 
	protected T fxml_controller;
	protected Pane root_pane;
	protected Pane service_pane;
	
	// abstract method for creating action listener
	protected abstract void prepareActions();
	// General start interface function
	public void startInterface() {
		// Remove Original UI ServiceAnchorPane
		this.root_pane.getChildren().remove(this.root_pane.lookup("#ServiceAnchorPane"));
		// Insert ServiceAnchorPane
		this.root_pane.getChildren().add(this.service_pane);
	}
	
	public void loadView(String fxml_file) {
		// Load fxml
		FXMLLoader fxmlloader = new FXMLLoader();
		try{
			this.service_pane = fxmlloader.load(new FileInputStream(fxml_file));
			// Get service pane
			this.service_pane = (Pane)((Node)this.service_pane).lookup("#ServiceAnchorPane"); // Only get Service Anchor Pane
		}catch(Exception e) {
			e.printStackTrace();
		}
		// Load fxml controller
		this.fxml_controller = fxmlloader.getController();
	}
}

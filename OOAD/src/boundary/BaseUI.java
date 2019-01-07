package boundary;

import java.io.FileInputStream;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public abstract class BaseUI<T> { 
	private T fxml_controller;
	public Pane service_pane;
	
	// abstract method for creating action listener
	protected abstract void prepareActions();
	// General start interface function
	public void startInterface(Pane root_pane) {
		// Remove Original UI ServiceAnchorPane
		root_pane.getChildren().remove(root_pane.lookup("#ServiceAnchorPane"));
		// Insert BookTicketUI ServiceAnchorPane
		root_pane.getChildren().add(this.service_pane);
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

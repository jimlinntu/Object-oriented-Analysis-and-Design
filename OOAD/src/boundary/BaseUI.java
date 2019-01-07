package boundary;

import javafx.scene.layout.Pane;

public abstract class BaseUI { 
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
}

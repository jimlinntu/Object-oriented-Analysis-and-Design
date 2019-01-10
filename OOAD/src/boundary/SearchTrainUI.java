package boundary;

import java.io.FileInputStream;
import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

import controller.SearchTrain;
import entity.Info;
import entity.Train;

public class SearchTrainUI extends BaseUI<SearchTrainUIFXMLController>{
	private SearchTrain search_train_controller;
	
	public SearchTrainUI(SearchTrain search_train_controller, Pane root_pane) {
		// Set controller association
		this.search_train_controller = search_train_controller;		
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/SearchTrain.fxml");
	}
	
	private void showTrains(ArrayList<Train> trains) {
		//this.loadView("fxml/");
	}
	
	protected void prepareActions() {
		EventHandler<ActionEvent> inputSearchInfo = (event) -> {
			int[] ticketNum = {0, 0, 0, 0};
			Info info = new Info(
					"",			// userID
					ticketNum,
					true,		// use time as selector
					this.fxml_controller.date.getValue(),
					this.fxml_controller.time.getValue(),
					false,		// buyBack?
					this.fxml_controller.date.getValue(),
					"",
					this.fxml_controller.origin.getValue(),
					this.fxml_controller.dest.getValue(),
					"無",		// cartype
					"無",		// seattype
					false		// onlyShowEarly?
					);
			ArrayList<Train> trains = search_train_controller.searchTrain(info);
			this.showTrains(trains);
		};
		this.fxml_controller.search.setOnAction(inputSearchInfo);
	}
}

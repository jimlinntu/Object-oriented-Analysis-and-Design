package boundary;

import java.io.FileInputStream;
import java.time.LocalDate;

import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Arrays;

import controller.SearchTrain;
import entity.Info;
import entity.Station;
import entity.Train;

public class SearchTrainUI extends BaseUI<SearchTrainUIFXMLController>{
	private SearchTrain search_train_controller;
	
	public SearchTrainUI(SearchTrain search_train_controller, Pane root_pane) {
		// Set controller association
		this.search_train_controller = search_train_controller;		
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/SearchTrain.fxml");
		this.prepareActions();
	}
	
	private String checkIfValid(SearchTrainUIFXMLController fxml_controller) {
		String origin = fxml_controller.origin.getValue();
		String dest = fxml_controller.dest.getValue();
		if (origin == null || dest == null) {
			return "請選擇 起程/到達 站";
		}
		int origin_index = Arrays.asList(Station.CHI_NAME).indexOf(origin) ;
		int dest_index = Arrays.asList(Station.CHI_NAME).indexOf(dest);
		if (origin_index == dest_index) {
			return "起程站不可與到達站相同";
		}
		
		if (fxml_controller.date.getValue() == null) {
			return "請選擇 日期";
		}

		if (fxml_controller.time.getValue() == null) {
			return "請選擇 出發時間";
		}
		
		return null;
	}
	
	private void showTrains(ArrayList<Train> trains) {
		//this.loadView("fxml/");
	}
	
	protected void prepareActions() {
		EventHandler<ActionEvent> inputSearchInfo = (event) -> {
			String errorMessage = this.checkIfValid(this.fxml_controller);
			if (errorMessage == null) {
				this.fxml_controller.errorMessage.setVisible(false);
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
			}
			else {
				this.fxml_controller.errorMessage.setText(errorMessage);
				this.fxml_controller.errorMessage.setVisible(true);
			}
		};
		this.fxml_controller.search.setOnAction(inputSearchInfo);
	}
}

package boundary;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controller.SearchTrain;
import entity.Info;
import entity.Station;
import entity.Train;
import entity.TrainTime;

public class SearchTrainUI extends BaseUI<BaseFXMLController>{
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
	
	private void showTrains(List<TrainTime> train_times) {
		// TODO: ShowTrainTimeFXMLController  
		this.loadView("fxml/ShowTrainTime.fxml");
		ShowTrainTimeFXMLController fxml_controller = (ShowTrainTimeFXMLController)this.fxml_controller;
		
		List<String> train_times_string = new ArrayList<String>();
		for(TrainTime x: train_times) {
			train_times_string.add(x.toString());
		}
		
		fxml_controller.message.setText("以下是查詢到的列車時刻表");
		fxml_controller.listview.setItems(FXCollections.observableArrayList(train_times_string));
		this.startInterface();
	}
	
	protected void prepareActions() {
		SearchTrainUIFXMLController fxml_controller = (SearchTrainUIFXMLController)this.fxml_controller;
		
		EventHandler<ActionEvent> inputSearchInfo = (event) -> {
			String errorMessage = this.checkIfValid(fxml_controller);
			if (errorMessage == null) {
				fxml_controller.errorMessage.setVisible(false);
				int[] ticketNum = {0, 0, 0, 0};
				Info info = new Info(
						"",			// userID
						ticketNum,
						true,		// use time as selector
						fxml_controller.date.getValue(),
						fxml_controller.time.getValue(),
						false,		// buyBack?
						null, //backDate should be null
						"",
						fxml_controller.origin.getValue(),
						fxml_controller.dest.getValue(),
						"無",		// cartype
						"無",		// seattype
						false		// onlyShowEarly?
						);
				//List<TrainTime> train_times = search_train_controller.searchTrain(info);
				List<TrainTime> train_times = new ArrayList<TrainTime>();
				train_times.add(new TrainTime(1334, LocalDate.of(2019, 3, 24), LocalTime.of(4, 2), LocalTime.of(5, 4)));
				this.showTrains(train_times);
			}
			else {
				fxml_controller.errorMessage.setText(errorMessage);
				fxml_controller.errorMessage.setVisible(true);
			}
		};
		fxml_controller.search.setOnAction(inputSearchInfo);
	}
}

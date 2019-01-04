package boundary;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import controller.BookTicket;
import controller.GenerateTicket;

public class MainPage extends Application{
	
	private Pane pane;
	private Scene scene;
	private BookTicket book_ticket_controller;
	private GenerateTicket generate_ticket_controller;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public void start(Stage primaryStage) {
		primaryStage.setTitle("高鐵訂票系統");
		this.pane = new VBox();
		
		Button searchTrain = new Button("查詢車次時刻表");
		Button bookTicket = new Button("訂票"); 
		Button searchBookID = new Button("查詢訂位代號");
		Button searchReserve = new Button("查詢訂位紀錄");
		
		// handle user event
		searchTrain.setOnAction(event ->{
			// Initialize BookTicket controller
			book_ticket_controller = new BookTicket(this, generate_ticket_controller);
		});
		
		
		this.pane.getChildren().addAll(searchTrain, bookTicket, searchBookID, searchReserve);
		
		this.scene = new Scene(this.pane, 1000, 1000);
		primaryStage.setScene(this.scene);
		primaryStage.show();
		
	}
	
	public Scene getScene() {
		return this.scene;
	}
	
	public void restorePane() {
		this.scene.setRoot(this.pane);
		// Clear all controller
		this.book_ticket_controller = null;
	}

}

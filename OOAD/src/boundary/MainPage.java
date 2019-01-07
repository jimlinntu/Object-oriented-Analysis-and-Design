package boundary;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import controller.BookTicket;
import controller.GenerateTicket;
import controller.SearchTrain;
import controller.SearchBookID;
import controller.SearchReserve;

public class MainPage extends Application{
	
	private Scene scene; // Scene
	private Pane root_pane; // Root pane
	// Fxml controller
	private MainPageFXMLController fxml_controller;
	// Controller Section
	private BookTicket book_ticket_controller;
	private SearchTrain search_train_controller;
	private SearchBookID search_bookid_controller;
	private SearchReserve search_reserve_controller;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public void start(Stage primaryStage) throws IOException, RuntimeException{
		// Load fxml
		FXMLLoader fxmlloader = new FXMLLoader();
		this.root_pane = fxmlloader.load(new FileInputStream("fxml/MainPage.fxml"));
		this.fxml_controller = fxmlloader.getController();
		// Set title
		primaryStage.setTitle("高鐵訂票系統");
		// Prepare all actions 
		this.prepareActions();
		// Setup Scene
		this.scene = new Scene(this.root_pane);
		primaryStage.setScene(this.scene);
		primaryStage.show();
	}
	
	// Prepare all action
	private void prepareActions(){
		// 查詢車次時刻表
		this.fxml_controller.searchTrainItem.setOnAction(event->{
			this.search_train_controller = new SearchTrain(this);
		});
		// 訂票
		this.fxml_controller.bookTicketItem.setOnAction(event->{
			this.book_ticket_controller = new BookTicket(this);
		});
		
		// 查詢訂位紀錄
		this.fxml_controller.searchReserveItem.setOnAction(event->{
			this.search_reserve_controller = new SearchReserve(this);
		});
		
		// 查詢訂位代號
		this.fxml_controller.searchBookID.setOnAction(event->{
			this.search_bookid_controller = new SearchBookID(this);
		});
		
		// 回到主畫面
		this.fxml_controller.goToMenuItem.setOnAction(event->{
			this.goToMenu();
		});
	}
	
	private void clearControllers() {
		this.search_train_controller = null;
		this.book_ticket_controller = null;
		this.search_reserve_controller = null;
		this.search_bookid_controller = null;
	}
	// Get Root Pane on MainPage
	public Pane getRootPane() {
		return this.root_pane;
	}
	// Go Back to menu
	public void goToMenu() {
		// Remove Current ServiceAnchorPane
		this.root_pane.getChildren().remove(this.root_pane.lookup("#ServiceAnchorPane"));
		// Restore Menu ServiceAnchorPane
		this.root_pane.getChildren().add(this.fxml_controller.service_pane);
		// Clear all controller
		this.clearControllers();
	}

}

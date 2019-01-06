package boundary;
import controller.BookTicket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.*;
import javafx.collections.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class BookTicketUI {
	private BookTicketUIFXMLController fxml_controller;
	private BookTicket book_ticket_controller;
	private Pane service_pane;
	
	public BookTicketUI(BookTicket book_ticket_controller) {
		// Set controller association
		this.book_ticket_controller = book_ticket_controller;
		// Load fxml
		FXMLLoader fxmlloader = new FXMLLoader();
		try{
			this.service_pane = fxmlloader.load(new FileInputStream("fxml/BookTicket.fxml"));
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
	private void initializeStation(ComboBox<String> combobox) {
		ObservableList<String> items = FXCollections.observableArrayList("南港", "台北", "板橋", "桃園", "新竹", "苗栗", "台中", "彰化", "雲林", "嘉義", "台南", "左營"); 
		combobox.setItems(items);
	}
	private void initializeTicketNum(ComboBox<Integer> combobox) {
		Integer[] ticket_ints = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
		ObservableList<Integer> ticket_nums = FXCollections.observableArrayList(ticket_ints);
		combobox.setItems(ticket_nums);
	}
}

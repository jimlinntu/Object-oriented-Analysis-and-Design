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

public class BookTicketUI extends BaseUI<BookTicketUIFXMLController>{
	private BookTicket book_ticket_controller;
	
	public BookTicketUI(BookTicket book_ticket_controller, Pane root_pane) {
		// Set controller association
		this.book_ticket_controller = book_ticket_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/BookTicket.fxml");
	}
	
	protected void prepareActions() {
		
	}
}

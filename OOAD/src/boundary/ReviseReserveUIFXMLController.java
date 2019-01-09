package boundary;

import java.util.ArrayList;

import entity.Order;
import entity.Ticket;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class ReviseReserveUIFXMLController extends BaseFXMLController {

    @FXML
    protected MenuItem searchTrainItem;

    @FXML
    protected MenuItem bookTicketItem;

    @FXML
    protected MenuItem searchReserveItem;

    @FXML
    protected MenuItem searchBookID;

    @FXML
    protected MenuItem goToMenuItem;

    @FXML
    protected AnchorPane service_pane;

    @FXML
    protected Label origin;

    @FXML
    protected Label dest;

    @FXML
    protected Label userid;

    @FXML
    protected Label adultTicket;

    @FXML
    protected Label disableTicket;

    @FXML
    protected Label childTicket;

    @FXML
    protected Label seniorTicket;

    @FXML
    protected Button startSearch;

    @FXML
    protected DatePicker goDate;

    @FXML
    protected ComboBox<String> goTime;

    @FXML
    protected Pane idBookPane;

    @FXML
    protected TextField goTrainID;

    @FXML
    protected TextField backTrainID;

    @FXML
    protected RadioButton standard;

    @FXML
    protected ToggleGroup cartype;

    @FXML
    protected RadioButton business;

    @FXML
    protected RadioButton none_pref;

    @FXML
    protected ToggleGroup seattype;

    @FXML
    protected RadioButton window_seat;

    @FXML
    protected RadioButton aisle_seat;

    public void setInfo(Order order) {
    	this.adultTicket.setText(String.valueOf(order.adultTicket));
		this.childTicket.setText(String.valueOf(order.childTicket));
		this.disableTicket.setText(String.valueOf(order.disableTicket));
		this.seniorTicket.setText(String.valueOf(order.seniorTicket));
		this.origin.setText(order.origin);
		this.dest.setText(order.dest);
		this.userid.setText(order.userID);
		this.initializeTime(goTime);
    }
}

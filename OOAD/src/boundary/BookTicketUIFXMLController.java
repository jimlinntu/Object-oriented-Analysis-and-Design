package boundary;

import entity.Station;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class BookTicketUIFXMLController implements Initializable {
	@FXML
	public MenuItem goToMenu;
    @FXML
    public ComboBox<String> origin;
    @FXML
    public ComboBox<String> dest;
    @FXML
    public RadioButton standard;
    @FXML
    public ToggleGroup cartype;
    @FXML
    public RadioButton business;
    @FXML
    public RadioButton none_pref;
    @FXML
    public ToggleGroup seattype;
    @FXML
    public RadioButton window_seat;
    @FXML
    public RadioButton aisle_seat;
    @FXML
    public RadioButton timeBook;
    @FXML
    public ToggleGroup booktype;
    @FXML
    public RadioButton idBook;
    @FXML
    public DatePicker goDate;
    @FXML
    public CheckBox buyBack;
    @FXML
    public ComboBox<String> goTime;
    @FXML
    public DatePicker backDate;
    @FXML
    public ComboBox<String> backTime;
    @FXML
    public ComboBox<Integer> adultTicket;
    @FXML
    public ComboBox<Integer> childTicket;
    @FXML
    public ComboBox<Integer> disableTicket;
    @FXML
    public ComboBox<Integer> seniorTicket;
    @FXML
    public CheckBox OnlyshowEarly;
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        System.out.println(this.getClass().toString() + " loaded!");
        //
        this.initializeStation(this.origin);
        this.initializeStation(this.dest);
        // 
        this.initializeTicketNum(adultTicket);
        this.initializeTicketNum(childTicket);
        this.initializeTicketNum(disableTicket);
        this.initializeTicketNum(seniorTicket);
    }
    
    private void initializeTicketNum(ComboBox<Integer> combobox) {
		Integer[] ticket_ints = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
		ObservableList<Integer> ticket_nums = FXCollections.observableArrayList(ticket_ints);
		combobox.setItems(ticket_nums);
	}
    
    private void initializeStation(ComboBox<String> combobox) {
		ObservableList<String> items = FXCollections.observableArrayList(Station.CHI_NAME); 
		combobox.setItems(items);
	}
}

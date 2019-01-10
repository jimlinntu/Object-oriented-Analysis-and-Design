package boundary;

import entity.Station;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class BookTicketUIFXMLController extends BaseFXMLController implements Initializable {
	@FXML
    MenuItem goToMenu;
	
	@FXML
	AnchorPane pane;
	
	@FXML
	TextField userID;
	
    @FXML
    ComboBox<String> origin;

    @FXML
    ComboBox<String> dest;

    @FXML
    RadioButton standard;

    @FXML
    ToggleGroup cartype;

    @FXML
    RadioButton business;

    @FXML
    RadioButton none_pref;

    @FXML
    ToggleGroup seattype;

    @FXML
    RadioButton window_seat;

    @FXML
    RadioButton aisle_seat;
    
    @FXML
    Pane timeBookPane;
    
    @FXML
    Pane idBookPane;

    @FXML
    RadioButton timeBook;

    @FXML
    ToggleGroup booktype;

    @FXML
    RadioButton idBook;

    @FXML
    DatePicker goDate;

    @FXML
    CheckBox buyBack;

    @FXML
    ComboBox<String> goTime;

    @FXML
    Label leaveLabel1;

    @FXML
    DatePicker backDate;

    @FXML
    ComboBox<String> backTime;

    @FXML
    Label leaveLabel2;

    @FXML
    ComboBox<Integer> adultTicket;

    @FXML
    ComboBox<Integer> childTicket;

    @FXML
    ComboBox<Integer> disableTicket;

    @FXML
    ComboBox<Integer> seniorTicket;

    @FXML
    CheckBox onlyShowEarly;

    @FXML
    Label backLabel;

    @FXML
    Button startSearch;

    @FXML
    TextField goTrainID;
    
    @FXML
    TextField backTrainID;
    
    @FXML
    Label errorMessage;
    
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
        //
        this.initializeTime(goTime);
        this.initializeTime(backTime);
    }
    
    // switch between booking tickets by time or train_id
    @FXML
    void changeBookType(ActionEvent event) {
		boolean isTimeBook = this.timeBook.isSelected();
		System.out.println("function changeBookType: " + Boolean.toString(isTimeBook));
		
		this.timeBookPane.setVisible(isTimeBook);
		this.idBookPane.setVisible(!isTimeBook);
    };
    
    // show buy back option or not
    @FXML
    void changeBuyBack(ActionEvent event) {
    	boolean showBuyBack = this.buyBack.isSelected();
    	System.out.println("function changeBuyBack: " + Boolean.toString(showBuyBack));
    	
    	this.backDate.setDisable(!showBuyBack);
    	this.backTime.setDisable(!showBuyBack);
    	this.backLabel.setDisable(!showBuyBack);
    	this.leaveLabel2.setDisable(!showBuyBack);
    	this.backTrainID.setDisable(!showBuyBack);
    }
    
    void initializeTicketNum(ComboBox<Integer> combobox) {
		Integer[] ticket_ints = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
		ObservableList<Integer> ticket_nums = FXCollections.observableArrayList(ticket_ints);
		combobox.setItems(ticket_nums);
		// set default value to 0 for every ticket num
		combobox.getSelectionModel().selectFirst();
	}

    
    
}

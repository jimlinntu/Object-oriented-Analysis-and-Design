package boundary;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SearchBookIDUIFXMLController implements Initializable{

    @FXML
    private MenuItem searchTrainItem;

    @FXML
    private MenuItem bookTicketItem;

    @FXML
    private MenuItem searchReserveItem;

    @FXML
    private MenuItem searchBookID;

    @FXML
    private MenuItem goToMenuItem;

    @FXML
    private AnchorPane service_pane;

    @FXML
    private TextField userID;

    @FXML
    private ComboBox<String> origin;

    @FXML
    private ComboBox<String> dest;

    @FXML
    private DatePicker date;

    @FXML
    private TextField trainID;

    @FXML
    private Button startSearch;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        System.out.println(this.getClass().toString() + " loaded!");
    }
}

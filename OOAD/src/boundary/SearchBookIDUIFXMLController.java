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

public class SearchBookIDUIFXMLController extends BaseFXMLController implements Initializable{

    @FXML
    public MenuItem searchTrainItem;

    @FXML
    public MenuItem bookTicketItem;

    @FXML
    public MenuItem searchReserveItem;

    @FXML
    public MenuItem searchBookID;

    @FXML
    public MenuItem goToMenuItem;

    @FXML
    public AnchorPane service_pane;

    @FXML
    public TextField userID;

    @FXML
    public ComboBox<String> origin;

    @FXML
    public ComboBox<String> dest;

    @FXML
    public DatePicker date;

    @FXML
    public TextField trainID;

    @FXML
    public Button startSearch;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        System.out.println(this.getClass().toString() + " loaded!");
        //
        this.initializeStation(this.origin);
        this.initializeStation(this.dest);
    }
}

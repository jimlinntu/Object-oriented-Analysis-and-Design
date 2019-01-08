package boundary;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import entity.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class SearchTrainUIFXMLController extends BaseFXMLController implements Initializable{
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
    public ComboBox<String> origin;

    @FXML
    public ComboBox<String> dest;

    @FXML
    public DatePicker date;

    @FXML
    public ComboBox<String> time;

    @FXML
    public ComboBox<String> go_or_apart;

    @FXML
    public Button search;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        System.out.println(this.getClass().toString() + " loaded!");
        this.initializeTime(time);
        this.initializeStation(origin);
        this.initializeStation(dest);
        this.go_or_apart.getItems().addAll("出發", "抵達");
    }
}



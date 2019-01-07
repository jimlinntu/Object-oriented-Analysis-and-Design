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
    
    
    private void initializeTime(ComboBox<String> combobox) {
    	int start = 5 * 60;
    	ArrayList<String> list = new ArrayList<String>();
    	for(int i = 0; i < 38; i++) {
    		String date_string = String.format("%02d:%02d", start / 60, start % 60);
    		list.add(date_string);
    		start += 30;
    	}
    	combobox.setItems(FXCollections.observableArrayList(list));
    }
}



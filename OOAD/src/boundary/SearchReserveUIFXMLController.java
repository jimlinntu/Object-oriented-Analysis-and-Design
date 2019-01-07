package boundary;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SearchReserveUIFXMLController implements Initializable{

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
    private TextField bookid;

    @FXML
    private Button loginSearch;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        System.out.println(this.getClass().toString() + " loaded!");
    }
}


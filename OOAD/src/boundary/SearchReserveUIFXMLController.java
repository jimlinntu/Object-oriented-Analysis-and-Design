package boundary;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SearchReserveUIFXMLController extends BaseFXMLController implements Initializable{

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
    protected TextField userID;

    @FXML
    protected TextField bookid;

    @FXML
    protected Button loginSearch;

    @FXML
    public Label errorMessage;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        System.out.println(this.getClass().toString() + " loaded!");
    }
}


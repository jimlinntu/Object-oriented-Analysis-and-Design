package boundary;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class ShowTrainTimeFXMLController extends BaseFXMLController implements Initializable {
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
    public ListView<String> listview;
    
    @FXML
    public Label message;
    
    public void initialize(URL location, ResourceBundle resources) {
    	System.out.println(this.getClass().toString() + " loaded!");
    }

}

package boundary;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class GenerateTicketUIFXMLController {
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

}

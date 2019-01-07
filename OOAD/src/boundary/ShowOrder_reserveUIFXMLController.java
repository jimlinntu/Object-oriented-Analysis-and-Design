package boundary;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class ShowOrder_reserveUIFXMLController extends BaseFXMLController {

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
    protected Label origin;

    @FXML
    protected Label dest;

    @FXML
    protected Label orderid;

    @FXML
    protected Label userid;

    @FXML
    protected Label goTime;

    @FXML
    protected Label arriveTime;

    @FXML
    protected Label adultTicket;

    @FXML
    protected Label disableTicket;

    @FXML
    protected Label childTicket;

    @FXML
    protected Label seniorTicket;

    @FXML
    protected Label price;

    @FXML
    protected ListView<String> seats;

    @FXML
    protected Button confirm;

    @FXML
    protected Button revise;

}

package boundary;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import entity.Order;
import entity.Ticket;

public class ShowOrderUIFXMLController extends BaseFXMLController implements Initializable{

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
    
    // Declared in ShowOrder-result.fxml, used for displaying order status (確認刪除. 確認成功. 修改成功. etc.).
    @FXML
    protected Label result;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
        System.out.println(this.getClass().toString() + " loaded!");
    }
    
    // used in both SearchReserve and BookTicket use case
    public void setOrder(Order order) {
    	this.adultTicket.setText(String.valueOf(order.adultTicket));
		this.childTicket.setText(String.valueOf(order.childTicket));
		this.disableTicket.setText(String.valueOf(order.disableTicket));
		this.seniorTicket.setText(String.valueOf(order.seniorTicket));
		this.price.setText(String.valueOf(order.price));
		this.origin.setText(order.origin);
		this.dest.setText(order.dest);
		this.goTime.setText(order.goTime);
		this.arriveTime.setText(order.arriveTime);
		this.orderid.setText(order.orderID);
		this.userid.setText(order.userID);
		
		ArrayList<String>seats = new ArrayList<String>();
		for(Ticket t : order.ticketList) {
			seats.add(t.seat);
		}
		this.seats.setItems(FXCollections.observableArrayList(seats));
    }
    
    public void setResultText(String result) {
    	System.out.println(this.result);
    	this.result.setText(result);
    }
}

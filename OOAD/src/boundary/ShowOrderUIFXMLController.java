package boundary;

import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import entity.Order;
import entity.Station;
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
    	int[] ticket_num = order.getTicketNum();
    	double cost = order.getCost();
    	Pair<int[], LocalTime[]> pair = null;
    	try{
    		pair = order.getFromTo();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	int[] fromto = pair.getKey();
    	LocalTime[] times = pair.getValue();
    	int from = fromto[0], to = fromto[1];
    	LocalTime goDepart = times[0], goArrival = times[1];
    	
    	this.adultTicket.setText(String.valueOf(ticket_num[0]));
		this.childTicket.setText(String.valueOf(ticket_num[1]));
		this.disableTicket.setText(String.valueOf(ticket_num[2]));
		this.seniorTicket.setText(String.valueOf(ticket_num[3]));
		this.price.setText(String.valueOf(cost));
		this.origin.setText(Station.CHI_NAME[from]);
		this.dest.setText(Station.CHI_NAME[to]);
		this.goTime.setText(goDepart.toString());
		this.arriveTime.setText(goArrival.toString());
		this.orderid.setText(String.valueOf(order.getOrderId()));
		this.userid.setText(order.getUserId());
		
		List<String> seats = new ArrayList<String>();
		for(Ticket t : order.tickets) {
			seats.add(t.seat.seatId);
		}
		this.seats.setItems(FXCollections.observableArrayList(seats));
    }
    
    public void setResultText(String result) {
    	System.out.println(this.result);
    	this.result.setText(result);
    }
}

package boundary;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import controller.GenerateTicket;
import controller.ReviseReserve;
import entity.CarType;
import entity.DataAccessObject;
import entity.Info;
import entity.Order;
import entity.Station;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

public class ReviseReserveUI extends BaseUI<BaseFXMLController> {
	private ReviseReserve revise_reserve_controller;
	
	
	public ReviseReserveUI(ReviseReserve revise_reserve_controller, Pane root_pane) {
		// Set controller association
		this.revise_reserve_controller = revise_reserve_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/ReviseReserve.fxml");
		this.prepareActions();
	}
	protected void prepareActions() {
		
	}
	
	private void alert(String errorMessage) {
		Alert alert = new Alert(AlertType.NONE, errorMessage);
		alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
		alert.showAndWait();
	}
	public boolean showFixedInfo(Order order) {
		ReviseReserveUIFXMLController tmp = (ReviseReserveUIFXMLController)this.fxml_controller;
		tmp.setInfo(order);
		
		EventHandler<ActionEvent> selectReviseDateTime = (event)-> {
			GenerateTicket generate_ticket_controller = new GenerateTicket();
			
			// 
			Pair<int[], LocalTime[]> fromto_localtime = null; 
			try{
				fromto_localtime = order.getFromTo();
			}catch(Exception e) {
				e.printStackTrace();
			}
			int[] fromto = fromto_localtime.getKey();
			int from = fromto[0], to = fromto[1];
			LocalTime[] times = fromto_localtime.getValue();
			LocalDate[] dates = order.getDates();
			boolean buyBack = order.checkRoundTrip();
			
			CarType carType = order.tickets.get(0).seat.carType;
			
			
			Info info = new Info(
					order.getUserId(),
					order.getTicketNum(),
					true, // selectByTime
					dates[0], // goDate
					null, // goSelector
					buyBack, // buyBack
					dates[1], //backDate
					null, //backSelector
					Station.CHI_NAME[from], // origin
					Station.CHI_NAME[to], // destination
					CarType.carTypeToString(carType), // carType
					"無", // seatType (ignore)
					false // 早鳥
					);
			
			 Pair<Order, String> ret = generate_ticket_controller.generate(info);
			 Order new_order = ret.getKey();
			 String errorMessage = ret.getValue();
			 
			 if(new_order == null) {
				 this.alert(errorMessage);
				 return;// 如果他選的車次是空的
			 }else {
				// release old order
				 try{
					 DataAccessObject.removeOrder(order);
				 }catch(Exception e) {
					 e.printStackTrace();
					 return; // 沒有成功移掉 Order
				 }
				 // Write new order
				 try{
					 DataAccessObject.writeOrder(new_order);
				 }catch(Exception e) {
					 e.printStackTrace();
					 return; // 沒有成功寫入 Order 
				 }
			 }
			 // change view to ShowOrder-result
			 this.loadView("fxml/ShowOrder-result.fxml");
			 this.startInterface(); // Change view
			 ((ShowOrderUIFXMLController)this.fxml_controller)
			 	.setResultText("換票成功！" );
			
		};
		
		
		tmp.startSearch.setOnAction(selectReviseDateTime);
		
		
		return true;
	}
}
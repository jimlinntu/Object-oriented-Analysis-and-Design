package boundary;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Optional;

import controller.SearchReserve;
import entity.Order;
import entity.Ticket;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;

public class SearchReserveUI extends BaseUI<BaseFXMLController>{
	private SearchReserve search_reserve_controller;
	
	
	public SearchReserveUI(SearchReserve search_reserve_controller, Pane root_pane) {
		// Set controller association
		this.search_reserve_controller = search_reserve_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/SearchReserve.fxml");
		this.prepareActions();
	}
	// return error message
	private String checkIfValid(SearchReserveUIFXMLController tmp) {
		if(tmp.userID.getCharacters().toString().equals("")) {
			return "身分證字號不可留空";
		}
		if(tmp.bookid.getCharacters().toString().equals("")) {
			return "訂位代號不可留空";
		}
		return null;
	}
	
	protected void prepareActions() {
		SearchReserveUIFXMLController tmp = (SearchReserveUIFXMLController)this.fxml_controller;
		EventHandler<ActionEvent> searchbyOrderID = (event) -> {
			String errMessage = this.checkIfValid(tmp);
			if(errMessage == null) {
				String userID = tmp.userID.getCharacters().toString();
				String orderID = tmp.bookid.getCharacters().toString();
				System.out.println("ID loaded!");
				if(!this.showOrder(this.search_reserve_controller.searchOrder(userID, orderID))) {
					System.out.println("[!] Failed: showOrder fail");
				};
			}
			// else show error
			else {
				tmp.errorMessage.setVisible(true);
				tmp.errorMessage.setText(errMessage);
			}
		};
		tmp.loginSearch.setOnAction(searchbyOrderID);
	}
	private boolean showOrder(Order order) {
		this.loadView("fxml/ShowOrder.fxml");
		
		ShowOrderUIFXMLController tmp = (ShowOrderUIFXMLController)this.fxml_controller;
		tmp.setOrder(order);
		tmp.confirm.setText("退票");
		tmp.revise.setText("換票");
		
		try {
			this.startInterface();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		EventHandler<ActionEvent> selectDeletion = (event)->{
			if(this.confirmAction("取消")) {
				// delete order
				if(this.search_reserve_controller.releaseOrder(order)) {
					this.loadView("fxml/ShowOrder-result.fxml");
					((ShowOrderUIFXMLController)this.fxml_controller).setResultText("退票成功！");
					this.startInterface();
				}
			}
		};
		tmp.confirm.setOnAction(selectDeletion);
		
		EventHandler<ActionEvent> selectRevision = (event)->{
			if(this.confirmAction("修改")) {
				// send fixed options to controller -> new UI
				if(search_reserve_controller.reviseOrder(order)) {
					System.out.println("Reserve revision started!");
				}
			}
		};
		tmp.revise.setOnAction(selectRevision);
		
		return true;
	}
	private boolean confirmAction(String op) {
		final Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("高鐵訂票系統");
		alert.setHeaderText("");
		alert.setContentText("確定要將此訂票紀錄"+op+"嗎？");
		final Optional<ButtonType> opt = alert.showAndWait();
		final ButtonType rtn = opt.get();
		System.out.println("Reserve deletion confirmation: "+rtn);
		if (rtn == ButtonType.OK) {
		    return true;
		} else if(rtn == ButtonType.CANCEL){
		    return false;
		}
		return false;
	}
}

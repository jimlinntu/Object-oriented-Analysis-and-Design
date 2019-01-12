package boundary;
import controller.BookTicket;
import entity.Order;
import entity.Station;
import entity.Ticket;
import entity.Info;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.*;
import java.util.Optional;
import javafx.collections.*;
import javafx.util.Pair;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


public class BookTicketUI extends BaseUI<BaseFXMLController>{
	private BookTicket book_ticket_controller;
	//private Dialog<ButtonType> dialog;
	
	public BookTicketUI(BookTicket book_ticket_controller, Pane root_pane) {
		// Set controller association
		this.book_ticket_controller = book_ticket_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/BookTicket.fxml");
		// prepare Actions
		this.prepareActions();
	}
	
	private void alert(String errorMessage) {
		Alert alert = new Alert(AlertType.NONE, errorMessage);
		alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
		alert.showAndWait();
	}
	
	// return error message
	private String checkIfValid(BookTicketUIFXMLController fxml_controller) {
		boolean buyBack = fxml_controller.buyBack.isSelected();
		if (fxml_controller.userID.getCharacters().toString().equals("")) {
			return "身分證字號不可留空";
		}
		String origin = fxml_controller.origin.getValue();
		String dest = fxml_controller.dest.getValue();
		if (origin == null || dest == null) {
			return "請選擇 起程/到達 站";
		}
		int origin_index = Arrays.asList(Station.CHI_NAME).indexOf(origin) ;
		int dest_index = Arrays.asList(Station.CHI_NAME).indexOf(dest);
		if (origin_index == dest_index) {
			return "起程站不可與到達站相同";
		}
		
		if (fxml_controller.goDate.getValue() == null) {
			return "請選擇 去程日期";
		}
		
		if (buyBack && fxml_controller.backDate.getValue() == null) {
			return "請選擇 回程日期";
		}
		
		if (fxml_controller.timeBook.isSelected()) {
			if (fxml_controller.goTime.getValue() == null) {
				return "請選擇 出發時間";
			}
			if (buyBack && fxml_controller.backTime.getValue() == null) {
				return "請選擇 回程時間";
			}
		}
		else {
			if (fxml_controller.goTrainID.getText().equals("")) {
				return "請輸入 車次號碼";
			}
			else if (!fxml_controller.goTrainID.getText().chars().allMatch(Character::isDigit)) {
				return "車次號碼需是數字";
			}
			
			if (buyBack) {
				if (fxml_controller.backTrainID.getText().equals("")) {
					return "請選擇 車次號碼";
				}
				else if (!fxml_controller.backTrainID.getText().chars().allMatch(Character::isDigit)) {
					return "車次號碼需是數字";
				}
			}
		}

		if (fxml_controller.adultTicket.getValue() == 0 &&
			fxml_controller.childTicket.getValue() == 0 &&
			fxml_controller.disableTicket.getValue() == 0 &&
			fxml_controller.seniorTicket.getValue() == 0) {
			return "請選擇 票數";
		}
		
		return null;
	}
	
	protected void prepareActions() {
		BookTicketUIFXMLController tmp = (BookTicketUIFXMLController)this.fxml_controller;
		
		EventHandler<ActionEvent> inputTrainInfo = (event)->{
			
			System.out.println("inputTrainInfo is fired");
			
			String errorMessage = this.checkIfValid(tmp);
			//String errorMessage = null;
			if (errorMessage == null) {
				tmp.errorMessage.setVisible(false);
				
				String go_selector, back_selector;
				// if the user selects train by time => clear the fields of train ID
				if (tmp.timeBook.isSelected()) {
					go_selector = tmp.goTime.getValue();
					back_selector = tmp.backTime.getValue();
				}
				// if the user selects train by trainID => clear the fields of time
				else {
					go_selector = tmp.goTrainID.getText();
					back_selector = tmp.backTrainID.getText();
				}
				
				String cartype, seattype;
				cartype = ((RadioButton)(tmp.cartype.getSelectedToggle())).getText();
				seattype = ((RadioButton)(tmp.seattype.getSelectedToggle())).getText();
			
				int ticket_num[] = {tmp.adultTicket.getValue(), tmp.childTicket.getValue(), tmp.disableTicket.getValue(), tmp.seniorTicket.getValue()};
				
				Info info = new Info(
							tmp.userID.getText(),
							ticket_num,
							tmp.timeBook.isSelected(),
							tmp.goDate.getValue(),
							go_selector,
							tmp.buyBack.isSelected(),
							tmp.backDate.getValue(),
							back_selector,
							tmp.origin.getValue(),
							tmp.dest.getValue(),
							cartype,
							seattype,
							tmp.onlyShowEarly.isSelected()
						);

				Pair<Order, String> ret = this.book_ticket_controller.inputTrainInfo(info);
				Order order = ret.getKey();
				// or empty string?
				if (order != null) {
					if (!this.showOrder(order)) {
						System.out.println("[!] Failed: showOrder fail");
					};
				}
				else {
					alert(ret.getValue());
				}
			}
			else {
				tmp.errorMessage.setText(errorMessage);
				tmp.errorMessage.setVisible(true);
			}
		};

		tmp.startSearch.setOnAction(inputTrainInfo);
	}
	
	public boolean showOrder(Order order) {
		this.loadView("fxml/ShowOrder.fxml");
		ShowOrderUIFXMLController tmp = (ShowOrderUIFXMLController)this.fxml_controller;
		
		try {
			tmp.setOrder(order);
			// 把「修改」改成「取消」
			tmp.revise.setText("取消");
			
			this.startInterface();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		EventHandler<ActionEvent> confirmOrder = (event) -> {
			this.book_ticket_controller.confirmOrder();
			this.loadView("fxml/ShowOrder-result.fxml");
			((ShowOrderUIFXMLController)this.fxml_controller).setResultText("訂單確認成功！");
			this.startInterface();
		};
		tmp.confirm.setOnAction(confirmOrder);
		
		EventHandler<ActionEvent> rejectOrder = (event) -> {
			this.book_ticket_controller.rejectOrder();
			this.loadView("fxml/ShowOrder-result.fxml");
			((ShowOrderUIFXMLController)this.fxml_controller).setResultText("訂單取消！");
			this.startInterface();
		};
		tmp.revise.setOnAction(rejectOrder);
		
		return true;
	}
}

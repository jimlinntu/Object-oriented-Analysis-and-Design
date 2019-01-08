package boundary;
import controller.BookTicket;
import entity.Order;
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
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;


public class BookTicketUI extends BaseUI<BaseFXMLController>{
	private BookTicket book_ticket_controller;
	
	public BookTicketUI(BookTicket book_ticket_controller, Pane root_pane) {
		// Set controller association
		this.book_ticket_controller = book_ticket_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/BookTicket.fxml");
		// prepare Actions
		this.prepareActions();
	}
	
	protected void prepareActions() {
		BookTicketUIFXMLController tmp = (BookTicketUIFXMLController)this.fxml_controller;
		
		EventHandler<ActionEvent> inputTrainInfo = (event)->{
			
			System.out.println("inputTrainInfo is fired");
			
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

			Order order = this.book_ticket_controller.inputTrainInfo(info);
			
			if(!this.showOrder(order)) {
				System.out.println("[!] Failed: showOrder fail");
			};
			
		};

		tmp.startSearch.setOnAction(inputTrainInfo);
	}
	
	public boolean showOrder(Order order) {
		this.loadView("fxml/ShowOrder_reserve.fxml");
		
		ShowOrder_reserveUIFXMLController tmp = (ShowOrder_reserveUIFXMLController)this.fxml_controller;
		tmp.setOrder(order);
		// 把「修改」改成「取消」
		tmp.revise.setText("取消");
		
		try {
			this.startInterface();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		EventHandler<ActionEvent> confirmOrder = (event) -> {
			this.book_ticket_controller.confirmOrder();
			this.loadView("fxml/ShowOrder-result.fxml");
			((ShowOrder_reserveUIFXMLController)this.fxml_controller).setResultText("訂單確認成功！");
			this.startInterface();
		};
		tmp.confirm.setOnAction(confirmOrder);
		
		EventHandler<ActionEvent> rejectOrder = (event) -> {
			this.book_ticket_controller.rejectOrder();
			this.loadView("fxml/ShowOrder-result.fxml");
			((ShowOrder_reserveUIFXMLController)this.fxml_controller).setResultText("訂單取消！");
			this.startInterface();
		};
		tmp.revise.setOnAction(rejectOrder);
		
		return true;
	}
}

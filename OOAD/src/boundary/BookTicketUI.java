package boundary;
import controller.BookTicket;
import entity.Order;
import entity.Ticket;

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

		EventHandler<ActionEvent> inputTraininfo = (event)->{
			
			System.out.println("startSearch is fired");
			
			/*public class Info {
				public int userID;
				public int ticket_num;
				public int trainID;
				public int date;
				public String origin;
				public String destination;
				public String type;
				public double discount;
			}*/
			// TODO: null is for temporary test
			Order order = this.book_ticket_controller.inputTrainInfo(null);
			
			if(!this.showOrder(order)) {
				System.out.println("[!] Failed: showOrder fail");
			};
			
		};

		((BookTicketUIFXMLController)this.fxml_controller).startSearch.setOnAction(inputTraininfo);
	}
	
	public boolean showOrder(Order order) {
		this.loadView("fxml/ShowOrder_reserve.fxml");
		
		ShowOrder_reserveUIFXMLController tmp = (ShowOrder_reserveUIFXMLController)this.fxml_controller;
		tmp.setOrder(order);
		tmp.confirm.setText("確認");
		tmp.revise.setText("取消");
		
		try {
			this.startInterface();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}

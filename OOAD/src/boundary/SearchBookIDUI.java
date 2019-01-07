package boundary;

import java.io.FileInputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;

import controller.SearchBookID;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.StringConverter;
import entity.*;

public class SearchBookIDUI extends BaseUI<SearchBookIDUIFXMLController>{
	private SearchBookID search_bookid_controller;
	
	public SearchBookIDUI(SearchBookID search_bookid_controller, Pane root_pane) {
		// Set controller association
		this.search_bookid_controller = search_bookid_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/SearchBookID.fxml");
		this.prepareActions();
	}
	// return error message
	private String checkIfValid() {
		if(this.fxml_controller.userID.getCharacters().toString().equals("")) {
			return "身分證字號不可留空";
		}
		String origin = this.fxml_controller.origin.getValue();
		String dest = this.fxml_controller.dest.getValue();
		if(origin == null || dest == null) {
			return "請選擇 起程/到達 站";
		}
		int origin_index = Arrays.asList(Station.CHI_NAME).indexOf(origin) ;
		int dest_index = Arrays.asList(Station.CHI_NAME).indexOf(dest);
		if(origin_index == dest_index) {
			return "起程站不可與到達站為同一站";
		}
		
		if(this.fxml_controller.date.getValue() == null) {
			return "請選擇 發車日期";
		}
		if(this.fxml_controller.trainID.getCharacters().toString().equals("")) {
			return "請輸入 車次號碼";
		}
		return null;
	}
	
	private void showOrderIDs(ArrayList<Integer> orderid_list) {
		// change service pane
		this.loadView("fxml/SearchBookID-showOrders.fxml");
		//
		ArrayList<String> orderid_stringlist = new ArrayList<String>(orderid_list.size());
		for(Integer myInt: orderid_list) {
			orderid_stringlist.add(String.valueOf(myInt));
		}
		// Set list view Items
		this.fxml_controller.listView.setItems(FXCollections.observableArrayList(orderid_stringlist));
		this.startInterface(); // restart interface via different service pane
	}
	
	protected void prepareActions() {
		// 
		EventHandler<ActionEvent> searchbyUserID = (event) -> {
			String errMessage = this.checkIfValid();
			if(errMessage == null) {
				String userID = this.fxml_controller.userID.getCharacters().toString();
				System.out.println(userID);
				String origin = this.fxml_controller.origin.getValue().toString();
				System.out.println(origin);
				String dest = this.fxml_controller.dest.getValue().toString();
				System.out.println(dest);
				int date = Integer.parseInt(this.fxml_controller.date.getValue().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
				System.out.println(date);
				int trainID = Integer.parseInt(this.fxml_controller.trainID.getCharacters().toString());
				System.out.println(trainID);
				ArrayList<Integer> orderid_list = this.search_bookid_controller.searchOrderID(userID, origin, dest, date, trainID);
				this.showOrderIDs(orderid_list);
			}
			else {
				this.fxml_controller.errorMessage.setVisible(true);
				this.fxml_controller.errorMessage.setText(errMessage);
			}
		};
		// 
		this.fxml_controller.startSearch.setOnAction(searchbyUserID);
	}
}

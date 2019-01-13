package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import boundary.MainPage;
import boundary.SearchBookIDUI;
import entity.DataAccessObject;
import entity.Order;
import entity.Station;

public class SearchBookID {
	private MainPage main_page;
	private SearchBookIDUI search_bookid_ui;
	
	public SearchBookID(MainPage main_page) {
		this.main_page = main_page;
		this.search_bookid_ui = new SearchBookIDUI(this, main_page.getRootPane());

		try {
			this.search_bookid_ui.startInterface();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Integer> searchOrderID(String userID, String origin, String dest, LocalDate date, int trainID){
		int from = Station.chineseToIndex.get(origin);
		int to = Station.chineseToIndex.get(dest);
		
		List<Integer> orderIndices = new ArrayList<Integer>(); 
		try{
			 orderIndices = DataAccessObject.getOrderId(userID, from, to, date, trainID);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return orderIndices;
	}
}

package controller;

import java.util.ArrayList;

import boundary.MainPage;
import boundary.SearchBookIDUI;
import entity.DataAccessObject;
import entity.Order;

public class SearchBookID {
	private MainPage main_page;
	private SearchBookIDUI search_bookid_ui;
	private DataAccessObject dao;
	
	public SearchBookID(MainPage main_page, DataAccessObject dao) {
		this.main_page = main_page;
		this.search_bookid_ui = new SearchBookIDUI(this, main_page.getRootPane());
		this.dao = dao;
		try {
			this.search_bookid_ui.startInterface();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> searchOrderID(String userID, String origin, String dest, int date, int trainID){
		return this.dao.getOrderID(userID, origin, dest, date, trainID);
	}
}

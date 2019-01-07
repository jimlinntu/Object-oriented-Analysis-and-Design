package controller;

import boundary.MainPage;
import boundary.SearchReserveUI;
import entity.DataAccessObject;


public class SearchReserve {
	private MainPage main_page;
	private SearchReserveUI search_reserve_ui;
	private DataAccessObject dao;
	
	public SearchReserve(MainPage main_page, DataAccessObject dao) {
		this.main_page = main_page;
		this.search_reserve_ui = new SearchReserveUI(this, main_page.getRootPane());
		this.dao = dao;
		
		try {
			this.search_reserve_ui.startInterface();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

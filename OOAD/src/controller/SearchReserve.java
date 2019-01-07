package controller;

import boundary.MainPage;
import boundary.SearchReserveUI;


public class SearchReserve {
	private MainPage main_page;
	private SearchReserveUI search_reserve_ui;
	
	public SearchReserve(MainPage main_page) {
		this.main_page = main_page;
		this.search_reserve_ui = new SearchReserveUI(this);
		try {
			this.search_reserve_ui.startInterface(main_page.getRootPane());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

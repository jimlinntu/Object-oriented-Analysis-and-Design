package controller;

import boundary.MainPage;
import boundary.SearchBookIDUI;


public class SearchBookID {
	private MainPage main_page;
	private SearchBookIDUI search_bookid_ui;
	
	public SearchBookID(MainPage main_page) {
		this.main_page = main_page;
		this.search_bookid_ui = new SearchBookIDUI(this);
		try {
			this.search_bookid_ui.startInterface(main_page.getRootPane());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

package controller;

import boundary.MainPage;
import boundary.SearchTrainUI;


public class SearchTrain {
	private MainPage main_page;
	private SearchTrainUI search_train_ui;
	public SearchTrain(MainPage main_page) {
		this.main_page = main_page;
		this.search_train_ui = new SearchTrainUI(this);
		try{
			this.search_train_ui.startInterface(main_page.getRootPane());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

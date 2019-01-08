package controller;

import java.util.ArrayList;

import boundary.MainPage;
import boundary.SearchTrainUI;
import entity.DataAccessObject;
import entity.Train;
import entity.Info;


public class SearchTrain {
	private MainPage main_page;
	private SearchTrainUI search_train_ui;
	private DataAccessObject dao; 
	
	public SearchTrain(MainPage main_page, DataAccessObject dao) {
		this.main_page = main_page;
		this.search_train_ui = new SearchTrainUI(this, main_page.getRootPane());
		this.dao = dao;
		
		try{
			this.search_train_ui.startInterface();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Train> searchTrain(Info info) {
		return dao.listTrains(info);
	}
}

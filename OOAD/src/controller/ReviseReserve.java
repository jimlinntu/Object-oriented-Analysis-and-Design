package controller;

import boundary.MainPage;
import boundary.ReviseReserveUI;
import entity.DataAccessObject;
import entity.Order;

public class ReviseReserve {
	private ReviseReserveUI revise_reserve_ui;
	private MainPage main_page;
	private DataAccessObject dao;
	
	public ReviseReserve(MainPage main_page, DataAccessObject dao) {
		this.main_page = main_page;
		this.revise_reserve_ui = new ReviseReserveUI(this, main_page.getRootPane());
		this.dao = dao;
		
		try {
			this.revise_reserve_ui.startInterface();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected boolean showFixedInfo(Order order) {
		this.revise_reserve_ui.showFixedInfo(order);
		return true;
	}
}
package controller;

import boundary.MainPage;
import boundary.SearchReserveUI;
import entity.DataAccessObject;
import entity.Order;

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
	/**
	 * 
	 */
	public Order searchOrder(String userID, String orderID) {
		Order order = null;
		try{
			order = DataAccessObject.getOrder(Integer.parseInt(orderID));
			// if this userID does not match
			if(order.getUserId().equals(userID) == false) {
				order = null;
			}
		}
		// cannot find order
		catch(Exception e) {
			order = null;
		}
		return order;
	}
	public void goToMenu() {
		this.main_page.goToMenu();
	}
	public boolean releaseOrder(Order order) {
		// release order and discard from DB
		if(order.release()) {
			return true;
		}
		return false;
	}
	public boolean reviseOrder(Order order) {
		ReviseReserve revise_reserve_controller = new ReviseReserve(this.main_page, this.dao);
		revise_reserve_controller.showFixedInfo(order);
		return true;
	}
}

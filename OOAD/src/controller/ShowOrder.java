package controller;

import boundary.MainPage;
import boundary.ShowOrderUI;
import entity.DataAccessObject;
import entity.Order;

public class ShowOrder {
	private MainPage main_page;
	private ShowOrderUI showorder_ui;
	private DataAccessObject dao;
	
	public ShowOrder(MainPage main_page, DataAccessObject dao, Order order) {
		this.main_page = main_page;
		this.showorder_ui = new ShowOrderUI(this, main_page.getRootPane());
		this.dao = dao;
		
		this.showorder_ui.showOrder(order);
		try {
			this.showorder_ui.startInterface();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
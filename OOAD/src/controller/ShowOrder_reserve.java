package controller;

import boundary.MainPage;
import boundary.ShowOrder_reserveUI;
import entity.DataAccessObject;
import entity.Order;

public class ShowOrder_reserve {
	private MainPage main_page;
	private ShowOrder_reserveUI showorder_reserve_ui;
	private DataAccessObject dao;
	
	public ShowOrder_reserve(MainPage main_page, DataAccessObject dao, Order order) {
		this.main_page = main_page;
		this.showorder_reserve_ui = new ShowOrder_reserveUI(this, main_page.getRootPane());
		this.dao = dao;
		
		this.showorder_reserve_ui.showOrder(order);
		try {
			this.showorder_reserve_ui.startInterface();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
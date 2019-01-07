package boundary;

import java.io.FileInputStream;

import controller.SearchBookID;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class SearchBookIDUI extends BaseUI<SearchBookIDUIFXMLController>{
	private SearchBookID search_bookid_controller;
	
	
	public SearchBookIDUI(SearchBookID search_bookid_controller) {
		// Set controller association
		this.search_bookid_controller = search_bookid_controller;
		// Load page
		this.loadView("fxml/SearchBookID.fxml");
	}
	protected void prepareActions() {
		//this.fxml_controller.startSearch
	}
}

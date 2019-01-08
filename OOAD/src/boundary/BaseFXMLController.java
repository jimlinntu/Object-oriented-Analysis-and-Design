package boundary;

import java.util.ArrayList;

import entity.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class BaseFXMLController {
	protected void initializeStation(ComboBox<String> combobox) {
		ObservableList<String> items = FXCollections.observableArrayList(Station.CHI_NAME); 
		combobox.setItems(items);
	}
	protected void initializeTime(ComboBox<String> combobox) {
    	int start = 5 * 60;
    	ArrayList<String> list = new ArrayList<String>();
    	for(int i = 0; i < 38; i++) {
    		String date_string = String.format("%02d:%02d", start / 60, start % 60);
    		list.add(date_string);
    		start += 30;
    	}
    	combobox.setItems(FXCollections.observableArrayList(list));
    }
}

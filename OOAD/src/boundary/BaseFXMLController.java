package boundary;

import entity.Station;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class BaseFXMLController {
	public void initializeStation(ComboBox<String> combobox) {
		ObservableList<String> items = FXCollections.observableArrayList(Station.CHI_NAME); 
		combobox.setItems(items);
	}
}

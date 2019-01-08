package boundary;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Optional;

import controller.GenerateTicket;
import entity.Train;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.Pane;

public class GenerateTicketUI extends BaseUI<GenerateTicketUIFXMLController>{ 
	private GenerateTicket generate_ticket_controller;
	
	public GenerateTicketUI(GenerateTicket generate_ticket_controller, Pane root_pane) {
		// Set controller association
		this.generate_ticket_controller = generate_ticket_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/GenerateTicket.fxml");
		this.prepareActions();
	}
	
	/** 
	 * This inputTrainID function is a blocking Dialog, which wait for user select train id.
	 * @author jimlin
	 */
	public int selectTrain(ArrayList<Train> train_list) {
		Dialog<Integer> dialog = new Dialog<>();
		dialog.setHeaderText("請選擇車次");
		
		ArrayList<String> train_stringlist = new ArrayList<String>(train_list.size());
		for(Train train: train_list) {
			train_stringlist.add(train.toString());
		}
		// Set available train list 
		this.fxml_controller.listview.setItems(FXCollections.observableArrayList(train_stringlist));
		// Set DialogPane
		dialog.setDialogPane((DialogPane)this.service_pane);
		// Set 確認車次 button
		ButtonType confirm = new ButtonType("確認車次", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().add(confirm);
		// Custom Result Converter
		dialog.setResultConverter(dialogButton ->{
			if(dialogButton == confirm) {
				String select_train = this.fxml_controller.listview.getSelectionModel().getSelectedItem().toString(); 
				return Integer.parseInt(select_train);
			}
			else {
				return null;
			}
		});
		
		Optional<Integer> result = null;
		while(true) {
			result = dialog.showAndWait();
			if(result.isPresent()) {
				break;
			}
		}
		
		return result.get(); 
	}
	
	protected void prepareActions() {
	}
	
}

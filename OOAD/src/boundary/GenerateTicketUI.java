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
	private Dialog<Integer> dialog;
	
	public GenerateTicketUI(GenerateTicket generate_ticket_controller, Pane root_pane) {
		// Set controller association
		this.generate_ticket_controller = generate_ticket_controller;
		this.root_pane = root_pane;
		// Load page
		this.loadView("fxml/GenerateTicket.fxml");
		this.prepareActions();
		// Set up Dialog
		this.prepareDialog();
	}
	private void prepareDialog() {
		this.dialog = new Dialog<Integer>();
		this.dialog.setHeaderText("請選擇車次");
		// Set DialogPane
		this.dialog.setDialogPane((DialogPane)this.service_pane);
		// Set 確認車次 button
		ButtonType confirm = new ButtonType("確認車次", ButtonData.OK_DONE);
		this.dialog.getDialogPane().getButtonTypes().add(confirm);
		Node confirm_button = this.dialog.getDialogPane().lookupButton(confirm);
		confirm_button.setDisable(true);
		// Listen if a listview is choosen
		this.fxml_controller.listview.getSelectionModel().
			selectedItemProperty().addListener((observable, oldSelection, newSelection)->{
			if(newSelection != null) {
				confirm_button.setDisable(false);
			}
		});
		
		// Custom Result Converter
		this.dialog.setResultConverter(dialogButton ->{
			if(dialogButton == confirm) {
				String select_train = this.fxml_controller.listview.getSelectionModel().getSelectedItem();
				if(select_train == null) {
					return null;
				}
				return Integer.parseInt(select_train);
			}
			else {
				return null;
			}
		});
	}
	
	/** 
	 * This inputTrainID function is a blocking Dialog, which wait for user select train id.
	 * @author jimlin
	 */
	public int selectTrain(ArrayList<Train> train_list) {
		ArrayList<String> train_stringlist = new ArrayList<String>(train_list.size());
		for(Train train: train_list) {
			train_stringlist.add(train.toString());
		}
		// Set available train list 
		this.fxml_controller.listview.setItems(FXCollections.observableArrayList(train_stringlist));
		
		Optional<Integer> result = this.dialog.showAndWait();
		
		return result.get(); 
	}
	
	protected void prepareActions() {
	}
	
}

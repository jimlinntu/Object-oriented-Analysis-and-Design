package boundary;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class MainPageFXMLController implements Initializable{
	@FXML
    public MenuItem searchTrainItem;

    @FXML
    public MenuItem bookTicketItem;

    @FXML
    public MenuItem searchReserveItem;

    @FXML
    public MenuItem searchBookID;

    @FXML
    public MenuItem goToMenuItem;
    
    @FXML
    public AnchorPane service_pane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
        System.out.println(this.getClass().toString() + " loaded!");
    }
}

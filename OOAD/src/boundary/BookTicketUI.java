package boundary;
import controller.BookTicket;
import java.util.Arrays;
import java.util.stream.*;
import javafx.collections.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class BookTicketUI {
	private BookTicket book_ticket_controller;
	private GridPane root_pane;
	private Pane info_pane;
	private BorderPane goToMenuPane;
	private Pane origin_destination_pane;
	private Pane seat_type_pane;
	private Pane seat_side;
	private Pane ticket_num_pane;
	
	public BookTicketUI(BookTicket book_ticket_controller) {
		this.book_ticket_controller = book_ticket_controller;
	}
	public void startInterface(Scene scene) {
		root_pane = new GridPane();
		info_pane = new VBox();
		// 起訖站
		origin_destination_pane = new HBox();
		Label origin_destination_label = new Label("起訖站");
		ComboBox<String> buyStart = new ComboBox<String>();
		buyStart.setId("buyStart");
		ComboBox<String> buyEnd = new ComboBox<String>();
		buyEnd.setId("buyEnd");
		this.initializeStation(buyStart);
		this.initializeStation(buyEnd);
		origin_destination_pane.getChildren().addAll(origin_destination_label, buyStart, buyEnd);
		// 車廂種類
		seat_type_pane = new HBox();
		Label seat_type_label = new Label("車廂種類");
		ToggleGroup group = new ToggleGroup();
		RadioButton standard = new RadioButton("標準車廂");
		standard.setId("standard");
		RadioButton bussiness = new RadioButton("商務車廂");
		bussiness.setId("bussiness");
		standard.setToggleGroup(group);
		standard.setSelected(true);
		bussiness.setToggleGroup(group);
		seat_type_pane.getChildren().addAll(seat_type_label, standard, bussiness);
		// 座位喜好
		seat_side = new HBox();
		Label seat_side_label = new Label("座位喜好");
		group = new ToggleGroup();
		RadioButton no_prefer = new RadioButton("無");
		no_prefer.setId("no_prefer");
		RadioButton window = new RadioButton("靠窗優先");
		window.setId("window");
		RadioButton aisle = new RadioButton("靠走道優先");
		aisle.setId("aisle");
		no_prefer.setToggleGroup(group);
		no_prefer.setSelected(true);
		window.setToggleGroup(group);
		aisle.setToggleGroup(group);
		seat_side.getChildren().addAll(seat_side_label, no_prefer, window, aisle);
		// 票數
		ticket_num_pane = new HBox();
		Label ticket_num_label = new Label("票數");
		Label adult_ticket_label = new Label("全票");
		Label child_ticket_label = new Label("孩童票(6-11歲)");
		Label disabled_ticket_label = new Label("愛心票");
		Label senior_ticket_label = new Label("敬老票(65歲以上)");
		
		ComboBox<Integer> adult_ticket_num = new ComboBox<Integer>();
		ComboBox<Integer> child_ticket_num = new ComboBox<Integer>();
		ComboBox<Integer> disabled_ticket_num = new ComboBox<Integer>();
		ComboBox<Integer> senior_ticket_num = new ComboBox<Integer>();
		this.initializeTicketNum(adult_ticket_num);
		this.initializeTicketNum(child_ticket_num);
		this.initializeTicketNum(disabled_ticket_num);
		this.initializeTicketNum(senior_ticket_num);
		adult_ticket_num.setValue(0);
		child_ticket_num.setValue(0);
		disabled_ticket_num.setValue(0);
		senior_ticket_num.setValue(0);
		
		ticket_num_pane.getChildren().addAll(ticket_num_label, 
				adult_ticket_label, adult_ticket_num,
				child_ticket_label, child_ticket_num,
				disabled_ticket_label, disabled_ticket_num,
				senior_ticket_label, senior_ticket_num);
		// 
		goToMenuPane = new BorderPane();
		Button goToMenuButton = new Button("回到主選單");
		goToMenuButton.setOnAction(event ->{
			this.book_ticket_controller.goToMenu();
		});
		goToMenuPane.setBottom(goToMenuButton);
		// Gather All
		info_pane.getChildren().addAll(origin_destination_pane, seat_type_pane, seat_side, ticket_num_pane);
		
		root_pane.setMaxWidth(Double.NEGATIVE_INFINITY);
		root_pane.setMaxHeight(Double.NEGATIVE_INFINITY);
		GridPane.setHgrow(info_pane, Priority.ALWAYS);
		GridPane.setVgrow(goToMenuPane, Priority.ALWAYS);
		root_pane.add(info_pane, 0, 0);
		root_pane.add(goToMenuPane, 0, 1);
		root_pane.setGridLinesVisible(true);
		// Change Root 
		scene.setRoot(root_pane);
	}
	private void initializeStation(ComboBox<String> combobox) {
		ObservableList<String> items = FXCollections.observableArrayList("南港", "台北", "板橋", "桃園", "新竹", "苗栗", "台中", "彰化", "雲林", "嘉義", "台南", "左營"); 
		combobox.setItems(items);
	}
	private void initializeTicketNum(ComboBox<Integer> combobox) {
		Integer[] ticket_ints = IntStream.rangeClosed(0, 10).boxed().toArray(Integer[]::new);
		ObservableList<Integer> ticket_nums = FXCollections.observableArrayList(ticket_ints);
		combobox.setItems(ticket_nums);
	}
}

import System ;

package controller;

public class SearchTrain {
	private DAO data_object ;
	private SearchTrainUI ui ;
	
//	public SearchTrain(DAO data_object, SearchTrainUI ui) {
	public SearchTrain(DAO data_object) {
		this.data_object = data_object ;
//		this.ui = ui ;
	}
	
	// searchTrain -> search
	public void search(String train_id, int date, int start, int end) {
		Vector<Train> trains ;
		trains = data_object.listTrains(train_id, date, start, end) ;
		
		//ui.showTrains(trains) ;
		for (Train train: trains) {
			System.out.println(train.id) ;
		}
	}
	
}

package entity;

public class Info {
	public int userID;
	public int ticket_num;
	public int trainID;
	public int date;
	public String origin;
	public String destination;
	public String type;
	public double discount;
	
	public Info(int userID, int ticket_num, int trainID, int date, 
				String origin, String destination, String type, double discount) {
		this.userID = userID;
		this.ticket_num = ticket_num;
		this.trainID = trainID;
		this.date = date;
		this.origin = origin;
		this.destination = destination;
		this.type = type;
		this.discount = discount;
	}
}

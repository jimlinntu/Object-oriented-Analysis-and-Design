package entity;

public class Train {
	public int trainID;
	public Train(int trainID) {
		this.trainID = trainID;
	}
	public String toString() {
		return String.valueOf(this.trainID);
	}
}

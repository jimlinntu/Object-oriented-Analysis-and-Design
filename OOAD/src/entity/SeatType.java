package entity;

public enum SeatType {
	// 無, 靠窗優先, 走道優先
	NONE(0), WINDOW(1), AISLE(2);
	
	private final int value;
	private SeatType(int value) {
		this.value = value;
	}
	
	static public SeatType stringToSeatType(String seatTypeString) {
		if(seatTypeString == "無") {
			return NONE;
		}else if(seatTypeString == "靠窗優先") {
			return WINDOW; 
		}else if(seatTypeString == "走道優先") {
			return AISLE;
		}else {
			throw new RuntimeException("You should input 無/靠窗優先/走道優先");
		}
	}
	public int getValue() {
		return this.value;
	}
	
}

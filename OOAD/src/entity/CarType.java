package entity;

public enum CarType {
	// 標準、商務
	STANDARD(0), BUSINESS(1);
	
	private final int value;
	private CarType(int value) {
		this.value = value;
	}
	
	static public CarType stringToCarType(String carTypeString) {
		if(carTypeString == "標準車廂") {
			return STANDARD;
		}else if(carTypeString == "商務車廂") {
			return BUSINESS;
		}else {
			throw new RuntimeException("You need to input 標準車廂 or 商務車廂");
		}
	}
	
	public int getValue() {
		return this.value;
	}
}

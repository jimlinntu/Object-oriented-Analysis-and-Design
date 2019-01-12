package entity;

public enum CarType {
	// 標準、商務、無(就是指標準和商務都算, 實際上 無 應該只有在查詢時刻表會用到)
	STANDARD(0), BUSINESS(1), NONE(2);
	
	private final int value;
	private CarType(int value) {
		this.value = value;
	}
	
	static public CarType stringToCarType(String carTypeString) {
		if(carTypeString.equals("標準車廂")) {
			return STANDARD;
		}else if(carTypeString.equals("商務車廂")) {
			return BUSINESS;
		}else if(carTypeString.equals("無")) {
			return NONE;
		}
		else {
			throw new RuntimeException("You need to input 標準車廂 or 商務車廂 or 無");
		}
	}
	
	public int getValue() {
		return this.value;
	}
}

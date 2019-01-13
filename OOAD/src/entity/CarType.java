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
	
	static public String carTypeToString(CarType carType) {
		if(carType.equals(STANDARD)) {
			return "標準車廂";
		}else if(carType.equals(BUSINESS)) {
			return "商務車廂";
		}else if(carType.equals(NONE)){
			return "無";
		}else {
			assert false;
		}
		assert false;
		return "絕對不會走到這裡";
	}

    static public CarType of(int k)
    {
        switch(k) {
        case 0:
            return STANDARD;
        case 1:
            return BUSINESS;
        case 2:
            return NONE;
        default:
            throw new RuntimeException("You need to input 0~2");
        }
    }
	
	public int getValue() {
		return this.value;
	}
}

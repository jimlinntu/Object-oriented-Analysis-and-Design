package entity;

public enum TicketType {
	// 全票, 孩童票, 愛心票, 敬老票
	ADULT(0, 0), CHILD(1, 0.5), DISABLE(2, 0.5), SENIOR(3, 0.5);
	private double discount; 
	private int index;
	/**
	 * @author jimlin
	 * @param discount(0 ~ 1)
	 */
	private TicketType(int index, double discount) {
		this.discount = discount;
	}
	/**
	 * @return discount of a TicketType(ADULT: 0, CHILD: 0.5, DISABLE: 0.5, SENIOR: 0.5)
	 */
	public double getDiscount() {
		return this.discount;
	}
	/**
	 * @author jimlin
	 */
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * https://stackoverflow.com/questions/17492079/how-to-get-number-of-possible-item-of-an-enum
	 */
	public static int getLength() {
		return TicketType.values().length;
	}
	/**
	 * @author jimlin
	 * @param index(0: Adult, 1: Child, 2: Disable, 3: Senior)
	 * @return TicketType
	 */
	public static TicketType indexToTicketType(int index) {
		if(index == 0) {
			return ADULT;
		}else if(index == 1) {
			return CHILD;
		}else if(index == 2) {
			return DISABLE;
		}else if(index == 3) {
			return SENIOR;
		}else {
			assert false;
		}
		assert false;
		return null;
	}
    public static TicketType of(int k) {
        return indexToTicketType(k);
    }
    public int toInt() {
        return getIndex();
    }
}

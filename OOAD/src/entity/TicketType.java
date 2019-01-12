package entity;

public enum TicketType {
	// 全票, 孩童票, 愛心票, 敬老票
	ADULT(0), CHILD(0.5), DISABLE(0.5), SENIOR(0.5);
	private double discount; 
	/**
	 * @author jimlin
	 * @param discount(0 ~ 1)
	 */
	private TicketType(double discount) {
		this.discount = discount;
	}
	/**
	 * @return discount of a TicketType
	 */
	public double getDiscount() {
		return this.discount;
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
	
}

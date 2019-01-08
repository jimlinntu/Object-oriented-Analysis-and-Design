package entity;

import java.time.LocalDate;

public class Info {
	public String userID;
	public int[] ticketNum;

	// true: goSelector and backSelector are "time"
	// false: goSelector and backSelector are "trainID"
	public boolean selectByTime;
	
	public LocalDate goDate;
	public String goSelector;
	
	public boolean buyBack;
	// the following two attributes should be ignored if buyBack is false
	public LocalDate backDate;
	public String backSelector;
	
	public String origin;
	public String destination;
	
	// one of {"無", "標準車廂", "商務車廂"}
	public String cartype;
	// one of {"無", "靠窗優先", "走到優先"}
	public String seattype;
	
	public boolean onlyShowEarly;
	
	public Info(String userID, int[] ticketNum, boolean selectByTime, LocalDate goDate, String goSelector,
			boolean buyBack, LocalDate backDate, String backSelector,
			String origin, String destination, String cartype, String seattype, boolean onlyShowEarly) 
	{
		this.userID = userID;
		this.ticketNum = ticketNum;
		this.selectByTime = selectByTime;
		
		this.goDate = goDate;
		this.goSelector = goSelector;
		
		this.buyBack = buyBack;
		this.backDate = backDate;
		this.backSelector = backSelector;
		
		this.origin = origin;
		this.destination = origin;

		this.cartype = cartype;
		this.seattype = seattype;
		
		this.onlyShowEarly = onlyShowEarly;
	}
}

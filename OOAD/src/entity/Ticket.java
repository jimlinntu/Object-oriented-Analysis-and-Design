package entity;

public class Ticket {
    Seat seat;
    int from;
    int to;
    double cost;
    TicketType ticketType;
    
    private static final double busPrice[][] = {
		{},
		{260},
		{310, 260},
		{500, 440, 400},
		{700, 640, 590, 400},
		{920, 850, 800, 620, 410},
		{1330, 1250, 1210, 1010, 820, 610},
		{1510, 1430, 1390, 1210, 1010, 790, 400},
		{1660, 1600, 1550, 1370, 1160, 950, 550, 370},
		{1880, 1820, 1780, 1580, 1390, 1160, 770, 580, 430},
		{2290, 2230, 2180, 1990, 1790, 1580, 1180, 1000, 830, 620},
		{2500, 2440, 2390, 2200, 2000, 1790, 1390, 1210, 1040, 820, 410},
    };

    private static final double stdPrice[][] = {
		{},
		{40},
		{70, 40},
		{200, 160, 130},
		{330, 290, 260, 130},
		{480, 430, 400, 280, 140},
		{750, 700, 670, 540, 410, 270},
		{870, 820, 790, 670, 540, 390, 130},
		{970, 930, 900, 780, 640, 500, 230, 110},
		{1120, 1080, 1050, 920, 790, 640, 380, 250, 150},
		{1390, 1350, 1320, 1190, 1060, 920, 650, 530, 420, 280},
		{1530, 1490, 1460, 1330, 1200, 1060, 790, 670, 560, 410, 140},
    };
    /**
     * @author jimlin and yao
     * @param seat
     * @param from
     * @param to
     * @param type
     */
    public Ticket(Seat seat, int from, int to, TicketType type) {
        this.seat = seat;
        this.from = from;
        this.to = to;
        this.ticketType = type;
        assert from < to;
        // Buy Standard class cabin
        if(this.seat.carType == CarType.STANDARD) {
        	this.cost = this.stdPrice[from][to] * (1 - this.ticketType.getDiscount());
        }
        // Buy Bussiness class cabin
        else if(this.seat.carType == CarType.BUSINESS) {
        	this.cost = this.busPrice[from][to] * (1 - this.ticketType.getDiscount());
        }else {
        	assert false;
        }
    }
    /**
     * @author jimlin
     * @return TrainTime(this ticket's train time information)
     */
    public TrainTime getTrainTime() {
    	TrainTime trainTime = null;
    	try{
    		trainTime = this.seat.train.getTrainTime(this.from, this.to);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return trainTime;
    }
    
}

package entity;

public class Ticket {
    public Seat seat;
    public int from;
    public int to;
    double cost;
    TicketType ticketType;
    
    private static final double busPrice[][] = {
        {    0, 260, 310, 500, 700, 920,1330,1510,1660,1880,2290,2500 },
        {    0,   0, 260, 440, 640, 850,1250,1430,1600,1820,2230,2440 },
        {    0,   0,   0, 400, 590, 800,1210,1390,1550,1780,2180,2390 },
        {    0,   0,   0,   0, 400, 620,1010,1210,1370,1580,1990,2200 },
        {    0,   0,   0,   0,   0, 410, 820,1010,1160,1390,1790,2000 },
        {    0,   0,   0,   0,   0,   0, 610, 790, 950,1160,1580,1790 },
        {    0,   0,   0,   0,   0,   0,   0, 400, 550, 770,1180,1390 },
        {    0,   0,   0,   0,   0,   0,   0,   0, 370, 580,1000,1210 },
        {    0,   0,   0,   0,   0,   0,   0,   0,   0, 430, 830,1040 },
        {    0,   0,   0,   0,   0,   0,   0,   0,   0,   0, 620, 820 },
        {    0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0, 410 },
        {    0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0 },
    };

    private static final double stdPrice[][] = {
        {    0,  40,  70, 200, 330, 480, 750, 870, 970,1120,1390,1530 },
        {    0,   0,  40, 160, 290, 430, 700, 820, 930,1080,1350,1490 },
        {    0,   0,   0, 130, 260, 400, 670, 790, 900,1050,1320,1460 },
        {    0,   0,   0,   0, 130, 280, 540, 670, 780, 920,1190,1330 },
        {    0,   0,   0,   0,   0, 140, 410, 540, 640, 790,1060,1200 },
        {    0,   0,   0,   0,   0,   0, 270, 390, 500, 640, 920,1060 },
        {    0,   0,   0,   0,   0,   0,   0, 130, 230, 380, 650, 790 },
        {    0,   0,   0,   0,   0,   0,   0,   0, 110, 250, 530, 670 },
        {    0,   0,   0,   0,   0,   0,   0,   0,   0, 150, 420, 560 },
        {    0,   0,   0,   0,   0,   0,   0,   0,   0,   0, 280, 410 },
        {    0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0, 140 },
        {    0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0 },
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
    /**
     * @author jimlin
     */
    public double getCost() {
    	return this.cost;
    }
    /**
     * 
     */
    public TicketType getTicketType() {
    	return this.ticketType;
    }
    
}

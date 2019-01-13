package entity;
import java.util.*;
import java.sql.*;
import java.time.*;

public class DataAccessObject {
    static String url="jdbc:mysql://localhost:3306/ooad?useSSL=false&serverTimezone=UTC";
    static String user="ooad";
    static String pw="password";
    static Connection connection;
    /**
     * initialize database connectoion
     */
    public static void init() throws Exception {
        connection = DriverManager.getConnection(url,user,pw);
    }
	public static List<Integer> getOrderId(String userId, int from, int to,
            LocalDate date, int trainId) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement(
                "SELECT OrderId FROM Orders " +
                "WHERE UserId = ? " +
                "AND EXISTS ( " +
                "   SELECT OrderId FROM Tickets " +
                "   WHERE OrderId = Orders.OrderId " +
                "   AND TrainId = ? " +
                "   AND Date = ? " +
                "   AND FromStation = ? " +
                "   AND ToStation = ? " +
                "   LIMIT 1" +
                ") " +
                ";" );
        pstmt.setString(1,userId);
        pstmt.setInt(2,trainId);
        pstmt.setString(3,date.toString());
        pstmt.setInt(4,from);
        pstmt.setInt(5,to);
        ResultSet rs = pstmt.executeQuery();
        List<Integer> res = new ArrayList<Integer>();
        while(rs.next()) {
            res.add(Integer.valueOf(rs.getInt(1)));
        }
        return res;
	}
	/**
	 *  @return List<TrainTime> (All available train)
	 */
	public static List<TrainTime> listTrains(LocalDate date, int from, int to) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement(
                "SELECT t1.TrainId,t1.Time,t2.Time FROM ( " +
                "   SELECT TrainId,Time FROM Froms " + 
                "   WHERE Date = ? AND StationId = ? " +
                ") AS t1 INNER JOIN ( " +
                "   SELECT TrainId,Time FROM Tos " + 
                "   WHERE Date = ? AND StationId = ? " +
                ") AS t2 ON t1.TrainId = t2.TrainId " +
                ";" );
        pstmt.setString(1,date.toString());
        pstmt.setInt(2,from);
        pstmt.setString(3,date.toString());
        pstmt.setInt(4,to);
        ResultSet rs = pstmt.executeQuery();
        List<TrainTime> res = new ArrayList<TrainTime>();
        while(rs.next()) {
            res.add(new TrainTime(rs.getInt(1),date,
                    LocalTime.parse(rs.getString(2)),LocalTime.parse(rs.getString(3))));
        }
        return res;
	}
	
    public static Order getOrder(int orderId) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement(
                "SELECT t1.TrainId,t1.Date,t1.SeatId,t1.FromStation,t1.ToStation,t1.Type,t2.Type " +
                "FROM Tickets AS t1 " +
                "LEFT JOIN Seats AS t2 ON t1.SeatId = t2.SeatId " +
                "WHERE OrderId = ? " +
                ";" );
        pstmt.setInt(1,orderId);
        ResultSet rs = pstmt.executeQuery();
        List<Ticket> tickets = new ArrayList<Ticket>();
        while(rs.next()) {
            Train train = new Train(rs.getInt(1),LocalDate.parse(rs.getString(2)));
            Seat seat = new Seat(train,rs.getString(3),CarType.of(rs.getInt(7)));
            Ticket ticket = new Ticket(seat,rs.getInt(4),rs.getInt(5),TicketType.of(rs.getInt(6)));
            tickets.add(ticket);
        }
        pstmt = connection.prepareStatement(
                "SELECT UserId FROM Orders " +
                "WHERE OrderId = ? " +
                ";" );
        pstmt.setInt(1,orderId);
        rs = pstmt.executeQuery();
        rs.next();
        return new Order(orderId,rs.getString(1),tickets);
    }

    /**
     * write order to database.
     * database will auto assign an id to order.
     * and the function will write the id to the input object.
     */
	public static boolean writeOrder(Order order) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement(
                "LOCK TABLES Orders WRITE, Tickets WRITE " + 
                ";");

        pstmt.execute();

        pstmt = connection.prepareStatement(
                "SELECT SeatId FROM Tickets " +
                "WHERE TrainId = ? AND Date = ? AND SeatId = ? " +
                "AND FromStation < ? AND ToStation > ? " +
                "LIMIT 1 " +
                ";");

        ResultSet rs;

        for( Ticket it:order.tickets ) {
            pstmt.setInt(1,it.seat.train.trainId);
            pstmt.setString(2,it.seat.train.date.toString());
            pstmt.setString(3,it.seat.seatId);
            pstmt.setInt(4,it.to);
            pstmt.setInt(5,it.from);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                pstmt = connection.prepareStatement(
                    "UNLOCK TABLES " + 
                    ";");
                pstmt.execute();
                return false;
            }
        }

        pstmt = connection.prepareStatement(
                "INSERT INTO Orders (UserId) VALUE (?) " +
                ";" );
        pstmt.setString(1,order.userId);
        pstmt.execute();
        pstmt = connection.prepareStatement(
                "INSERT INTO Tickets VALUE (LAST_INSERT_ID(),?,?,?,?,?,?) " +
                ";" );
        for( Ticket it:order.tickets ) {
            pstmt.setInt(1,it.seat.train.trainId);
            pstmt.setString(2,it.seat.train.date.toString());
            pstmt.setString(3,it.seat.seatId);
            pstmt.setInt(4,it.from);
            pstmt.setInt(5,it.to);
            pstmt.setInt(6,it.ticketType.toInt());
            pstmt.execute();
        }
        
        pstmt = connection.prepareStatement(
                "SELECT LAST_INSERT_ID() " +
                ";" );
        rs = pstmt.executeQuery();
        rs.next();
        order.orderId = rs.getInt(1);

        pstmt = connection.prepareStatement(
                "UNLOCK TABLES " + 
                ";");
        pstmt.execute();
		return true;
	}
    public static boolean removeOrder( int orderId ) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement(
                "LOCK TABLES Orders WRITE, Tickets WRITE " + 
                ";");
        pstmt.execute();
        pstmt = connection.prepareStatement(
                "DELETE FROM Tickets WHERE OrderId = ? " +
                ";");
        pstmt.setInt(1,orderId);
        pstmt.execute();
        pstmt = connection.prepareStatement(
                "DELETE FROM Orders WHERE OrderId = ? " +
                ";");
        pstmt.setInt(1,orderId);
        pstmt.execute();
        pstmt = connection.prepareStatement(
                "UNLOCK TABLES " + 
                ";");
        pstmt.execute();
        return true;
    }
    public static boolean removeOrder(Order order) throws Exception {
        return removeOrder(order.orderId);
    }
    public static boolean changeOrder(Order order) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement(
                "LOCK TABLES Orders WRITE, Tickets WRITE " + 
                ";");

        pstmt.execute();

        pstmt = connection.prepareStatement(
                "SELECT SeatId FROM Tickets " +
                "WHERE TrainId = ? AND Date = ? AND SeatId = ? " +
                "AND FromStation < ? AND ToStation > ? " +
                "LIMIT 1 " +
                ";");

        ResultSet rs;

        for( Ticket it:order.tickets ) {
            pstmt.setInt(1,it.seat.train.trainId);
            pstmt.setString(2,it.seat.train.date.toString());
            pstmt.setString(3,it.seat.seatId);
            pstmt.setInt(4,it.to);
            pstmt.setInt(5,it.from);
            rs = pstmt.executeQuery();
            // if there exist a record collision -> you can not buy this ticket
            if(rs.next()) {
                pstmt = connection.prepareStatement(
                    "UNLOCK TABLES " + 
                    ";");
                pstmt.execute();
                return false;
            }
        }

        pstmt = connection.prepareStatement(
                "DELETE FROM Tickets WHERE OrderId = ? " +
                ";");
        pstmt.setInt(1,order.orderId);
        pstmt.execute();

        pstmt = connection.prepareStatement(
                "INSERT INTO Tickets VALUE (?,?,?,?,?,?,?) " +
                ";" );
        for( Ticket it:order.tickets ) {
            pstmt.setInt(1,order.orderId);
            pstmt.setInt(2,it.seat.train.trainId);
            pstmt.setString(3,it.seat.train.date.toString());
            pstmt.setString(4,it.seat.seatId);
            pstmt.setInt(5,it.from);
            pstmt.setInt(6,it.to);
            pstmt.setInt(7,it.ticketType.toInt());
            pstmt.execute();
        }

        pstmt = connection.prepareStatement(
                "UNLOCK TABLES " + 
                ";");
        pstmt.execute();
        return true;
    }
    public static List<Seat> getAvailableSeats(int trainId, LocalDate date, int from, int to) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement(
                "SELECT t1.SeatId,t1.Type FROM Seats AS t1 " +
                "LEFT JOIN ( " +
                "   SELECT SeatId FROM Tickets " +
                "   WHERE TrainId = ? AND Date = ? " +
                "   AND FromStation < ? AND ToStation > ? " +
                ") as t2 ON t1.SeatId = t2.SeatId " +
                "WHERE t2.SeatId IS NULL " +
                ";");
        pstmt.setInt(1,trainId);
        pstmt.setString(2,date.toString());
        pstmt.setInt(3,to);
        pstmt.setInt(4,from);
        ResultSet rs = pstmt.executeQuery();
        Train train = new Train(trainId,date);
        List<Seat> res = new ArrayList<Seat>();
        while(rs.next()) {
            res.add(new Seat(train,rs.getString(1),CarType.of(rs.getInt(2))));
        }
        return res;
    }
    public static List<Seat> getAvailableSeats(TrainTime trainTime) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement(
                "SELECT StationId FROM Froms " +
                "WHERE TrainId = ? AND Date = ? AND Time = ? " +
                ";");
        pstmt.setInt(1,trainTime.trainId);
        pstmt.setString(2,trainTime.date.toString());
        pstmt.setString(3,trainTime.fromTime.toString());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int from = rs.getInt(1);
        pstmt = connection.prepareStatement(
                "SELECT StationId FROM Tos " +
                "WHERE TrainId = ? AND Date = ? AND Time = ? " +
                ";");
        pstmt.setInt(1,trainTime.trainId);
        pstmt.setString(2,trainTime.date.toString());
        pstmt.setString(3,trainTime.toTime.toString());
        rs = pstmt.executeQuery();
        rs.next();
        int to = rs.getInt(1);
        return getAvailableSeats(trainTime.trainId,trainTime.date,from,to);
    };

    /**
     * TODO
     */
    public static TrainTime getTrainTime(Train train, int from, int to) throws Exception {
        PreparedStatement pstmt = connection.prepareStatement(
                "SELECT Froms.Time,Tos.Time " +
                "FROM Froms INNER JOIN Tos " +
                "ON Froms.TrainId = Tos.TrainId AND Froms.Date = Tos.Date " +
                "WHERE Froms.TrainId = ? AND Froms.Date = ? " +
                ";");
        pstmt.setInt(1,train.trainId);
        pstmt.setString(2,train.date.toString());
        ResultSet rs = pstmt.executeQuery();
        rs.next();
        return new TrainTime(train.trainId, train.date,
                LocalTime.parse(rs.getString(1)), LocalTime.parse(rs.getString(2)));
    }
    
    /**
     * test
     */
    public static void main(String []args) throws Exception {
        init();
        for( Integer it:getOrderId("OAO",0,1,LocalDate.of(2019,1,11),100) ) {
            System.out.printf("%d\n",it.intValue());
        }
        for( TrainTime it:listTrains(LocalDate.of(2019,1,11),0,1) ) {
            System.out.printf("%d %s %s %s\n",it.trainId,it.date.toString(),
                    it.fromTime.toString(),it.toTime.toString());
        }
        Order order = getOrder(1);
        for( Ticket it:order.tickets ) {
            System.out.printf("%d %s %s %d %d %d\n",it.seat.train.trainId,it.seat.train.date,
                    it.seat.seatId,it.from,it.to,it.ticketType.toInt());
        }
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(new Ticket(new Seat(new Train(100,LocalDate.of(2019,1,11)),"3",CarType.STANDARD),
                0,1,TicketType.ADULT));
        tickets.add(new Ticket(new Seat(new Train(100,LocalDate.of(2019,1,11)),"4",CarType.STANDARD),
                0,1,TicketType.ADULT));
        order = new Order(-1,"XD",tickets);
        if(writeOrder(order))
        {
            order = getOrder(order.orderId);
            System.out.printf("%d %s\n",order.orderId,order.userId);
            for( Ticket it:order.tickets ) {
                System.out.printf("%d %s %s %d %d %d\n",it.seat.train.trainId,it.seat.train.date,
                        it.seat.seatId,it.from,it.to,it.ticketType.toInt());
            }
            order.tickets.get(0).seat.seatId = "5";
            order.tickets.get(1).seat.seatId = "6";
            if(changeOrder(order)) {
                order = getOrder(order.orderId);
                System.out.printf("%d %s\n",order.orderId,order.userId);
                for( Ticket it:order.tickets ) {
                    System.out.printf("%d %s %s %d %d %d\n",it.seat.train.trainId,it.seat.train.date,
                            it.seat.seatId,it.from,it.to,it.ticketType.toInt());
                }
            }
            removeOrder(order);
        }
        for(Seat it:getAvailableSeats(new TrainTime(100,LocalDate.of(2019,1,11),
                LocalTime.of(0,0,0),LocalTime.of(1,0,0)))) {
            System.out.printf("%s\n",it.seatId);
        }
        TrainTime tt = getTrainTime(new Train(100,LocalDate.of(2019,1,11)),0,1);
        System.out.printf("%d %s %s %s\n",tt.trainId,tt.date,tt.fromTime,tt.toTime);
    }
}

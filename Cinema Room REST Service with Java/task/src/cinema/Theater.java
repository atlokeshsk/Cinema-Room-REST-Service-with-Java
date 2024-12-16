package cinema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Theater {
    private int rows;
    private int columns;
    private List<Seat> seats;
    private Map<String,Seat> tickets;
    private int income;
    private int available;
    private int purchased;

    public static Theater getNew(int row, int column) {
        List<Seat> seats = new ArrayList<>(row * column);
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                Seat seat;
                if (i <= 4) {
                    seat = new Seat(i, j, 10);
                } else {
                    seat = new Seat(i, j, 8);
                }
                seats.add(seat);
            }
        }
        return new Theater(row, column, seats, new HashMap<String,Seat>());
    }

    public Theater(int row, int column, List<Seat> seats, Map<String,Seat> tickets) {
        this.rows = row;
        this.columns = column;
        this.seats = seats;
        this.tickets = tickets;
        available = row*column;
        purchased = 0;
        income = 0;
    }

    public void purchase(String token,Seat seat){
        seat.setBooked(true);
        tickets.put(token, seat);
        purchased++;
        available--;
        income+=seat.getPrice();
    }

    public Seat returnTicket(String token){
        var seat = tickets.remove(token);
        seat.setBooked(false);
        purchased--;
        available++;
        income-=seat.getPrice();
        return seat;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Map<String, Seat> getTickets() {
        return tickets;
    }

    public void setTickets(Map<String, Seat> tickets) {
        this.tickets = tickets;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }
}

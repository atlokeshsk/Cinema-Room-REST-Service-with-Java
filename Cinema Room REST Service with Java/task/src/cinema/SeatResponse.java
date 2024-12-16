package cinema;

import jakarta.validation.constraints.Min;

import java.util.List;

public class SeatResponse {

    @Min(1)
    int rows;
    int columns;
    List<Seat> seats;

    public SeatResponse(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
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
}

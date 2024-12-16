package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

class Seat {

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
        booked = false;
    }

    private int row;
    private int column;
    private int price;

    @JsonIgnore
    private boolean booked;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}

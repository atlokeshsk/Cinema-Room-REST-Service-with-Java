package cinema;

public class StatsResponse {
    int income;
    int available;
    int purchased;

    public StatsResponse(int purchased, int available, int income) {
        this.purchased = purchased;
        this.available = available;
        this.income = income;
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

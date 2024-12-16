package cinema;

import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

class PurchaseRequestBody {

    @Range(min = 1, max = 9, message = "The number of a row or a column is out of bounds!")
    int row;

    @Range(min = 1, max = 9, message = "The number of a row or a column is out of bounds!")
    int column;

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

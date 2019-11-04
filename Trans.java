import java.text.SimpleDateFormat;
import java.util.Calendar;

class Trans {

    Calendar calendar;
    SimpleDateFormat dateFormat;

    int transactionID;
    Car car;
    String salesPerson;
    String type;
    double salePrice;

    public Trans(int transactionID, Calendar calendar, Car car, String salesPerson, String type,
            double salePrice) {
        this.calendar = calendar;
        this.transactionID = transactionID;
        this.car = car;
        this.salesPerson = salesPerson;
        this.type = type;
        this.salePrice = salePrice;
    }

    public String display() {
        dateFormat = new SimpleDateFormat("EEE, MMM dd, YYYY");
        String date = "ID: " + transactionID + " " + dateFormat.format(calendar.getTime()) + " " + type
                + " SalesPerson: " + salesPerson + " Car: ";
        return date;
    }

    public int getYear() {
        return calendar.get(calendar.YEAR);
    }

    public int getMonth() {
        return calendar.get(calendar.MONTH);
    }

    public int getDay() {
        return calendar.get(calendar.DAY_OF_MONTH);
    }

    public String getSalesPerson() {
        return salesPerson;
    }

    public String getType() {
        return type;
    }

    public Car getCar() {
        return car;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public int getTransactionID() {
        return transactionID;
    }
}

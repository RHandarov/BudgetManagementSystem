package log;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private String name;
    private Date date;
    private LogType type;
    private BigDecimal moneyAmount;

    public Log(String name, String date, char type, double amount) throws ParseException {
        this.setName(name);
        this.setDate(date);
        this.setType(type);
        this.setMoneyAmount(amount);
    }

    private void setName(String name) {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name should not be empty!");
        }

        this.name = name;
    }

    private void setDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.date = format.parse(date);
    }

    private void setType(char type) {
        if (type == 'P') {
            this.type = LogType.PROFIT;
        } else if (type == 'E') {
            this.type = LogType.EXPENSE;
        } else {
            throw new IllegalArgumentException("Provide valid record type (P or E)!");
        }
    }

    private void setMoneyAmount(double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Amount of money should be positive number!");
        }

        this.moneyAmount = BigDecimal.valueOf(amount);
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public LogType getType() {
        return type;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public String getDateAsString() {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(this.date);
    }

    public String getDay() {
        return this.getDateAsString().split("-")[0];
    }
}

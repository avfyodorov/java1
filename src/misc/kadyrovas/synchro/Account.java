package misc.kadyrovas.synchro;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {

    private String id;
    private String holder;
    private Date date;
    private double amount;
    private int pin;
    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");

    public Account(String id, int pin){
        this.id = id;
        this.pin = pin;
    }
    public Account() {
        this.id ="";
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return  "id='" + id + '\'' +
                ", holder='" + holder + '\'' +
                ", date=" + simpleDateFormat.format(date) +
                ", amount=" + amount +
                ", pin=" + pin +"\n";
    }
}
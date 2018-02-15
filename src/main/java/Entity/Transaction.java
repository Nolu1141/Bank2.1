package Entity;


import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="Transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userFrom_id")
    private User userFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userTo_id")
    private User userTo;

    @Column
    private Date date;

    @Enumerated(EnumType.ORDINAL)
    private MyCurrency currency;

    @Column
    private double amount;

    public Transaction() {
        this.date = new Date();
    }

    public Transaction(User userFrom, User userTo, MyCurrency currency, double amount) {
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.date = new Date();
        this.currency = currency;
        this.amount = amount;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public MyCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(MyCurrency currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", date=" + date +
                ", currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}

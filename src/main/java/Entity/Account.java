package Entity;

import javax.persistence.*;

@Entity
@Table(name="Accounts")
public class Account {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.ORDINAL)
    private MyCurrency currency;

    @Column
    private double amount;

    public Account() {
    }

    public Account(User user, MyCurrency currency, double amount) {
        this.user = user;
        this.currency = currency;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "Account{" +
                "user=" + user +
                ", currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}

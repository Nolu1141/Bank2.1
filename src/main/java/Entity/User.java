package Entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;
    @Column
    private String mail;
    @Column
    private String phone;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<Account>();

    @OneToMany(mappedBy = "userFrom", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public User() {
    }

    public User(String name, String mail, String phone) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
    }

    public User(String name, String mail, String phone, List<Account> accounts, List<Transaction> transactions) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.accounts = accounts;
        this.transactions = transactions;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

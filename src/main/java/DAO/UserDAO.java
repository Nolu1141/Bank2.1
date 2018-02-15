package DAO;

import Entity.MyCurrency;
import Entity.User;

public interface UserDAO {
    void addUser(User user);
    void addMoney(User user, double amount, MyCurrency currency);
    void doTransaction(User userFrom, User userTo, MyCurrency currency, double amount);
    void currencyConvert(User user, MyCurrency currencyFrom, MyCurrency currencyTo, double amount);
    String getAllAmounts (User user);
}

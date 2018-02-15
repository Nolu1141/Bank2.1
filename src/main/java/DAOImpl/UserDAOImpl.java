package DAOImpl;

import DAO.CurrencyRateDAO;
import DAO.TransactionDAO;
import DAO.UserDAO;
import Entity.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;


    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addUser(User user) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();

        }
    }

    @Override
    public void addMoney(User user, double amount, MyCurrency currency) {
        try {
            entityManager.getTransaction().begin();
            Account acc = getAcc(user, currency);
            acc.setAmount(acc.getAmount() + amount);
            entityManager.merge(acc);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void doTransaction(User userFrom, User userTo, MyCurrency currency, double amount) {
        try {
            entityManager.getTransaction().begin();
            Account accFrom = getAcc(userFrom, currency);
            accFrom.setAmount(accFrom.getAmount() - amount);
            entityManager.merge(accFrom);
            Account accTo = getAcc(userTo, currency);
            accTo.setAmount(accTo.getAmount() + amount);
            entityManager.merge(accTo);
            entityManager.persist(new Transaction(userFrom, userTo, currency, amount));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void currencyConvert(User user,MyCurrency currencyFrom, MyCurrency currencyTo, double amount) {
        try {
            entityManager.getTransaction().begin();
            Account accountFrom = getAcc(user, currencyFrom);
            accountFrom.setAmount(accountFrom.getAmount() - amount);
            entityManager.merge(accountFrom);
            double result = amount * convert(currencyFrom, currencyTo);
            Account accountTo = getAcc(user, currencyTo);
            accountTo.setAmount(accountTo.getAmount() + result);
            entityManager.merge(accountTo);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public String getAllAmounts(User user) {
        List<Account> accounts = new ArrayList<>();
        Query query = entityManager.createQuery("from Account a where a.user.name = :name");
        query.setParameter("name", user.getName());
        accounts.addAll(query.getResultList());
        return accounts.toString();
    }

    private Account getAcc(User user, MyCurrency currency) {
        List<Account> accounts = new ArrayList<>();
        Query query = entityManager.createQuery("from Account a where user.id = :id and a.currency = :curr ");
        query.setParameter("id", user.getId());
        System.out.println(user.getId());
        query.setParameter("curr", currency);
        accounts.addAll(query.getResultList());
        Account acc = accounts.get(0);
        return acc;
    }

    private double convert(MyCurrency currencyFrom, MyCurrency currencyTo){
        Query query = entityManager.createQuery("SELECT r FROM MyCurrencyRate r WHERE r.id = 1");
        MyCurrencyRate r = (MyCurrencyRate) query.getSingleResult();
        if ((currencyFrom == MyCurrency.UAH)&(currencyTo==MyCurrency.USD)){return r.getUAHRate()/r.getUSDRate();}
        if ((currencyFrom == MyCurrency.UAH)&(currencyTo==MyCurrency.EUR)){return r.getUAHRate()/r.getEURRate();}
        if ((currencyFrom == MyCurrency.EUR)&(currencyTo==MyCurrency.USD)){return r.getEURRate()/r.getUSDRate();}
        if ((currencyFrom == MyCurrency.EUR)&(currencyTo==MyCurrency.UAH)){return r.getEURRate()/r.getUAHRate();}
        if ((currencyFrom == MyCurrency.USD)&(currencyTo==MyCurrency.UAH)){return r.getUSDRate()/r.getUAHRate();}
        if ((currencyFrom == MyCurrency.USD)&(currencyTo==MyCurrency.EUR)){return r.getUSDRate()/r.getEURRate();}
        return 1;
    }

}

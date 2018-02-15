import DAO.AccauntDAO;
import DAO.CurrencyRateDAO;
import DAO.UserDAO;
import DAOImpl.AccauntDAOImpl;
import DAOImpl.CurrencyRateDAOImpl;
import DAOImpl.UserDAOImpl;
import Entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Bank2Test");
        EntityManager em = emf.createEntityManager();
        System.out.println("Go!");

        User user1 = new User("Alex", "a@ukr.net", "+38055555" );
        User user2 = new User("Mike", "m@ukr.net", "+38066666" );
        Account account1U1 = new Account(user1, MyCurrency.UAH, 2000);
        Account account2U1 = new Account(user1, MyCurrency.USD, 2000);
        Account account1U2 = new Account(user2, MyCurrency.UAH, 1000);
        Account account2U2 = new Account(user2, MyCurrency.USD, 1000);

        user1.addAccount(account1U1);
        user1.addAccount(account2U1);
        user2.addAccount(account1U2);
        user2.addAccount(account2U2);

        UserDAO userDAO = new UserDAOImpl(em);
        AccauntDAO accauntDao = new AccauntDAOImpl(em);
        CurrencyRateDAO currencyRateDAO = new CurrencyRateDAOImpl(em);

        userDAO.addUser(user1);
        userDAO.addUser(user2);
        accauntDao.addAccount(account1U1);
        accauntDao.addAccount(account2U1);
        accauntDao.addAccount(account1U2);
        accauntDao.addAccount(account2U2);


        userDAO.doTransaction(user1, user2, MyCurrency.UAH ,500);

        System.out.println(userDAO.getAllAmounts(user2));
        System.out.println("Аккаунт до пополнения " + account2U1);
        userDAO.addMoney(user1, 100, MyCurrency.USD);
        System.out.println("Аккаунт после пополнения " + account2U1);


        MyCurrencyRate currencyRate = new MyCurrencyRate(1, 27, 32);
        currencyRateDAO.addCurrencyRate(currencyRate);

        userDAO.currencyConvert(user1, MyCurrency.USD, MyCurrency.UAH, 200);
        userDAO.currencyConvert(user2, MyCurrency.UAH, MyCurrency.USD, 27);

        System.out.println("End");

        em.close();
    }
}

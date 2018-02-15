package DAOImpl;


import DAO.CurrencyRateDAO;
import Entity.MyCurrencyRate;

import javax.persistence.EntityManager;

public class CurrencyRateDAOImpl implements CurrencyRateDAO {
    private EntityManager entityManager;

    public CurrencyRateDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addCurrencyRate(MyCurrencyRate currencyRate) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(currencyRate);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}

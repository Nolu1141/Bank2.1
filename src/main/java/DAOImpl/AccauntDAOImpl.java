package DAOImpl;


import DAO.AccauntDAO;
import Entity.Account;

import javax.persistence.EntityManager;

public class AccauntDAOImpl implements AccauntDAO{

    private EntityManager entityManager;

    public AccauntDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addAccount(Account account) {

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(account);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}

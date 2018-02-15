package DAOImpl;

import DAO.TransactionDAO;
import Entity.Transaction;

import javax.persistence.EntityManager;


public class TransactionDAOImpl implements TransactionDAO {

    private EntityManager entityManager;

    public TransactionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(transaction);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
}

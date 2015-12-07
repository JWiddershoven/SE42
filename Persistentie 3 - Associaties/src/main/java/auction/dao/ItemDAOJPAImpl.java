/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.dao;

import auction.domain.Item;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Jelle
 */
public class ItemDAOJPAImpl implements ItemDAO {

    private final EntityManager em;
    private final EntityTransaction tx;

    public ItemDAOJPAImpl(EntityManager em) {
        this.em = em;
        this.tx = em.getTransaction();
    }

    @Override
    public int count() {
        Query q = em.createNamedQuery("Item.count", Item.class);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void create(Item item) {
        if (find(item.getId()) != null) {
            throw new EntityExistsException();
        }

        tx.begin();
        try {
            em.persist(item);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    @Override
    public void edit(Item item) {
        if (find(item.getId()) == null) {
            throw new IllegalArgumentException();
        }

        tx.begin();
        try {
            em.merge(item);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    @Override
    public Item find(Long id) {
        Query q = em.createNamedQuery("Item.findByID", Item.class);
        q.setParameter("ID", id);
        try {
            Item item = (Item) q.getSingleResult();
            return item;
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Item> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Item.class));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Item> findByDescription(String description) {
        Query q = em.createNamedQuery("Item.findByDescription", Item.class);
        q.setParameter("description", description);
        try {
            return (List<Item>) q.getResultList();           
            
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public void remove(Item item) {
        tx.begin();
        try {
            em.remove(em.merge(item));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

}

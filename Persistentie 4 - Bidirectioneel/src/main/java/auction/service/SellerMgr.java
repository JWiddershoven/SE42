package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import auction.domain.Category;
import auction.domain.Item;
import auction.domain.User;
import javax.persistence.EntityManager;

public class SellerMgr {

    private final EntityManager em;

    public SellerMgr(EntityManager em) {
        this.em = em;
    }
    
    /**
     * @param seller
     * @param cat
     * @param description
     * @return het item aangeboden door seller, behorende tot de categorie cat
     *         en met de beschrijving description
     */
    public Item offerItem(User seller, Category cat, String description) {
        // TODO 
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        Item newItem = new Item(seller, cat, description);
        try
        {
            itemDAO.create(newItem);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return newItem;
    }
    
     /**
     * @param item
     * @return true als er nog niet geboden is op het item. Het item word verwijderd.
     *         false als er al geboden was op het item.
     */
    public boolean revokeItem(Item item) {
        // TODO 
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        if (item.getHighestBid() == null)
        {
            try
            {
                itemDAO.remove(item);
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }
}

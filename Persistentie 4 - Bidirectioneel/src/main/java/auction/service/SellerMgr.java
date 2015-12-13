package auction.service;

import auction.dao.ItemDAO;
import auction.dao.ItemDAOJPAImpl;
import auction.domain.Category;
import auction.domain.Furniture;
import auction.domain.Item;
import auction.domain.Painting;
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
    
    public Item offerFurniture(User seller, Category cat, String description, String material)
    {
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        Item newFurniture = new Furniture(material, seller, cat, description);
        try 
        {
            itemDAO.create(newFurniture);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return newFurniture;
    }
    
    public Item offerPainting(User seller, Category cat, String description, String title, String painter)
    {
        ItemDAO itemDAO = new ItemDAOJPAImpl(em);
        Item newPainting = new Painting(title, painter, seller, cat, description);
        try
        {
            itemDAO.create(newPainting);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return newPainting;
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

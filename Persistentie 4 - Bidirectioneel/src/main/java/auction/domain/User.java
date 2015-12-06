package auction.domain;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "User.getAll", query = "select u from User as u"),
    @NamedQuery(name = "User.count", query = "select count(u) from User as u"),
    @NamedQuery(name = "User.findByEmail", query = "select u from User as u where u.email = :userEmail")
})
public class User implements Serializable {

    @Id
    private String email;
    
    @OneToMany(mappedBy = "seller")
    private Set<Item> offeredItems;

    public User() {
        
    }
    
    public User(String email) {
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public Iterator<Item> getOfferedItems() {
        return offeredItems.iterator();
    } 
    
    void addItem(Item item) {
        offeredItems.add(item);
    }
    
    public int numberOfOfferedItems() {
        return offeredItems.size();
    }
    
}

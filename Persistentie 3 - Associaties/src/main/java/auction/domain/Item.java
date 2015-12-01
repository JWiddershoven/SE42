package auction.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import nl.fontys.util.Money;

@Entity
@NamedQueries({
    @NamedQuery(name = "Item.getAll", query = "select i from Item as i"),
    @NamedQuery(name = "Item.count", query = "select count(i) from Item as i"),
    @NamedQuery(name = "Item.findByDescription", query = "select i from Item as i where i.description = :description"),
    @NamedQuery(name = "Item.findByID", query = "select i from Item as i where i.id = :ID")
})
public class Item implements Comparable, Serializable {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User seller;
    @Embedded
    private Category category;
    private String description;
    @OneToOne
    private Bid highest;

    public Item() {
    }

    public Item(User seller, Category category, String description) {
        this.seller = seller;
        this.category = category;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public User getSeller() {
        return seller;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Bid getHighestBid() {
        return highest;
    }

    public Bid newBid(User buyer, Money amount) {
        if (highest != null && highest.getAmount().compareTo(amount) >= 0) {
            return null;
        }
        highest = new Bid(buyer, amount);
        return highest;
    }

    @Override
    public int compareTo(Object arg0) {
        //TODO
        Item item2 = (Item) arg0;
        return (this.getId().compareTo(item2.getId()));
    }

    @Override
    public boolean equals(Object o) {
        //TODO
        if (o == null) return false;
        if (!(o instanceof Item)) return false;
        Item item2 = (Item) o;
        return Objects.equals(this.id, item2.getId()) && Objects.equals(this.getDescription(), item2.getDescription());
    }

    @Override
    public int hashCode() {
        //TODO
        return Objects.hash(this.id, this.category, this.description, this.highest, this.seller);
    }
}

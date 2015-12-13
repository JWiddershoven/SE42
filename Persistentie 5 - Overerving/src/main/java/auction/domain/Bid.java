package auction.domain;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@Entity
public class Bid implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    
    @Embedded
    private FontysTime time;
    
    @OneToOne
    private User buyer;
    
    @Embedded
    private Money amount;
    
    @OneToOne(mappedBy = "highest")
    @NotNull
    private Item item;

    public Bid() {
    }

    public Bid(User buyer, Money amount) {
        //TODO
        this.amount = amount;
        this.buyer = buyer;
        this.time = FontysTime.now();
    }

    public FontysTime getTime() {
        return this.time;
    }

    public User getBuyer() {
        return this.buyer;
    }

    public Money getAmount() {
        return this.amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}

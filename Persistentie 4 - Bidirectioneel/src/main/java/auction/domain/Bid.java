package auction.domain;

import java.io.Serializable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@Entity
public class Bid implements Serializable {

    @Embedded
    private FontysTime time;
    
    @Id @OneToOne
    private User buyer;
    
    @Embedded
    private Money amount;

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
}

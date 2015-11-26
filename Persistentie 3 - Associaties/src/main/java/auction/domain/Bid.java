package auction.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import nl.fontys.util.FontysTime;
import nl.fontys.util.Money;

@Entity
public class Bid implements Serializable {

    @Id
    private FontysTime time;
    private User buyer;
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
        return time;
    }

    public User getBuyer() {
        return buyer;
    }

    public Money getAmount() {
        return amount;
    }
}

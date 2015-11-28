package auction.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Category implements Serializable {

    private String catDescription;

    public Category() {
        this.catDescription = "undefined";
    }

    public Category(String description) {
        this.catDescription = description;
    }

    public String getDiscription() {
        return catDescription;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.domain;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Jelle
 */
@Entity
public class Furniture extends Item implements Serializable {
    
    private String meterial;

    public Furniture() {
    }

    public Furniture(String meterial, User seller, Category category, String description) {
        super(seller, category, description);
        this.meterial = meterial;
        seller.addItemToUser(this);
    }

    public String getMeterial() {
        return meterial;
    } 
}

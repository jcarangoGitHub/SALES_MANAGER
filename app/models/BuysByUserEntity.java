package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "buys_by_user")
public class BuysByUserEntity extends Model {
    private String userName;
    private String itemName;
    private Integer quantity;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

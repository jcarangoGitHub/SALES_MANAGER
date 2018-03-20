package persistence;

import models.BuysByUserEntity;

public class BuysByUserPersistence {
    public void saveBuy(String userName, String itemName, Integer quantity) {
        if (quantity <= 0) {
            return;
        }
        BuysByUserEntity buy = new BuysByUserEntity();
        buy.setUserName(userName);
        buy.setItemName(itemName);
        buy.setQuantity(quantity);

        buy.save();
    }
}

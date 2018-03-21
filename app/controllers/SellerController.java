package controllers;

import persistence.BuysByUserPersistence;
import persistence.ItemPersistence;
import play.mvc.Controller;
import play.mvc.Result;
import utils.SellerUtil;
import views.html.seller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SellerController extends Controller {

    public static Result sell() {
        Set<String> keySet = request().body().asFormUrlEncoded().keySet();
        Map<String, String[]> values = request().body().asFormUrlEncoded();
        String userName = values.get("userName")[0];
        String message = validateBuy(keySet);
        ItemPersistence itemPersistence = new ItemPersistence();
        if (message.isEmpty()) {
            updateQuantities(keySet.iterator(), userName);
            message = "Compra exitosa";
        }

        return ok(seller.render(message, itemPersistence.getAllItems(), userName));
    }

    private static String validateBuy(Set<String> keySet) {
        Iterator<String> ite = keySet.iterator();
        String message = "";
        boolean valid = false;
        while (ite.hasNext()){
            String s = ite.next();
            if(s.equals("userName")) {
                continue;
            }
            Map hashMap = getMapItems(s);
            message = SellerUtil.getMessage(hashMap);
            valid = message.isEmpty();
            if (!valid) {
                break;
            }
        }
        return message;
    }

    private static void updateQuantities(Iterator<String> ite, String userName) {
        ItemPersistence itemPersistence = new ItemPersistence();
        BuysByUserPersistence buysByUserPersistence = new BuysByUserPersistence();
        String s = "";
        while (ite.hasNext()) {
            s = ite.next();
            if (s.equals("userName")) {
                continue;
            }
            Map hashMap = getMapItems(s);
            itemPersistence.updateQauntity(itemPersistence.getItemById(Integer.parseInt((String) hashMap.get("id"))),
                    SellerUtil.getNewValue(hashMap));
            buysByUserPersistence.saveBuy(userName, (String) hashMap.get("name"),
                    Integer.parseInt((String) hashMap.get("toBuy")));
        }
    }

    private static Map getMapItems(String s) {
        Map mapItems = new HashMap();
        String value = request().body().asFormUrlEncoded().get(s)[0];
        String[] arr = (s + "-" + value).split("-");
        mapItems.put("id", arr[0]);
        mapItems.put("name", arr[1]);
        mapItems.put("quantity", arr[2]);
        if(arr.length > 3) {
            mapItems.put("toBuy", arr[3]);
        }
        return mapItems;
    }
}

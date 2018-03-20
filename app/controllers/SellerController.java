package controllers;

import persistence.BuysByUserPersistence;
import persistence.ItemPersistence;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.seller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SellerController extends Controller {

    public static Result sell() {
        Set<String> keySet = request().body().asFormUrlEncoded().keySet();
        Iterator<String> ite = keySet.iterator();
        Map<String, String[]> values = request().body().asFormUrlEncoded();
        String userName = values.get("userName")[0];

        String message = "";
        boolean valid = false;
        while (ite.hasNext()){
            String s = ite.next();
            if(s.equals("userName")) {
                continue;
            }
            Map hashMap = getMapItems(s);
            message = getMessage(hashMap);
            valid = message.isEmpty();
            if (!valid) {
                break;
            }
        }

        ItemPersistence itemPersistence = new ItemPersistence();
        if (valid) {
            updateQuantities(keySet.iterator(), userName);
            message = "Compra exitosa";
        }


        return ok(seller.render(message, itemPersistence.getAllItems(), userName));
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
                    getNewValue(hashMap));
            buysByUserPersistence.saveBuy(userName, (String) hashMap.get("name"),
                    Integer.parseInt((String) hashMap.get("toBuy")));
        }


    }

    private static Integer getNewValue(Map hashMap) {
        Integer currentQuantity = Integer.parseInt((String) hashMap.get("quantity"));
        Integer toBuyQuantity = Integer.parseInt((String) hashMap.get("toBuy"));

        return currentQuantity - toBuyQuantity;
    }

    private static String getMessage(Map hashMap) {
        String message = "";
        if (null == hashMap.get("toBuy")) {
            return message;
        }
        int toBuy = getToBuyNumeric(hashMap);
        if (toBuy < 0) {
           return "En el campo Cantidad a comprar, por favor ingrese un valor numérico positivo";
        }
        int currentQuantity = Integer.parseInt((String) hashMap.get("quantity"));

        if (!validateQuantity(toBuy, currentQuantity)) {
            message = "La cantidad del producto: " + hashMap.get("name") + " que desea comprar: "+ hashMap.get("toBuy")
                    + ", supera la cantidad disponible ";
        }

        return message;
    }

    private static int getToBuyNumeric(Map hashMap) {
        try {
            return Integer.parseInt((String) hashMap.get("toBuy"));
        }catch (Exception exc) {
            return -1;
        }
    }

    private static boolean validateQuantity(int toBuy, int currentQuantity) {
        if (toBuy > currentQuantity) {
            return false;
        }
        return true;        
        
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

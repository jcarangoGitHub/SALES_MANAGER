package utils;

import java.util.HashMap;
import java.util.Map;

public class SellerUtil {

    public static Integer getNewValue(Map hashMap) {
        Integer currentQuantity = Integer.parseInt((String) hashMap.get("quantity"));
        Integer toBuyQuantity = Integer.parseInt((String) hashMap.get("toBuy"));

        return currentQuantity - toBuyQuantity;
    }

    public static String getMessage(Map hashMap) {
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

    protected static int getToBuyNumeric(Map hashMap) {
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

}

package utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.fail;
import static org.fest.assertions.Assertions.assertThat;

public class SellerUtilTest {

    @Test
    public void getNewValueMustReturnCorrectIntegerWhenMapIsOk() {
        // Arrange
        Map mapItems = new HashMap();
        mapItems.put("id", "1");
        mapItems.put("name", "item one");
        mapItems.put("quantity", "25");
        mapItems.put("toBuy", "5");

        // Act
        Integer ans = SellerUtil.getNewValue(mapItems);

        //Assert
        assertThat(ans).isEqualTo(20);
    }

    @Test
    public void getNewValueMustThrowClassCastExceptionWhenMapHasFieldToBuyInteger() {
        // Arrange
        Map mapItems = new HashMap();
        mapItems.put("id", "1");
        mapItems.put("name", "item one");
        mapItems.put("quantity", "25");
        mapItems.put("toBuy", 5);

        try {
            // Act
            Integer ans = SellerUtil.getNewValue(mapItems);
            fail();
        }catch (ClassCastException exc){
            //Assert
            assertThat(exc.getMessage()).contains("java.lang.Integer cannot be cast to java.lang.String");
        }
    }

    @Test
    public void getNewValueMustThrowClassCastExceptionWhenMapHasNotToBuyField() {
        // Arrange
        Map mapItems = new HashMap();
        mapItems.put("id", "1");
        mapItems.put("name", "item one");
        mapItems.put("quantity", "25");

        try {
            // Act
            Integer ans = SellerUtil.getNewValue(mapItems);
            fail();
        }catch (NumberFormatException exc){
            //Assert
            assertThat(exc.toString()).contains("java.lang.NumberFormatException: null");
        }
    }
}

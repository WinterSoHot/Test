package cn.dx.inventory.microservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
class InventoryControllerTest {

    @Test
    void getProductInventories() {
        InventoryController controller = new InventoryController();
        int numberOfInventories = controller.getProductInventories();
        assertEquals(5, numberOfInventories);
    }
}
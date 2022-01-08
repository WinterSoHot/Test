package cn.dx.inventory.microservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gudongxian
 * @date 2022/1/7
 */
@RestController
public class InventoryController {

    @GetMapping("/inventories")
    public int getProductInventories() {
        return 5;
    }
}

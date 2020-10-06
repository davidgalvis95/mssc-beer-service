package guru.david.msscbeerservice.services.inventory;


import guru.david.msscbeerservice.services.inventory.model.BeerInventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

/**
 * Here what we mean is that when the feignclient fails to reach to the inventory service, then look for the fallback class
 * to make the request
 */

@FeignClient(name = "inventory-service", fallback = InventoryServiceFeignClientFailover.class)
public interface InventoryServiceFeignClient {

    /**By using this we are requesting the inventory service for an onhand inventory to some specified beer**/
    @RequestMapping(method = RequestMethod.GET, value = BeerInventoryServiceRestTemplateImpl.INVENTORY_PATH)
    ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(@PathVariable UUID beerId);
}

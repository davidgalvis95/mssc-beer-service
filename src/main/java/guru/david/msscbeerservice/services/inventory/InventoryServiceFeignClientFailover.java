package guru.david.msscbeerservice.services.inventory;

import guru.david.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt on 3/14/20.
 */
@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientFailover implements InventoryServiceFeignClient {

    private final InventoryFailOverFeignClient failoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(UUID beerId) {
        return failoverFeignClient.getOnhandInventory();
    }
}

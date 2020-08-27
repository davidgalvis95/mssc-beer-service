package guru.david.msscbeerservice.services;

import guru.david.msscbeerservice.web.model.BeerDto;
import guru.david.msscbeerservice.web.model.BeerPagedList;
import guru.david.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    Object getById(UUID beerId);

    Object getByUpc(String upc);

    Object saveNewBeer(BeerDto beerDto);

    Object updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand);
}

package guru.david.msscbeerservice.services;

import guru.sfg.brewery.model.BeerDto;
import guru.sfg.brewery.model.BeerPagedList;
import guru.sfg.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    Object getBeerById(UUID beerId, Boolean showInventoryOnHand);

    //Object getByUpc(String upc);

    Object saveBeer(BeerDto beerDto);

    Object updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand);

    //this is the method to retrieve the beer by upc
    Object getByUpc( String upc );
}

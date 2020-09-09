package guru.david.msscbeerservice.events;

import guru.david.msscbeerservice.web.model.BeerDto;


public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent( BeerDto beerDto) {
        super(beerDto);
    }
}

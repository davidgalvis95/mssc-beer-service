package guru.david.msscbeerservice.events;

import guru.david.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent( BeerDto beerDto) {
        super(beerDto);
    }
}

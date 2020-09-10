package guru.david.msscbeerservice.events;

import guru.david.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {
    public NewInventoryEvent( BeerDto beerDto) {
        super(beerDto);
    }
}

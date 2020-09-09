package guru.david.msscbeerservice.events;

import guru.david.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;


@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable
{

    static final long serialVersionUID = -2142317658102172056L;

    private final BeerDto beerDto;
}

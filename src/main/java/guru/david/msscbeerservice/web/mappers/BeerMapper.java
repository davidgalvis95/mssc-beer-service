package guru.david.msscbeerservice.web.mappers;

import guru.david.msscbeerservice.domain.Beer;
import guru.david.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto BeerDtoToBeer(Beer beer);
    Beer BeerToBeerDto(BeerDto beerDto);
}

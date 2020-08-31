package guru.david.msscbeerservice.services;

import guru.david.msscbeerservice.domain.Beer;
import guru.david.msscbeerservice.repositories.BeerRepository;
import guru.david.msscbeerservice.web.controller.NotFoundException;
import guru.david.msscbeerservice.web.mappers.BeerMapper;
import guru.david.msscbeerservice.web.model.BeerDto;
import guru.david.msscbeerservice.web.model.BeerPagedList;
import guru.david.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    @Override
    public BeerDto getBeerById(UUID beerId) {
        return beerMapper.BeerDtoToBeer(
                beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
        );
    }

//    @Override
//    public Object getByUpc(String upc) {
//        return null;
//    }

    @Override
    public Object saveBeer(BeerDto beerDto) {
        return beerMapper.BeerDtoToBeer(beerRepository.save(beerMapper.BeerToBeerDto(beerDto)));
    }

    @Override
    public Object updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        //todo check this property
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return beerMapper.BeerDtoToBeer(beerRepository.save(beer));
    }

//    @Override
//    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of, Boolean showInventoryOnHand) {
//        return null;
//    }
}

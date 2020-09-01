package guru.david.msscbeerservice.services;

import guru.david.msscbeerservice.domain.Beer;
import guru.david.msscbeerservice.repositories.BeerRepository;
import guru.david.msscbeerservice.web.controller.NotFoundException;
import guru.david.msscbeerservice.web.mappers.BeerMapper;
import guru.david.msscbeerservice.web.model.BeerDto;
import guru.david.msscbeerservice.web.model.BeerPagedList;
import guru.david.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow(NotFoundException::new)
        );
    }

//    @Override
//    public Object getByUpc(String upc) {
//        return null;
//    }

    @Override
    public Object saveBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public Object updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        //todo check this property
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if ( !StringUtils.isEmpty( beerName) && !StringUtils.isEmpty( beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        if (showInventoryOnHand){
            beerPagedList = new BeerPagedList(beerPage
                                                    .getContent()
                                                    .stream()
                                                    .map(beerMapper::beerToBeerDtoWithInventory)
                                                    .collect( Collectors.toList()),
                                              PageRequest
                                                    .of(beerPage.getPageable().getPageNumber(),
                                                        beerPage.getPageable().getPageSize()),
                                              beerPage.getTotalElements());
        } else {
            beerPagedList = new BeerPagedList(beerPage
                                                    .getContent()
                                                    .stream()
                                                    .map(beerMapper::beerToBeerDto)
                                                    .collect(Collectors.toList()),
                                              PageRequest
                                                    .of(beerPage.getPageable().getPageNumber(),
                                                        beerPage.getPageable().getPageSize()),
                                              beerPage.getTotalElements());
        }

        return beerPagedList;
    }
}

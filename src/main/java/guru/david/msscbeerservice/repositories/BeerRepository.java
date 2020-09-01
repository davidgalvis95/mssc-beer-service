package guru.david.msscbeerservice.repositories;

import guru.david.msscbeerservice.domain.Beer;
import guru.david.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//actually te repository tag is not really needed but we can do it if we want to
@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

    //the PagingAndSortingRepository is a kind of JPA feature that offers us a variety of methods, by extending the
    //CrudRepository

    Page<Beer> findAllByBeerName( String beerName, Pageable pageable);

    Page<Beer> findAllByBeerStyle( BeerStyleEnum beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);

    Beer findByUpc(String upc);
}

package guru.david.msscbeerservice.repositories;

import guru.david.msscbeerservice.domain.Beer;
import guru.sfg.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//actually te repository tag is not really needed but we can do it if we want to
@Repository
public interface BeerRepository extends JpaRepository<Beer, UUID>
{
    //we had to change it to a jpa repo, due that it returns a find all in a List format

    //the PagingAndSortingRepository is a kind of JPA feature that offers us a variety of methods, by extending the
    //CrudRepository

    Page<Beer> findAllByBeerName( String beerName, Pageable pageable);

    Page<Beer> findAllByBeerStyle( BeerStyleEnum beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);

    Beer        findByUpc(String upc);
}

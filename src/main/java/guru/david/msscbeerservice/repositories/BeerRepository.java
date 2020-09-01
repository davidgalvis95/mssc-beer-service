package guru.david.msscbeerservice.repositories;

import guru.david.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

//actually te repository tag is not really needed but we can do it if we want to
@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {

    //the PagingAndSortingRepository is a kind of JPA feature that offers us a variety of methods, by extending the
    //CrudRepository
}

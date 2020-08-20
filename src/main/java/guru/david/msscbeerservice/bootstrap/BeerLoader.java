package guru.david.msscbeerservice.bootstrap;

import guru.david.msscbeerservice.domain.Beer;
import guru.david.msscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//the CommandLineRunner tells spring to run the run method
//right away spring starts it will look for this component and will start the actions that were injected from the repo
@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    //this is the method that comes from the interface and runs what is in this component
    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        //here what im saying is that if have nothing initialized there in my repo
        //come on and initialize it
        if(beerRepository.count() == 0){

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(33010000023L)
                    .price(new BigDecimal(11.95))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(201)
                    .minOnHand(13)
                    .upc(43010000023L)
                    .price(new BigDecimal(11.95))
                    .build());
            //with this we know that the bootstrap class is now functional
            //TODO remove this sysout
            System.out.println("Loaded beers" + beerRepository.count());
        }
    }
}

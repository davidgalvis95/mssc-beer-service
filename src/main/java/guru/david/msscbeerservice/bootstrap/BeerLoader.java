package guru.david.msscbeerservice.bootstrap;

import guru.david.msscbeerservice.domain.Beer;
import guru.david.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;


//the CommandLineRunner tells spring to run the run method
//right away spring starts it will look for this component and will start the actions that were injected from the repo
@Slf4j
@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString( "0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final BeerRepository beerRepository;

    //public BeerLoader(BeerRepository beerRepository) {
        //this.beerRepository = beerRepository;
    //}

    //this is the method that comes from the interface and runs what is in this component
    @Override
    public void run(String... args) throws Exception {

        log.info("the count is: '{}'",beerRepository.count());
        if(beerRepository.count() == 0)
        {
            log.info("there are no beers in the repo");
            loadBeerObjects();
        }

        log.info("the beer is '{}'",beerRepository.findByUpc( "0631234200036" ).getBeerName());
    }


    private void loadBeerObjects()
    {

        //if(beerRepository.count() == 0){

            Beer b1 = beerRepository.save(Beer.builder()
                                    .beerName("Galaxy Cat")
                                    .beerStyle("PALE_ALE")
                                    .quantityToBrew(200)
                                    .minOnHand(12)
                                    .upc(BEER_1_UPC)
                                    .price(new BigDecimal(11.95))
                                    .build());

            Beer b2 = beerRepository.save(Beer.builder()
                                    .beerName("Mango Bobs")
                                    .beerStyle("IPA")
                                    .quantityToBrew(201)
                                    .minOnHand(13)
                                    .upc(BEER_2_UPC)
                                    .price(new BigDecimal(11.95))
                                    .build());

            Beer b3 = beerRepository.save(Beer.builder()
                                    .beerName("Indian Wife Beru")
                                    .beerStyle("PILSNER")
                                    .quantityToBrew(40)
                                    .minOnHand(220)
                                    .upc(BEER_3_UPC)
                                    .price(new BigDecimal("5.95"))
                                    .build());

            //with this we know that the bootstrap class is now functional
            //TODO remove this sysout
            System.out.println("Loaded beers" + beerRepository.count());
        //}
        beerRepository.save( b1 );
        beerRepository.save( b2 );
        beerRepository.save( b3 );
    }
}


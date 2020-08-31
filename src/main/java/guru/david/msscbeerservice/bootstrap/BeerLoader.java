package guru.david.msscbeerservice.bootstrap;

import guru.david.msscbeerservice.domain.Beer;
import guru.david.msscbeerservice.repositories.BeerRepository;
import guru.david.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;


//the CommandLineRunner tells spring to run the run method
//right away spring starts it will look for this component and will start the actions that were injected from the repo
//@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString( "0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    //this is the method that comes from the interface and runs what is in this component
    @Override
    public void run(String... args) throws Exception {
       // loadBeerObjects();
    }

    //now we will use a sql insert statement instead
}

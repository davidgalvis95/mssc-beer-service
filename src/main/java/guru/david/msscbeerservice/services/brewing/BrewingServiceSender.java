package guru.david.msscbeerservice.services.brewing;

import guru.david.msscbeerservice.config.JmsConfig;
import guru.david.msscbeerservice.domain.Beer;
import guru.david.msscbeerservice.events.BrewBeerEvent;
import guru.david.msscbeerservice.repositories.BeerRepository;
import guru.david.msscbeerservice.services.inventory.BeerInventoryService;
import guru.david.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingServiceSender {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled( fixedRate = 5000) //every 5 seconds
    public void checkForLowInventory(){
        //due that the pageablerepo returns an iterator, we need to change it to a jparepo
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQOH = beerInventoryService.getOnhandInventory(beer.getId());

            log.debug("Min Onhand is: " + beer.getMinOnHand());
            log.debug("Inventory is: "  + invQOH);

            if(beer.getMinOnHand() >= invQOH){
                jmsTemplate.convertAndSend( JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent( beerMapper.beerToBeerDto( beer)));
            }
        });

    }
}

package guru.david.msscbeerservice.services.brewing;

import guru.david.msscbeerservice.config.JmsConfig;
import guru.david.msscbeerservice.domain.Beer;
import guru.david.msscbeerservice.events.BrewBeerEvent;
import guru.david.msscbeerservice.events.NewInventoryEvent;
import guru.david.msscbeerservice.repositories.BeerRepository;
import guru.david.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @Transactional
    //here we have to add the transactional annotation, since we are talking to the DB and we need to set up a session in hibernate, so if there is no annotation, we cannot track it
    //The error was: Listener method 'public void guru.david.msscbeerservice.services.brewing.BrewBeerListener.listen(guru.david.msscbeerservice.events.BrewBeerEvent)' threw exception; nested exception is org.hibernate.LazyInitializationException: could not initialize proxy [guru.david.msscbeerservice.domain.Beer#e124de8c-1e8d-4764-b32a-87147c040399] - no Session
    @JmsListener( destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen( BrewBeerEvent event){
        BeerDto beerDto = event.getBeerDto();
        //due that in the BeerDto we don'' have the quantity to brew, we gotta go and make a call to the db to
        //have that info, due that in this case we don't wanna expose that field
        Beer beer = beerRepository.getOne( beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent( beerDto);

        log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH: " + beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}

package guru.david.msscbeerservice.services.order;


import guru.david.msscbeerservice.config.JmsConfig;
import guru.sfg.brewery.model.events.BeerOrderDto;
import guru.sfg.brewery.model.events.ValidateBeerOrderRequest;
import guru.sfg.brewery.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@RequiredArgsConstructor
@Slf4j
public class ValidationRequestListener
{

    private final BeerOrderValidator beerOrderValidator;
    private final JmsTemplate jmsTemplate;

    @JmsListener( destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen( ValidateBeerOrderRequest event){

        BeerOrderDto beerOrderDto = event.getBeerOrderDto();
        Boolean isValid = beerOrderValidator.validateOrder( beerOrderDto );

        jmsTemplate.convertAndSend( JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, ValidateOrderResult.builder()
                                                                                                .isValid( isValid )
                                                                                                .orderId( beerOrderDto.getId() )
                                                                                                .build() );
    }
}

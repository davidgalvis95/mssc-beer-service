package guru.david.msscbeerservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
//import org.springframework.jms.support.converter.


@Configuration
public class JmsConfig
{
    //this is the queue that is going to send a new inventory request
    public static final String BREWING_REQUEST_QUEUE = "brewing-request";

    @Bean // Serialize message content to json using TextMessage
    //public MessageConverter jacksonJmsMessageConverter( ObjectMapper objectMapper) {
    public MessageConverter jacksonJmsMessageConverter( ) {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType( MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        //converter.setObjectMapper(objectMapper);
        return converter;
    }
}

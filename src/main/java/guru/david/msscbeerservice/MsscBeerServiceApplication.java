package guru.david.msscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

//the following annotation enables us to work with feign clients
@EnableFeignClients
//this is a thing coming from JMS in the parent pom so this is generating some issues in the running of the service
@SpringBootApplication//(exclude = ArtemisAutoConfiguration.class )
public class MsscBeerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsscBeerServiceApplication.class, args);
    }

}

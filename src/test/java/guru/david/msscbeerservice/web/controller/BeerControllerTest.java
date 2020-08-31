package guru.david.msscbeerservice.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.david.msscbeerservice.bootstrap.BeerLoader;
import guru.david.msscbeerservice.services.BeerService;
import guru.david.msscbeerservice.web.model.BeerDto;
import guru.david.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.print.attribute.standard.Media;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    //this is added because is needed in order to perform the taks with the service
    //otherwise will throw an exception that is related to the spring context
    @MockBean
    BeerService beerService;

    @Test
    void getBeerById() throws Exception {
        given(beerService.getBeerById(any())).willReturn(createBeerDto());

        mockMvc.perform(get("/api/v1/beer/"+ UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = createBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveBeer(any())).willReturn(createBeerDto());

        mockMvc.perform(post("/api/v1/beer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerById() throws Exception {

        BeerDto beerDto = createBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.updateBeer(any(), any())).willReturn(createBeerDto());

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    BeerDto createBeerDto(){
        return BeerDto.builder()
                .beerName("My Beer")
                .beerStyle(BeerStyleEnum.ALE)
                .price(new BigDecimal("2.99"))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }
}
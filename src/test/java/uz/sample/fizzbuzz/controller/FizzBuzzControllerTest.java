package uz.sample.fizzbuzz.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.sample.fizzbuzz.dto.FizzBuzzRequest;
import uz.sample.fizzbuzz.dto.FizzBuzzResponse;
import uz.sample.fizzbuzz.dto.NumberPairRequestResponse;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class FizzBuzzControllerTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void testPlay() {
        ResponseEntity<FizzBuzzResponse> response = template.postForEntity("/fizz-buzz", FizzBuzzRequest.builder()
            .numbers(List.of(1, 3, 5, 2, 15))
            .build(), FizzBuzzResponse.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertFalse(response.getBody().getNumbers().isEmpty());
    }

    @Test
    public void testPlayThrowValidationError() {
        ResponseEntity<FizzBuzzResponse> response = template.postForEntity("/fizz-buzz", FizzBuzzRequest.builder()
            .numbers(List.of())
            .build(), FizzBuzzResponse.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testSetNumberPair() {
        Assertions.assertDoesNotThrow(() -> template.postForEntity("/fizz-buzz/pairs", NumberPairRequestResponse.builder()
            .buzz(5)
            .fizz(3)
            .build(), Void.class));
    }

    @Test
    public void testSetNumberPairThrowValidationError() {
        ResponseEntity<Void> response = template.postForEntity("/fizz-buzz/pairs", NumberPairRequestResponse.builder()
            .build(), Void.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getNumberPairs() {
        ResponseEntity<NumberPairRequestResponse> response = template.getForEntity("/fizz-buzz/pairs", NumberPairRequestResponse.class);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
    }

}

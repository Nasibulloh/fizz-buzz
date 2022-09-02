package uz.sample.fizzbuzz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import uz.sample.fizzbuzz.dao.pair.NumberPair;
import uz.sample.fizzbuzz.dao.pair.NumberPairRepository;
import uz.sample.fizzbuzz.dto.FizzBuzzRequest;
import uz.sample.fizzbuzz.dto.FizzBuzzResponse;
import uz.sample.fizzbuzz.dto.NumberPairRequestResponse;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class FizzBuzzServiceTest {

    @Autowired
    private FizzBuzzService service;
    @Autowired
    private NumberPairRepository pairRepository;

    @Test
    public void testPlayWithEmptyNumbers() {
        FizzBuzzResponse response = service.play(FizzBuzzRequest.builder()
            .numbers(List.of())
            .build());
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getNumbers());
        Assertions.assertTrue(response.getNumbers().isEmpty());
    }

    @Test
    public void testPlay() {
        FizzBuzzResponse response = service.play(FizzBuzzRequest.builder()
            .numbers(List.of(0, 3, 5, 0, 15, 20))
            .build());
        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getNumbers());
        Assertions.assertFalse(response.getNumbers().isEmpty());
        Assertions.assertTrue(response.getNumbers().contains("Fizz"));
        Assertions.assertTrue(response.getNumbers().contains("FizzBuzz"));
        Assertions.assertTrue(response.getNumbers().contains("Buzz"));
        Assertions.assertEquals(response.getNumbers().size(), 6);
    }

    @Test
    public void testSetNumberPair() {
        Assertions.assertDoesNotThrow(() -> service.setNumberPair(NumberPairRequestResponse.builder()
            .buzz(5)
            .fizz(3)
            .build()));

        service.setNumberPair(NumberPairRequestResponse.builder()
            .buzz(5)
            .fizz(3)
            .build());

        List<NumberPair> pairs = pairRepository.findAll();

        Assertions.assertFalse(pairs.isEmpty());
        Assertions.assertNotNull(pairs.get(0));
    }

    @Test
    public void testGetNumberPair() {
        NumberPair numberPair = new NumberPair();
        numberPair.setFizz(3);
        numberPair.setBuzz(5);

        pairRepository.save(numberPair);

        NumberPairRequestResponse response = service.getNumberPair();
        Assertions.assertNotNull(response);
    }
}

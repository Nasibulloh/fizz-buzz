package uz.sample.fizzbuzz.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.sample.fizzbuzz.dao.pair.NumberPair;
import uz.sample.fizzbuzz.dao.pair.NumberPairRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class FizzBuzzHelper {
    private final NumberPairRepository pairRepository;
    private Integer fizz;
    private Integer buzz;

    @PostConstruct
    public void init() {
        List<NumberPair> all = pairRepository.findAll();
        if (all.isEmpty()) {
            this.fizz = 3;
            this.buzz = 5;
        } else {
            NumberPair numberPair = all.get(0);
            this.fizz = numberPair.getFizz();
            this.buzz = numberPair.getBuzz();
        }
    }

    public Predicate<Integer> fizzPredicate() {
        return number -> number != 0 && number % fizz == 0;
    }

    public Predicate<Integer> buzzPredicate() {
        return number -> number != 0 && number % buzz == 0;
    }

    public Predicate<Integer> fizzBuzzPredicate() {
        return fizzPredicate().and(buzzPredicate());
    }
}

package uz.sample.fizzbuzz.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.sample.fizzbuzz.dao.pair.NumberPair;
import uz.sample.fizzbuzz.dao.pair.NumberPairRepository;
import uz.sample.fizzbuzz.dto.FizzBuzzRequest;
import uz.sample.fizzbuzz.dto.FizzBuzzResponse;
import uz.sample.fizzbuzz.dto.NumberPairRequestResponse;
import uz.sample.fizzbuzz.dto.mapper.NumberPairMapper;
import uz.sample.fizzbuzz.helper.FizzBuzzHelper;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FizzBuzzService {

    private final NumberPairRepository pairRepository;
    private final FizzBuzzHelper fizzBuzzHelper;
    private final NumberPairMapper numberPairMapper;

    @Transactional
    public FizzBuzzResponse play(final FizzBuzzRequest request) {
        List<String> result = request.getNumbers().stream().map(num -> {
            if (fizzBuzzHelper.fizzBuzzPredicate().test(num)) return "FizzBuzz";
            if (fizzBuzzHelper.fizzPredicate().test(num)) return "Fizz";
            if (fizzBuzzHelper.buzzPredicate().test(num)) return "Buzz";
            return num.toString();
        }).collect(Collectors.toList());

        FizzBuzzResponse response = FizzBuzzResponse.builder()
            .numbers(result)
            .build();

        log.debug("FizzBuzz result: {}", response);

        return response;
    }

    @Transactional(readOnly = true)
    public NumberPairRequestResponse getNumberPair() {
        List<NumberPair> all = pairRepository.findAll();
        if (all.isEmpty()) {
            throw new EntityNotFoundException("No pairs in DB.... First you should pairs.");
        }
        return numberPairMapper.toResponse(all.get(0));
    }

    @Transactional
    public void setNumberPair(NumberPairRequestResponse pair) {
        List<NumberPair> all = pairRepository.findAll();
        NumberPair numberPair;
        if (all.isEmpty()) {
            numberPair = numberPairMapper.toEntity(pair);
        } else {
            numberPair = all.get(0);
            numberPairMapper.update(numberPair, pair);
        }
        pairRepository.save(numberPair);
    }
}

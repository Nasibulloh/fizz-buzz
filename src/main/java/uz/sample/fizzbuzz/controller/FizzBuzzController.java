package uz.sample.fizzbuzz.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.sample.fizzbuzz.dto.FizzBuzzRequest;
import uz.sample.fizzbuzz.dto.FizzBuzzResponse;
import uz.sample.fizzbuzz.dto.NumberPairRequestResponse;
import uz.sample.fizzbuzz.service.FizzBuzzService;

import javax.validation.Valid;

@RestController
@RequestMapping("/fizz-buzz")
@RequiredArgsConstructor
@Slf4j
public class FizzBuzzController {
    private final FizzBuzzService service;

    @PostMapping("/pairs")
    public ResponseEntity<Void> setNumberPair(@Valid NumberPairRequestResponse pair) {
        service.setNumberPair(pair);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/pairs")
    public ResponseEntity<NumberPairRequestResponse> getNumberPair() {
        return ResponseEntity.ok(service.getNumberPair());
    }

    @PostMapping
    public ResponseEntity<FizzBuzzResponse> play(@Valid @RequestBody FizzBuzzRequest request) {
        log.debug("FizzBuzz request handled. {}", request);
        return ResponseEntity.ok(service.play(request));
    }
}

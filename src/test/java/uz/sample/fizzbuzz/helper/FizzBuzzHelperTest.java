package uz.sample.fizzbuzz.helper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class FizzBuzzHelperTest {

    @Autowired
    private FizzBuzzHelper fizzBuzzHelper;

    @Test
    public void testPredicates() {
        Assertions.assertTrue(fizzBuzzHelper.buzzPredicate().test(10));
        Assertions.assertFalse(fizzBuzzHelper.buzzPredicate().test(9));

        Assertions.assertTrue(fizzBuzzHelper.fizzBuzzPredicate().test(15));
        Assertions.assertFalse(fizzBuzzHelper.fizzBuzzPredicate().test(12));

        Assertions.assertTrue(fizzBuzzHelper.fizzPredicate().test(6));
        Assertions.assertFalse(fizzBuzzHelper.fizzPredicate().test(7));
    }
}

package uz.sample.fizzbuzz.dao.pair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberPairRepository extends JpaRepository<NumberPair, Integer> {
}

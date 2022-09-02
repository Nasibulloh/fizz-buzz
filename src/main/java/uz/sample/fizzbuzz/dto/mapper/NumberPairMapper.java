package uz.sample.fizzbuzz.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import uz.sample.fizzbuzz.dao.pair.NumberPair;
import uz.sample.fizzbuzz.dto.NumberPairRequestResponse;

@Mapper
public interface NumberPairMapper {
    NumberPairRequestResponse toResponse(NumberPair pair);

    NumberPair toEntity(NumberPairRequestResponse request);

    void update(@MappingTarget NumberPair pair, NumberPairRequestResponse request);
}

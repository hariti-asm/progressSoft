package ma.hariti.asmaa.progresssoft.mapper;


import ma.hariti.asmaa.progresssoft.dto.DealRequestDto;
import ma.hariti.asmaa.progresssoft.dto.DealResponseDto;
import ma.hariti.asmaa.progresssoft.entity.Deal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface DealMapper {
    @Mapping(target = "dealUniqueId", source = "dealUniqueId")
    @Mapping(target = "fromCurrencyIsoCode", source = "fromCurrencyIsoCode")
    @Mapping(target = "toCurrencyIsoCode", source = "toCurrencyIsoCode")
    @Mapping(target = "dealTimestamp", source = "dealTimestamp")
    @Mapping(target = "dealAmount", source = "dealAmount")
    Deal toEntity(DealRequestDto dto);

    @Mapping(target = "id", source = "dealUniqueId")
    @Mapping(target = "timestamp", source = "dealTimestamp")
    @Mapping(target = "amount", source = "dealAmount")
    DealResponseDto toResponseEntity(Deal deal);
}
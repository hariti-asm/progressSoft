package ma.hariti.asmaa.progresssoft.mapper;


import ma.hariti.asmaa.progresssoft.dto.DealRequestDto;
import ma.hariti.asmaa.progresssoft.dto.DealResponseDto;
import ma.hariti.asmaa.progresssoft.entity.Deal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DealMapper {
    Deal toEntity(DealRequestDto dto);

    DealResponseDto toResponseEntity(Deal deal);
}
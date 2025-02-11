package com.progresssoft.mapper;


import com.progresssoft.dto.DealRequestDto;
import com.progresssoft.dto.DealResponseDto;
import com.progresssoft.entity.Deal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DealMapper {
    Deal toEntity(DealRequestDto dto);
    DealResponseDto toResponseEntity(Deal deal);
}
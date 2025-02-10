package com.progresssoft.controller;

import jakarta.validation.Valid;
import com.progresssoft.dto.DealRequestDto;
import com.progresssoft.dto.DealResponseDto;
import com.progresssoft.service.DealService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/deals")
public class DealController {
    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping
    public ResponseEntity<DealResponseDto> createDeal(@Valid @RequestBody DealRequestDto dealRequestDto) {
        DealResponseDto deal = dealService.save(dealRequestDto);
        return new ResponseEntity<>(deal, HttpStatus.CREATED);
    }
}
package ma.hariti.asmaa.progresssoft.controller;

import jakarta.validation.Valid;
import ma.hariti.asmaa.progresssoft.dto.DealRequestDto;
import ma.hariti.asmaa.progresssoft.dto.DealResponseDto;
import ma.hariti.asmaa.progresssoft.service.DealService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/deals")
public class DealController {
    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DealResponseDto createDeal(@Valid @RequestBody DealRequestDto dealRequestDto) {
        return dealService.save(dealRequestDto);
    }
}
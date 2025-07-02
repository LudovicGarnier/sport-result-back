package com.sportresult.app_back.season.controller;

import com.sportresult.app_back.season.dto.NbaSeasonDto;
import com.sportresult.app_back.season.service.NbaSeasonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seasons")
@RequiredArgsConstructor
@Tag(name = "Season")
@Slf4j
public class NbaSeasonController {

    private final NbaSeasonService nbaSeasonService;

    @Operation(summary = "Return a Season from a year")
    @GetMapping("/{year}")
    public ResponseEntity<NbaSeasonDto> getNbaSeasonDto(@PathVariable int year) {
        NbaSeasonDto seasonDto = nbaSeasonService.findNbaSeasonByYear(year);
        return ResponseEntity.ok(seasonDto);
    }
}

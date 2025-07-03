package com.sportresult.app_back.season.controller;

import com.sportresult.app_back.season.dto.NbaSeasonDto;
import com.sportresult.app_back.season.service.NbaSeasonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nbaSeasons")
@RequiredArgsConstructor
@Tag(name = "NBA Season")
@Slf4j
public class NbaSeasonController {

    private final NbaSeasonService nbaSeasonService;

    @Operation(summary = "Return a Season from a year")
    @GetMapping("/{year}")
    public ResponseEntity<NbaSeasonDto> getNbaSeason(@PathVariable int year) {
        NbaSeasonDto seasonDto = nbaSeasonService.findNbaSeasonByYear(year);
        return ResponseEntity.ok(seasonDto);
    }

    @Operation(summary = "Add a Season from a year")
    @PostMapping("/{year}")
    public ResponseEntity<NbaSeasonDto> addNbaSeason(@PathVariable int year) {
        NbaSeasonDto seasonDto = nbaSeasonService.createNbaSeasonByYear(year);
        return ResponseEntity.ok(seasonDto);
    }
}

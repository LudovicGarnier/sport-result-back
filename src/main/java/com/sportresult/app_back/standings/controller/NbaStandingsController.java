package com.sportresult.app_back.standings.controller;

import com.sportresult.app_back.standings.dto.NbaStandingsDto;
import com.sportresult.app_back.standings.service.NbaStandingsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("nbaStandings")
@RequiredArgsConstructor
@Tag(name = "NBA Standings")
public class NbaStandingsController {

    private final NbaStandingsService nbaStandingsService;

    @Operation(summary = "Return Standings by Year")
    @GetMapping("year")
    public ResponseEntity<List<NbaStandingsDto>> getStandingsPerYear(@PathParam("seasonYear") int seasonYear) {
        log.info("START - Standings by year: {}", seasonYear);

        List<NbaStandingsDto> dtoList = nbaStandingsService.getStandingBySeason(seasonYear);

        return ResponseEntity.ok(dtoList);
    }

    @Operation(summary = "Return Standings by Year And By Conference")
    @GetMapping("yearAndConference")
    public ResponseEntity<List<NbaStandingsDto>> getStandingsPerYearAndConferenceName(
            @PathParam("seasonYear") int seasonYear, @PathParam("conference") String conferenceName) {
        log.info("START - Standings by year {} and conference {}", seasonYear, conferenceName);

        List<NbaStandingsDto> dtoList = nbaStandingsService.getStandingBySeasonAndConferenceName(seasonYear, conferenceName.toLowerCase());

        return ResponseEntity.ok(dtoList);
    }

    @Operation(summary = "Return Standings by Year And By Conference")
    @GetMapping("yearAndDivision")
    public ResponseEntity<List<NbaStandingsDto>> getStandingsPerYearAndDivisionName(
            @PathParam("seasonYear") int seasonYear, @PathParam("division") String divisionName) {
        log.info("START - Standings by year {} and division {}", seasonYear, divisionName);

        List<NbaStandingsDto> dtoList = nbaStandingsService.getStandingBySeasonAndDivisionName(seasonYear, divisionName.toLowerCase());

        return ResponseEntity.ok(dtoList);
    }

}

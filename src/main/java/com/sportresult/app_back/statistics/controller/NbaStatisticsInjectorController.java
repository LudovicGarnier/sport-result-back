package com.sportresult.app_back.statistics.controller;

import com.sportresult.app_back.statistics.dto.NbaTeamGameStatisticsDto;
import com.sportresult.app_back.statistics.dto.NbaTeamSeasonStatisticsDto;
import com.sportresult.app_back.statistics.service.NbaStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("statistics")
@RequiredArgsConstructor
@Tag(name = "NBA Statistics")
public class NbaStatisticsInjectorController {

    private final NbaStatisticsService nbaStatisticsService;


    @Operation(summary = "Return NbaTeam Season Statistics per season and per team")
    @GetMapping("/teams/season")
    public ResponseEntity<NbaTeamSeasonStatisticsDto> getStatisticsPerTeamAndPerYear(@RequestParam int season, @RequestParam String teamCode) {
        log.info("getStatisticsPerTeamAndPerYear: {}", season);
        NbaTeamSeasonStatisticsDto nbaTeamSeasonStatisticsDto = nbaStatisticsService.getTeamSeasonStatisticsByTeamAndBySeason(season, teamCode);

        return ResponseEntity.ok(nbaTeamSeasonStatisticsDto);
    }

    @Operation(summary = "Return NbaTeam Season Statistics per season and per team")
    @GetMapping("/teams/game")
    public ResponseEntity<NbaTeamGameStatisticsDto> getTeamGameStatisticsPerTeamAndPerGame(@RequestParam String gameId, @RequestParam String teamCode) {
        log.info("getTeamGameStatisticsPerTeamAndPerGame for game {} and team {}", gameId, teamCode);
        NbaTeamGameStatisticsDto nbaTeamGameStatisticsDto = nbaStatisticsService.getTeamGameStatisticsPerTeamAndPerGame(gameId, teamCode);

        return ResponseEntity.ok(nbaTeamGameStatisticsDto);
    }
}

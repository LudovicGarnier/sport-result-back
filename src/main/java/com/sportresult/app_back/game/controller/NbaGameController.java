package com.sportresult.app_back.game.controller;

import com.sportresult.app_back.game.dto.NbaGameDto;
import com.sportresult.app_back.game.service.NbaGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("nbaGames")
@RequiredArgsConstructor
@Tag(name = "NBA Games")
public class NbaGameController {

    private final NbaGameService nbaGameService;

    @Operation(summary = "Return all Games by Year")
    @GetMapping("/year")
    public ResponseEntity<List<NbaGameDto>> getGamesPerSeasonYear(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size,
                                                                  @RequestParam int seasonYear) {
        log.info("Games by year: {}", seasonYear);
        List<NbaGameDto> nbaGameDtos = nbaGameService.getGamesBySeasonOrderByGameStart(page, size, seasonYear);

        return ResponseEntity.ok(nbaGameDtos);
    }


    @Operation(summary = "Return all Games by Year and by Stage")
    @GetMapping("/yearAndStage")
    public ResponseEntity<List<NbaGameDto>> getGamesPerSeasonYearAndPerStage(@RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size,
                                                                             @RequestParam int seasonYear,
                                                                             @RequestParam int stage) {
        log.info("Games by year {} and by stage: {}", seasonYear, stage);
        List<NbaGameDto> nbaGameDtos = nbaGameService.getGamesBySeasonAndPerStageOrderByGameStart(page, size, seasonYear, stage);

        return ResponseEntity.ok(nbaGameDtos);
    }

    @Operation(summary = "Return all Games by Year and By Team")
    @GetMapping("/yearAndTeam")
    public ResponseEntity<List<NbaGameDto>> getGamesPerSeasonYearAndPerTeam(@RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "10") int size,
                                                                            @RequestParam int seasonYear,
                                                                            @RequestParam String teamCode) {
        log.info("Games by year {} and by team: {}", seasonYear, teamCode);
        List<NbaGameDto> nbaGameDtos = nbaGameService.getGamesBySeasonAndByTeam(page, size, seasonYear, teamCode);

        return ResponseEntity.ok(nbaGameDtos);
    }


}

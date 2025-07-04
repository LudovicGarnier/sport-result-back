package com.sportresult.app_back.player.controller;

import com.sportresult.app_back.player.dto.NbaPlayerDto;
import com.sportresult.app_back.player.service.NbaPlayerService;
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

@RestController
@RequestMapping("nbaPlayer")
@RequiredArgsConstructor
@Tag(name = "NBA Players")
@Slf4j
public class NbaPlayerController {

    private final NbaPlayerService nbaPlayerService;


    @Operation(summary = "Return all the paginated Players")
    @GetMapping
    public ResponseEntity<List<NbaPlayerDto>> getAllPlayers(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        log.info("getAllPlayers page: {}, size: {}", page, size);
        List<NbaPlayerDto> players = nbaPlayerService.getPlayersOrderByLastName(page, size);
        return ResponseEntity.ok(players);
    }

    @Operation(summary = "Return all the paginated Players by Lastname")
    @GetMapping(value = "/lastname")
    public ResponseEntity<List<NbaPlayerDto>> getAllPlayersByLastName(@RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "10") int size,
                                                                      @RequestParam String lastname) {
        log.info("getAllPlayersByLastName page: {}, size: {}", page, size);
        List<NbaPlayerDto> players = nbaPlayerService.getPlayersByLastNameOrderByLastName(page, size, lastname);
        return ResponseEntity.ok(players);
    }

    @Operation(summary = "Return all the paginated Players by Firstname")
    @GetMapping(value = "/firstname")
    public ResponseEntity<List<NbaPlayerDto>> getAllPlayersByFirstName(@RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size,
                                                                       @RequestParam String firstname) {
        log.info("getAllPlayersByFirstName page: {}, size: {}", page, size);
        List<NbaPlayerDto> players = nbaPlayerService.getPlayersByFirstNameOrderByLastName(page, size, firstname);
        return ResponseEntity.ok(players);
    }

    @Operation(summary = "Return all Active or Inactive paginated Players")
    @GetMapping(value = "/active")
    public ResponseEntity<List<NbaPlayerDto>> getAllActivePlayers(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size,
                                                                  @RequestParam Boolean isActive) {
        log.info("getAllActivePlayers page: {}, size: {}", page, size);
        List<NbaPlayerDto> players = nbaPlayerService.getPlayersByIsActiveOrderByLastName(page, size, isActive);
        return ResponseEntity.ok(players);
    }
}

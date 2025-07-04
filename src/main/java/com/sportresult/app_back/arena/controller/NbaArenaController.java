package com.sportresult.app_back.arena.controller;

import com.sportresult.app_back.arena.dto.NbaArenaDto;
import com.sportresult.app_back.arena.service.NbaArenaService;
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
@RequestMapping("/nbaArena")
@RequiredArgsConstructor
@Tag(name = "NBA Arenas")
@Slf4j
public class NbaArenaController {

    private final NbaArenaService nbaArenaService;

    @Operation(summary = "Return an Arena from a name")
    @GetMapping("/{name}")
    public ResponseEntity<NbaArenaDto> getNbaArenaByName(@PathVariable String name) {
        log.info("Getting NBA Arena by name: {}", name);
        NbaArenaDto arenaDto = nbaArenaService.getNbaArenaEntitiesByName(name);
        return ResponseEntity.ok(arenaDto);
    }
}

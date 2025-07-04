package com.sportresult.app_back.team.controller;

import com.sportresult.app_back.team.dto.NbaTeamDto;
import com.sportresult.app_back.team.service.NbaTeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nbaTeam")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "NBA Teams")
public class NbaTeamController {

    private final NbaTeamService nbaTeamService;

    @Operation(summary = "Return a Team from a code")
    @GetMapping("code/{code}")
    public ResponseEntity<NbaTeamDto> getNbaTeam(@PathVariable String code) {
        log.info("getNbaTeam code: {}", code);
        NbaTeamDto nbaTeamDtos = nbaTeamService.getTeamByCode(code);
        return ResponseEntity.ok(nbaTeamDtos);
    }

    @Operation(summary = "Return Teams from a conference")
    @GetMapping("conference/{conference}")
    public ResponseEntity<List<NbaTeamDto>> getNbaTeamByConference(@PathVariable String conference) {
        log.info("getNbaTeamByConference conference: {}", conference);
        List<NbaTeamDto> nbaTeamDtos = nbaTeamService.getTeamsByConference(conference);
        return ResponseEntity.ok(nbaTeamDtos);
    }

    @Operation(summary = "Return Teams from a division")
    @GetMapping("division/{division}")
    public ResponseEntity<List<NbaTeamDto>> getNbaTeamByDivision(@PathVariable String division) {
        log.info("getNbaTeamByDivision division: {}", division);
        List<NbaTeamDto> nbaTeamDtos = nbaTeamService.getTeamsByDivision(division);
        return ResponseEntity.ok(nbaTeamDtos);
    }

    @Operation(summary = "Return Teams from a division")
    @GetMapping("/nbaFanchise")
    public ResponseEntity<List<NbaTeamDto>> getAllNbaTeams() {
        log.info("getAllNbaTeams");
        List<NbaTeamDto> nbaTeamDtos = nbaTeamService.getAllNbaTeams();
        return ResponseEntity.ok(nbaTeamDtos);
    }
}

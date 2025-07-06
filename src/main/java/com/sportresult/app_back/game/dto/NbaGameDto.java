package com.sportresult.app_back.game.dto;

import com.sportresult.app_back.arena.dto.NbaArenaDto;
import com.sportresult.app_back.team.dto.NbaTeamDto;

import java.time.LocalDateTime;
import java.util.List;

public record NbaGameDto(
        Integer season,
        LocalDateTime gameStart,
        LocalDateTime gameEnd,
        String duration,
        Integer stage,
        String status,
        NbaArenaDto arenaDto,
        NbaTeamDto homeTeam,
        NbaTeamDto visitorTeam,
        Integer timesTied,
        Integer leadChanges,
        List<String> officials,
        List<String> visitorLineScore,
        List<String> homeLineScore,
        Integer homeScore,
        Integer visitorScore,
        Integer homeWin,
        Integer homeLoss,
        Integer visitorWin,
        Integer visitorLoss
) {
}

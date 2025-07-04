package com.sportresult.app_back.statistics.service;

import com.sportresult.app_back.statistics.dto.NbaTeamGameStatisticsDto;
import com.sportresult.app_back.game.entity.NbaGameEntity;
import com.sportresult.app_back.statistics.repository.NbaTeamGameStatisticsRepository;
import com.sportresult.app_back.game.service.NbaGameService;
import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.season.service.NbaSeasonService;
import com.sportresult.app_back.statistics.dto.NbaTeamSeasonStatisticsDto;
import com.sportresult.app_back.statistics.repository.NbaTeamSeasonStatisticsRepository;
import com.sportresult.app_back.team.entity.NbaTeamEntity;
import com.sportresult.app_back.team.service.NbaTeamService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbaStatisticsService {

    private final NbaTeamSeasonStatisticsRepository nbaTeamSeasonStatisticsRepository;

    private final NbaTeamGameStatisticsRepository nbaTeamGameStatisticsRepository;

    private final NbaSeasonService nbaSeasonService;

    private final NbaTeamService nbaTeamService;

    private final NbaGameService nbaGameService;


    public NbaTeamSeasonStatisticsDto getTeamSeasonStatisticsByTeamAndBySeason(int seasonYear, String teamCode) {
        log.info("getStatisticsByTeamAndBySeason  seasonYear: {}, teamCode: {}", seasonYear, teamCode);
        NbaSeasonEntity nbaSeason = getNbaSeasonEntity(seasonYear);
        NbaTeamEntity nbaTeam = nbaTeamService.getTeamEntityByCode(teamCode);
        return nbaTeamSeasonStatisticsRepository.findBySeasonAndTeam(nbaSeason, nbaTeam).orElseThrow(
                () -> new EntityNotFoundException("No Season Statistics Found For Team: " + teamCode)).toDto();
    }

    private NbaSeasonEntity getNbaSeasonEntity(int seasonYear) {
        return nbaSeasonService.findNbaSeasonEntityByYear(seasonYear);
    }

    public NbaTeamGameStatisticsDto getTeamGameStatisticsPerTeamAndPerGame(String gameId, String teamCode) {
        log.info("getTeamGameStatisticsPerTeamAndPerGame  gameId: {}, teamId: {}", gameId, teamCode);
        NbaTeamEntity nbaTeam = nbaTeamService.getTeamEntityByCode(teamCode);
        NbaGameEntity nbaGame = nbaGameService.getGameEntityById(UUID.fromString(gameId));
        return nbaTeamGameStatisticsRepository.findByTeamAndGame(nbaTeam, nbaGame).orElseThrow(
                () -> new EntityNotFoundException("Not Statistics Found for Team " + nbaTeam + " and Game " + nbaGame)).toDto();
    }
}

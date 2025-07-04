package com.sportresult.app_back.game.service;

import com.sportresult.app_back.game.dto.NbaGameDto;
import com.sportresult.app_back.game.entity.NbaGameEntity;
import com.sportresult.app_back.game.repository.NbaGameRepository;
import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.season.service.NbaSeasonService;
import com.sportresult.app_back.team.entity.NbaTeamEntity;
import com.sportresult.app_back.team.service.NbaTeamService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbaGameService {

    private final NbaGameRepository nbaGameRepository;

    private final NbaSeasonService nbaSeasonService;

    private final NbaTeamService nbaTeamService;

    public List<NbaGameDto> getGamesBySeasonOrderByGameStart(int page, int size, int seasonYear) {
        log.info("getGamesBySeasonOrderByGameStart page: {}, size: {}, seasonYear: {}", page, size, seasonYear);
        NbaSeasonEntity nbaSeason = getNbaSeasonEntity(seasonYear);
        Pageable pageable = PageRequest.of(page, size, Sort.by("gameStart").ascending());
        Page<NbaGameEntity> nbaGamePageable = nbaGameRepository.findNbaGameEntitiesBySeason(pageable, nbaSeason);
        return nbaGamePageable.stream().map(NbaGameEntity::toDto).collect(Collectors.toList());
    }

    public List<NbaGameDto> getGamesBySeasonAndPerStageOrderByGameStart(int page, int size, int seasonYear, int stage) {
        log.info("getGamesBySeasonAndPerStageOrderByGameStart page: {}, size: {}, seasonYear: {}, stage: {}", page, size, seasonYear, stage);
        NbaSeasonEntity nbaSeason = getNbaSeasonEntity(seasonYear);
        Pageable pageable = PageRequest.of(page, size, Sort.by("gameStart").ascending());
        Page<NbaGameEntity> nbaGamePageable = nbaGameRepository.findNbaGameEntitiesBySeasonAndStage(nbaSeason, stage, pageable);
        return nbaGamePageable.stream().map(NbaGameEntity::toDto).collect(Collectors.toList());
    }

    public List<NbaGameDto> getGamesBySeasonAndByTeam(int page, int size, int seasonYear, String teamCode) {
        log.info("getGamesBySeasonAndByTeam page: {}, size: {}, seasonYear: {}, teamCode: {}", page, size, seasonYear, teamCode);
        NbaSeasonEntity nbaSeason = getNbaSeasonEntity(seasonYear);

        NbaTeamEntity nbaTeam = nbaTeamService.getTeamEntityByCode(teamCode);
        Pageable pageable = PageRequest.of(page, size, Sort.by("gameStart").ascending());
        Page<NbaGameEntity> nbaGamePageable = nbaGameRepository.findNbaGameEntitiesBySeasonAndTeam(nbaSeason, nbaTeam, pageable);
        return nbaGamePageable.stream().map(NbaGameEntity::toDto).collect(Collectors.toList());
    }

    public NbaGameEntity getGameEntityById(UUID id) {
        log.info("getGameEntityById: {}", id);
        return nbaGameRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("NBA Game Not Found for id:" + id)
        );
    }

    private NbaSeasonEntity getNbaSeasonEntity(int seasonYear) {
        return nbaSeasonService.findNbaSeasonEntityByYear(seasonYear);
    }
}

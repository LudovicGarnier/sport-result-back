package com.sportresult.app_back.standings.service;

import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.season.service.NbaSeasonService;
import com.sportresult.app_back.standings.dto.NbaStandingsDto;
import com.sportresult.app_back.standings.entity.NbaStandingsEntity;
import com.sportresult.app_back.standings.repository.NbaStandingsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbaStandingsService {

    private final NbaStandingsRepository nbaStandingsRepository;

    private final NbaSeasonService nbaSeasonService;


    public List<NbaStandingsDto> getStandingBySeason(int seasonYear) {
        NbaSeasonEntity seasonEntity = nbaSeasonService.findNbaSeasonEntityByYear(seasonYear);
        List<NbaStandingsEntity> entities = nbaStandingsRepository.findBySeasonStandings(seasonEntity);
        if (entities.isEmpty()) {
            throw new EntityNotFoundException("NBA Standings not found for season " + seasonYear);
        }
        return entities.stream().map(NbaStandingsEntity::toDto).collect(Collectors.toList());
    }

    public List<NbaStandingsDto> getStandingBySeasonAndConferenceName(int seasonYear, String conferenceName) {
        NbaSeasonEntity seasonEntity = nbaSeasonService.findNbaSeasonEntityByYear(seasonYear);
        List<NbaStandingsEntity> entities = nbaStandingsRepository.findBySeasonStandingsAndConferenceName(seasonEntity, conferenceName);
        if (entities.isEmpty()) {
            throw new EntityNotFoundException("NBA Standings not found for season " + seasonYear + " and conference " + conferenceName);
        }
        return entities.stream().map(NbaStandingsEntity::toDto).collect(Collectors.toList());
    }

    public List<NbaStandingsDto> getStandingBySeasonAndDivisionName(int seasonYear, String divisionName) {
        NbaSeasonEntity seasonEntity = nbaSeasonService.findNbaSeasonEntityByYear(seasonYear);
        List<NbaStandingsEntity> entities = nbaStandingsRepository.findBySeasonStandingsAndDivisionName(seasonEntity, divisionName);
        if (entities.isEmpty()) {
            throw new EntityNotFoundException("NBA Standings not found for season " + seasonYear + " and division " + divisionName);
        }
        return entities.stream().map(NbaStandingsEntity::toDto).collect(Collectors.toList());
    }
}

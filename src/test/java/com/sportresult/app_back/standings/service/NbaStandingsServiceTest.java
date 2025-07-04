package com.sportresult.app_back.standings.service;

import com.sportresult.app_back.ModelFactory;
import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.season.service.NbaSeasonService;
import com.sportresult.app_back.standings.dto.NbaStandingsDto;
import com.sportresult.app_back.standings.entity.NbaStandingsEntity;
import com.sportresult.app_back.standings.repository.NbaStandingsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NbaStandingsServiceTest {

    @Mock
    private NbaStandingsRepository nbaStandingsRepository;

    @Mock
    private NbaSeasonService nbaSeasonService;

    @InjectMocks
    private NbaStandingsService nbaStandingsService;

    @Test
    @DisplayName("should Find NbaStanding By Year")
    public void shouldFindNbaStandingByYear() {
        int year = 2024;
        NbaSeasonEntity nbaSeason = NbaSeasonEntity.builder().year(year).build();

        NbaStandingsEntity nbaStandings = ModelFactory.createNbaStandingsEntity(year);

        // WHEN
        when(nbaSeasonService.findNbaSeasonEntityByYear(year)).thenReturn(nbaSeason);
        when(nbaStandingsRepository.findBySeasonStandings(nbaSeason)).thenReturn(List.of(nbaStandings));
        List<NbaStandingsDto> dto = nbaStandingsService.getStandingBySeason(year);

        // THEN
        assertNotNull(dto);
        verify(nbaStandingsRepository, times(1)).findBySeasonStandings(nbaSeason);
    }

    @Test
    @DisplayName("should Throw Exception When Nba Standings Not Exists")
    public void shouldThrowExceptionWhenNbaStandingsNotExists() {
        int year = 1950;
        NbaSeasonEntity nbaSeason = NbaSeasonEntity.builder().year(year).build();

        when(nbaSeasonService.findNbaSeasonEntityByYear(year)).thenReturn(nbaSeason);
        assertThrows(EntityNotFoundException.class, () -> {
            nbaStandingsService.getStandingBySeason(year);
        });

        // THEN
        verify(nbaStandingsRepository, times(1)).findBySeasonStandings(nbaSeason);
    }

    @Test
    @DisplayName("should Find NbaStanding By Year And By Conference")
    public void shouldFindNbaStandingByYearAndByConference() {
        int year = 2024;
        String conference = "East";
        NbaSeasonEntity nbaSeason = NbaSeasonEntity.builder().year(year).build();

        NbaStandingsEntity nbaStandings = ModelFactory.createNbaStandingsEntity(year);

        // WHEN
        when(nbaSeasonService.findNbaSeasonEntityByYear(year)).thenReturn(nbaSeason);
        when(nbaStandingsRepository.findBySeasonStandingsAndConferenceName(nbaSeason, conference)).thenReturn(List.of(nbaStandings));
        List<NbaStandingsDto> dto = nbaStandingsService.getStandingBySeasonAndConferenceName(year, conference);

        // THEN
        assertNotNull(dto);
        verify(nbaStandingsRepository, times(1)).findBySeasonStandingsAndConferenceName(nbaSeason, conference);
    }

    @Test
    @DisplayName("should Throw Exception When Nba Standings For Conference Not Exists")
    public void shouldThrowExceptionWhenNbaStandingsForConferenceNotExists() {
        int year = 1950;
        String conference = "East";
        NbaSeasonEntity nbaSeason = NbaSeasonEntity.builder().year(year).build();

        when(nbaSeasonService.findNbaSeasonEntityByYear(year)).thenReturn(nbaSeason);
        assertThrows(EntityNotFoundException.class, () -> {
            nbaStandingsService.getStandingBySeasonAndConferenceName(year, conference);
        });

        // THEN
        verify(nbaStandingsRepository, times(1)).findBySeasonStandingsAndConferenceName(nbaSeason, conference);
    }

    @Test
    @DisplayName("should Find NbaStanding By Year And By Division")
    public void shouldFindNbaStandingByYearAndByDivision() {
        int year = 2024;
        String division = "Central";
        NbaSeasonEntity nbaSeason = NbaSeasonEntity.builder().year(year).build();

        NbaStandingsEntity nbaStandings = ModelFactory.createNbaStandingsEntity(year);

        // WHEN
        when(nbaSeasonService.findNbaSeasonEntityByYear(year)).thenReturn(nbaSeason);
        when(nbaStandingsRepository.findBySeasonStandingsAndConferenceName(nbaSeason, division)).thenReturn(List.of(nbaStandings));
        List<NbaStandingsDto> dto = nbaStandingsService.getStandingBySeasonAndConferenceName(year, division);

        // THEN
        assertNotNull(dto);
        verify(nbaStandingsRepository, times(1)).findBySeasonStandingsAndConferenceName(nbaSeason, division);
    }

    @Test
    @DisplayName("should Throw Exception When Nba Standings For Division Not Exists")
    public void shouldThrowExceptionWhenNbaStandingsForDivisionNotExists() {
        int year = 1950;
        String division = "Central";
        NbaSeasonEntity nbaSeason = NbaSeasonEntity.builder().year(year).build();

        when(nbaSeasonService.findNbaSeasonEntityByYear(year)).thenReturn(nbaSeason);
        assertThrows(EntityNotFoundException.class, () -> {
            nbaStandingsService.getStandingBySeasonAndConferenceName(year, division);
        });

        // THEN
        verify(nbaStandingsRepository, times(1)).findBySeasonStandingsAndConferenceName(nbaSeason, division);
    }


}
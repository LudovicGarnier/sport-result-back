package com.sportresult.app_back.team.service;

import com.sportresult.app_back.ModelFactory;
import com.sportresult.app_back.team.dto.NbaTeamDto;
import com.sportresult.app_back.team.entity.NbaTeamEntity;
import com.sportresult.app_back.team.repository.NbaTeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NbaTeamServiceTest {

    @Mock
    private NbaTeamRepository nbaTeamRepository;

    @InjectMocks
    private NbaTeamService nbaTeamService;

    @Test
    @DisplayName("should Find NbaTeam By Code")
    public void shouldFindNbaTeamByCode() {
        String code = "CHI";
        NbaTeamEntity nbaTeam = NbaTeamEntity.builder().code(code).nbaFranchise(true).allStar(false).build();

        // WHEN
        when(nbaTeamRepository.findByCode(code)).thenReturn(List.of(nbaTeam));
        NbaTeamDto newDto = nbaTeamService.getTeamByCode(code);

        // THEN
        assertNotNull(newDto);
        assertEquals(code, newDto.code());
        verify(nbaTeamRepository, times(1)).findByCode(code);
    }

    @Test
    @DisplayName("should Throw Exception When NbaTeam code Not Exists")
    public void shouldThrowExceptionWhenNbaSeasonNotExists() {
        String code = "NOT_EXISTS";
        assertThrows(EntityNotFoundException.class, () -> {
            nbaTeamService.getTeamByCode(code);
        });

        // THEN
        verify(nbaTeamRepository, times(1)).findByCode(code);
    }

    @Test
    @DisplayName("should Find All Nba Franchise")
    public void shouldFindNbaFranchise() {
        NbaTeamEntity nbaTeam1 = NbaTeamEntity.builder().code("CODE1").nbaFranchise(true).allStar(false).build();
        NbaTeamEntity nbaTeam2 = NbaTeamEntity.builder().code("CODE2").nbaFranchise(true).allStar(false).build();
        List<NbaTeamEntity> nbaFranchise = List.of(nbaTeam1, nbaTeam2);

        // WHEN
        when(nbaTeamRepository.findByNbaFranchise(true)).thenReturn(nbaFranchise);
        List<NbaTeamDto> nbaFranchiseDtos = nbaTeamService.getAllNbaTeams();

        List<NbaTeamDto> filteredNbaFranchiseDtos = nbaFranchiseDtos.stream().filter(NbaTeamDto::nbaFranchise).toList();

        // THEN
        assertEquals(filteredNbaFranchiseDtos.size(), nbaFranchiseDtos.size());
        verify(nbaTeamRepository, times(1)).findByNbaFranchise(true);
    }

    @Test
    @DisplayName("should Find NbaTeam By Division")
    public void shouldFindNbaTeamByDivision() {
        String division = "Central";
        NbaTeamEntity nbaTeam1 = ModelFactory.createNbaTeamEntity("CHI", null, division);
        NbaTeamEntity nbaTeam2 = ModelFactory.createNbaTeamEntity("CLE", null, division);

        List<NbaTeamEntity> teams = List.of(nbaTeam1, nbaTeam2);

        // WHEN
        when(nbaTeamRepository.findByDivision(division)).thenReturn(teams);
        List<NbaTeamDto> teamsByDivision = nbaTeamService.getTeamsByDivision(division);

        // THEN
        assertEquals(teamsByDivision.size(), teams.size());
        verify(nbaTeamRepository, times(1)).findByDivision(division);
    }

    @Test
    @DisplayName("should Throw Exception When Nba Division Not Exists")
    public void shouldThrowExceptionWhenNbaDivisionNotExists() {
        String division = "NOT_EXISTS";
        assertThrows(EntityNotFoundException.class, () -> {
            nbaTeamService.getTeamsByDivision(division);
        });

        // THEN
        verify(nbaTeamRepository, times(1)).findByDivision(division);
    }

    @Test
    @DisplayName("should Find NbaTeam By Conference")
    public void shouldFindNbaTeamByConference() {
        String conference = "East";
        NbaTeamEntity nbaTeam1 = ModelFactory.createNbaTeamEntity("CHI", conference, null);
        NbaTeamEntity nbaTeam2 = ModelFactory.createNbaTeamEntity("CLE", conference, null);

        List<NbaTeamEntity> teams = List.of(nbaTeam1, nbaTeam2);

        // WHEN
        when(nbaTeamRepository.findByConference(conference)).thenReturn(teams);
        List<NbaTeamDto> teamsByDivision = nbaTeamService.getTeamsByConference(conference);

        // THEN
        assertEquals(teamsByDivision.size(), teams.size());
        verify(nbaTeamRepository, times(1)).findByConference(conference);
    }


    @Test
    @DisplayName("should Throw Exception When Nba Conference Not Exists")
    public void shouldThrowExceptionWhenNbaConferenceNotExists() {
        String conference = "NOT_EXISTS";
        assertThrows(EntityNotFoundException.class, () -> {
            nbaTeamService.getTeamsByConference(conference);
        });

        // THEN
        verify(nbaTeamRepository, times(1)).findByConference(conference);
    }
}
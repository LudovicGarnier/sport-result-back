package com.sportresult.app_back.season.service;

import com.sportresult.app_back.season.dto.NbaSeasonDto;
import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.season.repository.NbaSeasonRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NbaSeasonServiceTest {

    @Mock
    private NbaSeasonRepository nbaSeasonRepository;

    @InjectMocks
    private NbaSeasonService nbaSeasonService;


    @Test
    @DisplayName("should Create NbaSeason")
    public void shouldCreateNbaSeason() {
        // GIVEN
        int yearToAdd = 2099;
        NbaSeasonEntity nbaSeason = NbaSeasonEntity.builder().year(yearToAdd).build();

        // WHEN
        when(nbaSeasonRepository.save(any(NbaSeasonEntity.class))).thenReturn(nbaSeason);
        NbaSeasonDto newDto = nbaSeasonService.createNbaSeasonByYear(yearToAdd);

        // THEN
        assertNotNull(newDto);
        assertEquals(yearToAdd, newDto.year());
        verify(nbaSeasonRepository, times(1)).save(any(NbaSeasonEntity.class));
    }

    @Test
    @DisplayName("should Throw Exception When NbaSeason Already Exists")
    public void shouldThrowExceptionWhenNbaSeasonAlreadyExists() {
        int year = 2099;
        NbaSeasonEntity nbaSeason = NbaSeasonEntity.builder().year(year).build();

        // WHEN
        when(nbaSeasonRepository.findByYear(year)).thenReturn(Optional.ofNullable(nbaSeason));
        assertThrows(EntityExistsException.class, () -> {
            nbaSeasonService.createNbaSeasonByYear(year);
        });

        // THEN
        verify(nbaSeasonRepository, times(1)).findByYear(year);
    }

    @Test
    @DisplayName("should Find NbaSeason By Year")
    public void shouldFindNbaSeasonByYear() {
        int year = 2099;
        NbaSeasonEntity nbaSeason = NbaSeasonEntity.builder().year(year).build();

        // WHEN
        when(nbaSeasonRepository.findByYear(year)).thenReturn(Optional.ofNullable(nbaSeason));
        NbaSeasonDto newDto = nbaSeasonService.findNbaSeasonByYear(year);

        // THEN
        assertNotNull(newDto);
        assertEquals(year, newDto.year());
        verify(nbaSeasonRepository, times(1)).findByYear(year);
    }

    @Test
    @DisplayName("should Throw Exception When NbaSeason Not Exists")
    public void shouldThrowExceptionWhenNbaSeasonNotExists() {
        assertThrows(EntityNotFoundException.class, () -> {
            nbaSeasonService.findNbaSeasonByYear(2099);
        });

        // THEN
        verify(nbaSeasonRepository, times(1)).findByYear(2099);
    }

}
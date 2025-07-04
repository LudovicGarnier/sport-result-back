package com.sportresult.app_back.arena.service;

import com.sportresult.app_back.arena.dto.NbaArenaDto;
import com.sportresult.app_back.arena.entity.NbaArenaEntity;
import com.sportresult.app_back.arena.repository.NbaArenaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NbaArenaServiceTest {

    @Mock
    private NbaArenaRepository nbaArenaRepository;

    @InjectMocks
    private NbaArenaService nbaArenaService;

    @Test
    @DisplayName("should Find NbaArena By Name")
    public void shouldFindNbaArenaByName() {
        String name = "Arena Name";
        NbaArenaEntity nbaArena = NbaArenaEntity.builder().name(name).build();

        // WHEN
        when(nbaArenaRepository.findNbaArenaEntitiesByName(name)).thenReturn(Optional.ofNullable(nbaArena));
        NbaArenaDto dto = nbaArenaService.getNbaArenaEntitiesByName(name);

        // THEN
        assertNotNull(dto);
        assertEquals(name, dto.name());
        verify(nbaArenaRepository, times(1)).findNbaArenaEntitiesByName(name);
    }

    @Test
    @DisplayName("should Throw Exception When NbaArena Not Exists")
    public void shouldThrowExceptionWhenNbaArenaNotExists() {
        assertThrows(EntityNotFoundException.class, () -> {
            nbaArenaService.getNbaArenaEntitiesByName("NOT EXISTS");
        });

        // THEN
        verify(nbaArenaRepository, times(1)).findNbaArenaEntitiesByName("NOT EXISTS");
    }
}
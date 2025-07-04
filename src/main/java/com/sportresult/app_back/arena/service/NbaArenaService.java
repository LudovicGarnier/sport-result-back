package com.sportresult.app_back.arena.service;


import com.sportresult.app_back.arena.dto.NbaArenaDto;
import com.sportresult.app_back.arena.entity.NbaArenaEntity;
import com.sportresult.app_back.arena.repository.NbaArenaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbaArenaService {

    private final NbaArenaRepository nbaArenaRepository;

    public NbaArenaDto getNbaArenaEntitiesByName(String name) {
        NbaArenaEntity arenaEntity = nbaArenaRepository.findNbaArenaEntitiesByName(name).orElseThrow(
                () -> new EntityNotFoundException("NBA Arena Not Found for Name: " + name));
        return arenaEntity.toDto();
    }

    public NbaArenaEntity save(NbaArenaEntity nbaArenaEntity) {
        return nbaArenaRepository.saveAndFlush(nbaArenaEntity);
    }
}

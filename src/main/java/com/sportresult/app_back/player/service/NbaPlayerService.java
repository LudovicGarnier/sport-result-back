package com.sportresult.app_back.player.service;

import com.sportresult.app_back.player.dto.NbaPlayerDto;
import com.sportresult.app_back.player.entity.NbaPlayerEntity;
import com.sportresult.app_back.player.repository.NbaPlayerRepository;
import com.sportresult.app_back.team.service.NbaTeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbaPlayerService {

    private final NbaPlayerRepository nbaPlayerRepository;

    private final NbaTeamService nbaTeamService;

    public List<NbaPlayerDto> getPlayersOrderByLastName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastname").ascending());
        Page<NbaPlayerEntity> nbaPlayerPageable = nbaPlayerRepository.findAll(pageable);
        return nbaPlayerPageable.stream().map(NbaPlayerEntity::toDto).collect(Collectors.toList());
    }

    public List<NbaPlayerDto> getPlayersByLastNameOrderByLastName(int page, int size, String lastname) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastname").ascending());
        Page<NbaPlayerEntity> nbaPlayerPageable = nbaPlayerRepository.findAllByLastname(pageable, lastname);
        return nbaPlayerPageable.stream().map(NbaPlayerEntity::toDto).collect(Collectors.toList());
    }

    public List<NbaPlayerDto> getPlayersByFirstNameOrderByLastName(int page, int size, String firstname) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastname").ascending());
        Page<NbaPlayerEntity> nbaPlayerPageable = nbaPlayerRepository.findAllByFirstname(pageable, firstname);
        return nbaPlayerPageable.stream().map(NbaPlayerEntity::toDto).collect(Collectors.toList());
    }

    public List<NbaPlayerDto> getPlayersByIsActiveOrderByLastName(int page, int size, Boolean isActive) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("lastname").ascending());
        Page<NbaPlayerEntity> nbaPlayerPageable = nbaPlayerRepository.findAllByIsActive(pageable, isActive);
        return nbaPlayerPageable.stream().map(NbaPlayerEntity::toDto).collect(Collectors.toList());
    }
}

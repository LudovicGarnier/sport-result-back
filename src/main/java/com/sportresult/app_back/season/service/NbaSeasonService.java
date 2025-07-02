package com.sportresult.app_back.season.service;

import com.sportresult.app_back.season.dto.NbaSeasonDto;
import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.season.repository.NbaSeasonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbaSeasonService {

    private final NbaSeasonRepository nbaSeasonRepository;

    public NbaSeasonDto findNbaSeasonByYear(Integer year) {
        NbaSeasonEntity nbaSeason = nbaSeasonRepository.findByYear(year).orElseThrow(() -> new EntityNotFoundException("NBA Season Not Found for Year: " + year));
        return nbaSeason.toDto();
    }
}

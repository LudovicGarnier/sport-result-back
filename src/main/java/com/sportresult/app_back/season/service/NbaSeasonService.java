package com.sportresult.app_back.season.service;

import com.sportresult.app_back.season.dto.NbaSeasonDto;
import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.season.repository.NbaSeasonRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbaSeasonService {

    private final NbaSeasonRepository nbaSeasonRepository;

    public NbaSeasonDto findNbaSeasonByYear(Integer year) {
        NbaSeasonEntity nbaSeason = nbaSeasonRepository.findByYear(year).orElseThrow(() -> new EntityNotFoundException("NBA Season Not Found for Year: " + year));
        return nbaSeason.toDto();
    }

    public NbaSeasonDto createNbaSeasonByYear(int year) {
        if (nbaSeasonRepository.findByYear(year).isPresent()) {
            throw new EntityExistsException("NBA Season Already Exists for Year: " + year);
        }
        NbaSeasonEntity savedNbaSeason = nbaSeasonRepository.save(NbaSeasonEntity.builder().year(year).build());
        return savedNbaSeason.toDto();
    }
}

package com.sportresult.app_back.season.service;

import com.sportresult.app_back.season.dto.NbaSeasonDto;
import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.season.repository.NbaSeasonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbaSeasonService {

    private final NbaSeasonRepository nbaSeasonRepository;

    public NbaSeasonDto getNbaSeasonByYear(Integer year) {
        Optional<NbaSeasonEntity> optionalNbaSeasonEntity = nbaSeasonRepository.findByYear(year);
        return optionalNbaSeasonEntity.get().toDto();
    }
}

package com.sportresult.app_back.season.repository;

import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NbaSeasonRepository extends JpaRepository<NbaSeasonEntity, UUID> {

    Optional<NbaSeasonEntity> findByYear(Integer year);
}

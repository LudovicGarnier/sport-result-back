package com.sportresult.app_back.standings.repository;

import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.standings.entity.NbaStandingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NbaStandingsRepository extends JpaRepository<NbaStandingsEntity, UUID> {

    List<NbaStandingsEntity> findBySeasonStandings(NbaSeasonEntity nbaSeason);

    List<NbaStandingsEntity> findBySeasonStandingsAndConferenceName(NbaSeasonEntity nbaSeason, String conferenceName);

    List<NbaStandingsEntity> findBySeasonStandingsAndDivisionName(NbaSeasonEntity nbaSeason, String divisionName);

}

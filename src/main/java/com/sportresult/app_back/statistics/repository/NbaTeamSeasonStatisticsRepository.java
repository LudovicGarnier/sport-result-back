package com.sportresult.app_back.statistics.repository;

import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.statistics.entity.NbaTeamSeasonStatisticsEntity;
import com.sportresult.app_back.team.entity.NbaTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NbaTeamSeasonStatisticsRepository extends JpaRepository<NbaTeamSeasonStatisticsEntity, UUID> {

    Optional<NbaTeamSeasonStatisticsEntity> findBySeasonAndTeam(NbaSeasonEntity season, NbaTeamEntity team);
}

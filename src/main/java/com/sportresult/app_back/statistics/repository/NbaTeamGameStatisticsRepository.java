package com.sportresult.app_back.statistics.repository;

import com.sportresult.app_back.game.entity.NbaGameEntity;
import com.sportresult.app_back.statistics.entity.NbaTeamGameStatisticsEntity;
import com.sportresult.app_back.team.entity.NbaTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NbaTeamGameStatisticsRepository extends JpaRepository<NbaTeamGameStatisticsEntity, Integer> {

    Optional<NbaTeamGameStatisticsEntity> findByTeamAndGame(NbaTeamEntity team, NbaGameEntity game);

}

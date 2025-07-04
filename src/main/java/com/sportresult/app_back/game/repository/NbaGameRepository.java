package com.sportresult.app_back.game.repository;

import com.sportresult.app_back.game.entity.NbaGameEntity;
import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.team.entity.NbaTeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface NbaGameRepository extends JpaRepository<NbaGameEntity, UUID> {

    Page<NbaGameEntity> findNbaGameEntitiesBySeason(Pageable pageable, NbaSeasonEntity nbaSeason);

    Page<NbaGameEntity> findNbaGameEntitiesBySeasonAndStage(NbaSeasonEntity season, Integer stage, Pageable pageable);

    @Query("SELECT g FROM NbaGameEntity g WHERE g.season = :season AND (g.home = :nbaTeam OR g.visitor = :nbaTeam)")
    Page<NbaGameEntity> findNbaGameEntitiesBySeasonAndTeam(NbaSeasonEntity season, NbaTeamEntity nbaTeam, Pageable pageable);

}

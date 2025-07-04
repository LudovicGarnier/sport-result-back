package com.sportresult.app_back.team.repository;

import com.sportresult.app_back.team.entity.NbaTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NbaTeamRepository extends JpaRepository<NbaTeamEntity, UUID> {

    List<NbaTeamEntity> findByCode(String code);

    List<NbaTeamEntity> findByConference(String conference);

    List<NbaTeamEntity> findByDivision(String division);

    List<NbaTeamEntity> findByNbaFranchise(Boolean isNbaFranchise);
}

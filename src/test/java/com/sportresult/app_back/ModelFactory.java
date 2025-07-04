package com.sportresult.app_back;

import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.standings.entity.NbaStandingsEntity;
import com.sportresult.app_back.team.entity.NbaTeamEntity;

import java.util.UUID;

public class ModelFactory {

    public static NbaStandingsEntity createNbaStandingsEntity(int year) {
        return NbaStandingsEntity.builder()
                .id(UUID.randomUUID())
                .seasonStandings(NbaSeasonEntity.builder().year(year).build())
                .teamStandings(createNbaTeamEntity("CHI", "East", "Central"))
                .build();
    }

    public static NbaTeamEntity createNbaTeamEntity(String code, String conference, String division) {
        return NbaTeamEntity.builder()
                .code(code)
                .nbaFranchise(true)
                .allStar(false)
                .conference(conference)
                .division(division)
                .build();
    }
}

package com.sportresult.app_back.team.dto;

public record NbaTeamDto(
        String name,
        String nickname,
        String code,
        String city,
        String logo,
        boolean allStar,
        boolean nbaFranchise,
        String conference,
        String division) {
}

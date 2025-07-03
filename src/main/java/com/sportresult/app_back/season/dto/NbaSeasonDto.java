package com.sportresult.app_back.season.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record NbaSeasonDto(
        @Schema(name = "year", example = "2015", required = true)
        int year) {
}

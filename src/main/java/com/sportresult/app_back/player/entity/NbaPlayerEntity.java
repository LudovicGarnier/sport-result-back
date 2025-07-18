package com.sportresult.app_back.player.entity;

import com.sportresult.app_back.player.dto.NbaPlayerDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "nba_player")
public class NbaPlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;

    // Birth
    @Column(name = "birthDate")
    private LocalDate birthDate;
    @Column(name = "country")
    private String country;

    // NbaCareer
    @Column(name = "startYear")
    private Integer startYear;
    @Column(name = "proYear")
    private Integer proYear;

    @Column(name = "height")
    private Double height;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "college")
    private String college;

    // PlayerLeaguesData
    @Column(name = "jerseyNumber")
    private Integer jerseyNumber;
    @Column(name = "isActive")
    private boolean isActive;
    @Column(name = "position")
    private String position;

    public NbaPlayerDto toDto() {
        return NbaPlayerDto.builder()
                .firstname(firstname)
                .lastname(lastname)
                .birthDate(birthDate)
                .country(country)
                .startYear(startYear)
                .proYear(proYear)
                .height(height)
                .weight(weight)
                .college(college)
                .jerseyNumber(jerseyNumber)
                .isActive(isActive)
                .position(position)
                .build();
    }
}

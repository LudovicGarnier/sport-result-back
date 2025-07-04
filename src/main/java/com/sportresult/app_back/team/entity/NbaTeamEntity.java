package com.sportresult.app_back.team.entity;

import com.sportresult.app_back.team.dto.NbaTeamDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "nba_team")
public class NbaTeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "city")
    private String city;

    @Column(name = "logo")
    private String logo;

    @Column(name = "allStar")
    private Boolean allStar;

    @Column(name = "nbaFranchise")
    private Boolean nbaFranchise;

    @Column(name = "conference")
    private String conference;

    @Column(name = "division")
    private String division;

    public NbaTeamDto toDto() {
        return new NbaTeamDto(
                name,
                nickname,
                code,
                city,
                logo,
                allStar,
                nbaFranchise,
                conference,
                division);
    }
}

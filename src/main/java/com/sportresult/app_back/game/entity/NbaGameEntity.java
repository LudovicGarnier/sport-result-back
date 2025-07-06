package com.sportresult.app_back.game.entity;

import com.sportresult.app_back.arena.entity.NbaArenaEntity;
import com.sportresult.app_back.game.dto.NbaGameDto;
import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import com.sportresult.app_back.team.entity.NbaTeamEntity;
import com.sportresult.app_back.utils.StringListConverter;
import com.sportresult.app_back.utils.UniqueStringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "nba_game")
public class NbaGameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private NbaSeasonEntity season;

    @Column(name = "game_start")
    private LocalDateTime gameStart;

    @Column(name = "game_end")
    private LocalDateTime gameEnd;

    @Column(name = "duration")
    private String duration;

    @Column(name = "stage")
    private Integer stage;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "arena_id")
    private NbaArenaEntity arena;

    @ManyToOne
    @JoinColumn(name = "visitor_team_id")
    private NbaTeamEntity visitor;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private NbaTeamEntity home;

    @Column(name = "times_tied")
    private Integer timesTied;

    @Column(name = "lead_changes")
    private Integer leadChanges;

    @Column(name = "home_score")
    private Integer homeScore;

    @Column(name = "home_win")
    private Integer homeWin;

    @Column(name = "home_loss")
    private Integer homeLoss;

    @Column(name = "visitor_score")
    private Integer visitorScore;

    @Column(name = "visitor_win")
    private Integer visitorWin;

    @Column(name = "visitor_loss")
    private Integer visitorLoss;

    @Convert(converter = UniqueStringListConverter.class)
    @Column(name = "officials")
    private Set<String> officials = new HashSet<>();

    @Convert(converter = StringListConverter.class)
    @Column(name = "visitor_line_score")
    private List<String> visitorLineScore = new ArrayList<>();

    @Convert(converter = StringListConverter.class)
    @Column(name = "home_line_score")
    private List<String> homeLineScore = new ArrayList<>();

    public NbaGameDto toDto() {
        if (season == null) {
            season = NbaSeasonEntity.builder().build();
        }
        if (arena == null) {
            arena = NbaArenaEntity.builder().build();
        }
        if (home == null) {
            home = NbaTeamEntity.builder().nbaFranchise(true).allStar(true).build();
        }
        if (visitor == null) {
            visitor = NbaTeamEntity.builder().nbaFranchise(true).allStar(true).build();
        }
        if (officials == null) {
            officials = new HashSet<>();
        }
        return new NbaGameDto(
                season.getYear(),
                gameStart,
                gameEnd,
                duration,
                stage,
                status,
                arena.toDto(),
                home.toDto(),
                visitor.toDto(),
                timesTied,
                leadChanges,
                officials.stream().toList(),
                visitorLineScore,
                homeLineScore,
                homeScore,
                visitorScore,
                homeWin,
                homeLoss,
                visitorWin,
                visitorLoss
        );
    }
}

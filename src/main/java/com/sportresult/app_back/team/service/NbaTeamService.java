package com.sportresult.app_back.team.service;

import com.sportresult.app_back.team.dto.NbaTeamDto;
import com.sportresult.app_back.team.entity.NbaTeamEntity;
import com.sportresult.app_back.team.repository.NbaTeamRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NbaTeamService {

    private final NbaTeamRepository nbaTeamRepository;

    public NbaTeamDto getTeamByCode(String code) {
        List<NbaTeamEntity> nbaTeamEntities = nbaTeamRepository.findByCode(code);

        if (nbaTeamEntities.isEmpty()) {
            throw new EntityNotFoundException("NBA Team Not Found for Code:" + code);
        }
        return nbaTeamEntities.getFirst().toDto();
    }


    public List<NbaTeamDto> getTeamsByConference(String conference) {
        List<NbaTeamEntity> nbaTeamEntities = nbaTeamRepository.findByConference(conference);

        if (nbaTeamEntities.isEmpty()) {
            throw new EntityNotFoundException("NBA Team Not Found for Conference:" + conference);
        }
        return nbaTeamEntities.stream().map(NbaTeamEntity::toDto).collect(Collectors.toList());
    }


    public List<NbaTeamDto> getTeamsByDivision(String division) {
        List<NbaTeamEntity> nbaTeamEntities = nbaTeamRepository.findByDivision(division);

        if (nbaTeamEntities.isEmpty()) {
            throw new EntityNotFoundException("NBA Team Not Found for Division:" + division);
        }
        return nbaTeamEntities.stream().map(NbaTeamEntity::toDto).collect(Collectors.toList());
    }


    public List<NbaTeamDto> getAllNbaTeams() {
        List<NbaTeamEntity> nbaTeamEntities = nbaTeamRepository.findByNbaFranchise(true);

        if (nbaTeamEntities.isEmpty()) {
            throw new EntityNotFoundException("No NBA Team Not Found");
        }
        return nbaTeamEntities.stream().map(NbaTeamEntity::toDto).collect(Collectors.toList());
    }
}

package com.sportresult.app_back.player.service;

import com.sportresult.app_back.player.dto.NbaPlayerDto;
import com.sportresult.app_back.player.entity.NbaPlayerEntity;
import com.sportresult.app_back.player.repository.NbaPlayerRepository;
import com.sportresult.app_back.season.dto.NbaSeasonDto;
import com.sportresult.app_back.season.entity.NbaSeasonEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NbaPlayerServiceTest {

    @Mock
    private NbaPlayerRepository nbaPlayerRepository;

    @InjectMocks
    private NbaPlayerService nbaPlayerService;


}
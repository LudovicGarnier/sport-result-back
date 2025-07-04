package com.sportresult.app_back.player.service;

import com.sportresult.app_back.player.repository.NbaPlayerRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NbaPlayerServiceTest {

    @Mock
    private NbaPlayerRepository nbaPlayerRepository;

    @InjectMocks
    private NbaPlayerService nbaPlayerService;


}
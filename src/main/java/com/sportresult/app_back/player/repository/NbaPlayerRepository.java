package com.sportresult.app_back.player.repository;

import com.sportresult.app_back.player.entity.NbaPlayerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface NbaPlayerRepository extends PagingAndSortingRepository<NbaPlayerEntity, UUID> {

    Page<NbaPlayerEntity> findAllByLastname(Pageable pageable, String lastname);

    Page<NbaPlayerEntity> findAllByFirstname(Pageable pageable, String firstname);

    Page<NbaPlayerEntity> findAllByIsActive(Pageable pageable, boolean active);
}

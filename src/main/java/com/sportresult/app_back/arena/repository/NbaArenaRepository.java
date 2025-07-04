package com.sportresult.app_back.arena.repository;

import com.sportresult.app_back.arena.entity.NbaArenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NbaArenaRepository extends JpaRepository<NbaArenaEntity, UUID> {

    Optional<NbaArenaEntity> findNbaArenaEntitiesByName(String name);
}

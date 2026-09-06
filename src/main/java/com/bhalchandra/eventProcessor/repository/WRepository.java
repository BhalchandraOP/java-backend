package com.bhalchandra.eventProcessor.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.bhalchandra.eventProcessor.entity.WEntity;

import jakarta.persistence.LockModeType;

public interface WRepository extends JpaRepository<WEntity, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<WEntity> findByUserId(UUID userId);
}
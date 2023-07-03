package com.training.repository.impl;

import com.training.domain.Owner;
import com.training.repository.OwnerRepository;
import io.micronaut.transaction.annotation.ReadOnly;
import jakarta.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Singleton
public class OwnerRepositoryImpl implements OwnerRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public OwnerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Optional<Owner> findById(@NotNull long id) {
        return Optional.ofNullable(entityManager.find(Owner.class, id));
    }
}

package com.training.repository.impl;

import com.training.domain.Owner;
import com.training.domain.OwnerRegisterCommand;
import com.training.repository.OwnerRepository;
import jakarta.inject.Singleton;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@Singleton
@Log4j2
public class OwnerRepositoryImpl implements OwnerRepository {
    private static final Logger logger = LogManager.getLogger(OwnerRepositoryImpl.class);
    @PersistenceContext
    private final EntityManager entityManager;

    public OwnerRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public Optional<Owner> findById(@NotNull long id) {
        logger.info("System is checking details for Owner {}", id);
        return Optional.ofNullable(entityManager.find(Owner.class, id));
    }

    @Override
    @Transactional
    public Owner save(@NotNull OwnerRegisterCommand ownerRegisterCommand) {
        Owner owner = new Owner();
        owner.setFirstName(ownerRegisterCommand.getFirstName());
        owner.setLastName(ownerRegisterCommand.getLastName());
        owner.setPhoneNumber(ownerRegisterCommand.getPhoneNumber());
        owner.setEmail(ownerRegisterCommand.getEmail());
        entityManager.persist(owner);
        return owner;
    }
}

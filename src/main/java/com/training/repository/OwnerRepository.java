package com.training.repository;

import com.training.domain.Owner;
import com.training.domain.OwnerRegisterCommand;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface OwnerRepository {
    Optional<Owner> findById(@NotNull long id);
    Owner save(@NotNull OwnerRegisterCommand ownerRegisterCommand);
}

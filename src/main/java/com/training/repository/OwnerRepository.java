package com.training.repository;

import com.training.domain.Owner;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface OwnerRepository {
    Optional<Owner> findById(@NotNull long id);
}

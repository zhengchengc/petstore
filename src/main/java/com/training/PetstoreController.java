package com.training;

import com.training.domain.Owner;
import com.training.repository.OwnerRepository;
import io.micronaut.http.annotation.*;

@Controller("/petstore")
public class PetstoreController {
    private final OwnerRepository ownerRepository;

    public PetstoreController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }

    @Get("/{id}")
    public Owner show(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }
}
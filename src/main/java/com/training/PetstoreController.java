package com.training;

import com.training.domain.Owner;
import com.training.domain.OwnerRegisterCommand;
import com.training.repository.OwnerRepository;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@Controller("/petstore")
@Log4j2
@Introspected
public class PetstoreController {
    private static final Logger logger = LogManager.getLogger(PetstoreController.class);
    private final OwnerRepository ownerRepository;

    public PetstoreController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }

    @Get("/owners/{id}")
    public Owner show(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isPresent()) {
            logger.info("The owner is present, and the phone number is {}", owner.get().getPhoneNumber());
            return owner.get();
        } else {
            logger.info("The owner is not present");
            return null;
        }
    }

    @Post("/owners/register")
    HttpResponse<Owner> save(@Body @Valid OwnerRegisterCommand cmd) {
        logger.info("The command received: first name: {}, last name: {}, phone number: {}, email: {}", cmd.getFirstName(), cmd.getLastName(), cmd.getPhoneNumber(), cmd.getEmail());

        Owner owner = ownerRepository.save(cmd);
        return HttpResponse
                .created(owner)
                .headers(headers -> headers.location(location(owner.getId())));

    }

    private URI location(Long id) {
        return URI.create("/petstore/owners/" + id);
    }
}
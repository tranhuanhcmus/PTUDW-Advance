package com.example.accessData.actor;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/actor")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewActor(@RequestBody ActorDTO form) {
        String firstName = form.getFirstName();
        String lastName = form.getLastName();

        return actorService.addNewActor(firstName, lastName);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<Actor>>  getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Actor>>  getActor(@PathVariable Short id) {
        return actorService.getActor(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String>  deleteActor(@PathVariable Short id) {
        return actorService.deleteActor(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String>  updateActor(@PathVariable Short id, @RequestBody ActorDTO form) {
        String firstName = form.getFirstName();
        String lastName = form.getLastName();
        return actorService.updateActor(id, firstName, lastName);
    }

}

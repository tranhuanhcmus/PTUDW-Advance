package com.example.accessData.actor;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/v1/actors")
public class ActorController {
    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<String> addNewActor(@RequestBody @Valid ActorDTO actorDTO) {
        return actorService.createActor(actorDTO);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<ActorDTO>> getAllActors() {
        return actorService.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ActorDTO> getActor(@PathVariable Short id) {
        return actorService.getActorById(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable Short id) {
        return actorService.deleteActorById(id);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateActor(@PathVariable Short id, @RequestBody @Valid ActorDTO actorDTO) {

        return actorService.updateActor(id, actorDTO);
    }

}

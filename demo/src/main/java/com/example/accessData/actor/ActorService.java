package com.example.accessData.actor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorService {
    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public ResponseEntity<String> addNewActor(ActorDTO actorDTO) {

        actorRepository.save(new Actor(actorDTO));

        return new ResponseEntity<>("Saved Success", HttpStatus.CREATED);
    }


    public ResponseEntity<Iterable<Actor>> getAllActors() {
        return new ResponseEntity<>(actorRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Optional<Actor>> getActorById(Short id) {
        return new ResponseEntity<>(actorRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteActorById(Short id) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (actorOptional.isEmpty()) {
            return new ResponseEntity<>("Actor do not exists", HttpStatus.NOT_FOUND);
        } else {
            actorRepository.deleteById(id);
            return new ResponseEntity<>("Delete Success", HttpStatus.OK);
        }
    }

    public ResponseEntity<String> updateActor(Short id, ActorDTO updateActor) {
        Optional<Actor> actorOptional = actorRepository.findById(id);

        if (actorOptional.isEmpty()) {
            return new ResponseEntity<>("Actor do not exists", HttpStatus.NOT_FOUND);
        }

        String firstName=updateActor.getFirstName();
        String lastName=updateActor.getLastName();

        Actor actor = actorOptional.get();
        actor.setFirstName(firstName);
        actor.setLastName(lastName);

        actorRepository.save(actor);
        return new ResponseEntity<>("Update Success", HttpStatus.OK);
    }
}

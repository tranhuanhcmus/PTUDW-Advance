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

    public ResponseEntity<String> addNewActor(String firstName, String lastName) {
        // Validate input
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST) ;
        }

        // Check if actor with the same details exists
        Optional<Actor> actor = actorRepository.findByFirstNameAndLastName(firstName, lastName);
        if (actor.isPresent()) {
            return new ResponseEntity<>("Actor already exists", HttpStatus.CONFLICT);
        }

        // Create a new actor and save it
        Actor newActor = new Actor();
        newActor.setFirstName(firstName);
        newActor.setLastName(lastName);
        actorRepository.save(newActor);

        return new ResponseEntity<>("Saved Success", HttpStatus.OK);
    }


    public ResponseEntity<Iterable<Actor>> getAllActors() {
        return new ResponseEntity<>(actorRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Optional<Actor>> getActor(Short id) {
        return new ResponseEntity<>(actorRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteActor(Short id) {
        Optional<Actor> actor = actorRepository.findById(id);
        if (!actor.isPresent()) {
            return new ResponseEntity<>("Actor do not exists", HttpStatus.NOT_FOUND);
        } else {
            actorRepository.deleteById(id);
            return new ResponseEntity<>("Delete Success", HttpStatus.OK);
        }
    }

    public ResponseEntity<String> updateActor(Short id, String firstName,String lastName) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (!actorOptional.isPresent()) {
            return new ResponseEntity<>("Actor do not exists", HttpStatus.NOT_FOUND);
        }
        if (firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty()) {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST) ;
        }
        Actor actor=actorOptional.get();
        actor.setFirstName(firstName);
        actor.setLastName(lastName);

        actorRepository.save(actor);
        return new ResponseEntity<>("Update Success", HttpStatus.OK);
    }
}

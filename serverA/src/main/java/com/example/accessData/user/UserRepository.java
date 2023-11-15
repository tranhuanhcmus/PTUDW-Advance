package com.example.accessData.user;

import com.example.accessData.actor.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public interface UserRepository extends JpaRepository<User, Short>, JpaSpecificationExecutor<User> {
    Optional<User> findByUserName(String userName);
}

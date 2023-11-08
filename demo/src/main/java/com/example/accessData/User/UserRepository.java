package com.example.accessData.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	boolean existsByUserNameAndPassword(String username, String password);
	public UserEntity findByUserName(String username);
}

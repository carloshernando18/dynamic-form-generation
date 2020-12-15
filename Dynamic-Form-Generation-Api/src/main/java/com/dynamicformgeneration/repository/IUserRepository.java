package com.dynamicformgeneration.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dynamicformgeneration.entities.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String userName);

	Boolean existsByUsername(String username);

}

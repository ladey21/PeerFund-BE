package com.peerfund.peerfundapp.repositories;

import com.peerfund.peerfundapp.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String emailAddress);

    User findUserByEmail(String email);

    User findUserById(Long id);
    boolean existsByEmail(String emailAddress);



}

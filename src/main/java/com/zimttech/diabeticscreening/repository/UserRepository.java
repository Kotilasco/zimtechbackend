package com.zimttech.diabeticscreening.repository;

import com.zimttech.diabeticscreening.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNationalId(String email);
}

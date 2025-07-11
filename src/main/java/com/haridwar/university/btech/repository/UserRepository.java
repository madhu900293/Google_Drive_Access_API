package com.haridwar.university.btech.repository;

import com.haridwar.university.btech.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);
    Optional<User> findByEmail(String email);


}

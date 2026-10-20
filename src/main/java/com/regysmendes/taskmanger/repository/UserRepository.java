package com.regysmendes.taskmanger.repository;

import com.regysmendes.taskmanger.entities.Task;
import com.regysmendes.taskmanger.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<Task> findByEmail(String username);

}

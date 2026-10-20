package com.regysmendes.taskmanger.repository;

import com.regysmendes.taskmanger.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity, Integer> {

    Optional<RolesEntity> findByName(String role);

}

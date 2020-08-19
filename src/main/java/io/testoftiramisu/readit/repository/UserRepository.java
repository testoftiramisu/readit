package io.testoftiramisu.readit.repository;

import io.testoftiramisu.readit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

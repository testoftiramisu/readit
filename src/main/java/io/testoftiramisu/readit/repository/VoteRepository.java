package io.testoftiramisu.readit.repository;

import io.testoftiramisu.readit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {}

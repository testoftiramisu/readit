package io.testoftiramisu.readit.repository;

import io.testoftiramisu.readit.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}

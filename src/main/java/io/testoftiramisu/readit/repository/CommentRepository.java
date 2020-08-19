package io.testoftiramisu.readit.repository;

import io.testoftiramisu.readit.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

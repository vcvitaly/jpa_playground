package io.github.vcvitaly.jpaplayground.repository;

import io.github.vcvitaly.jpaplayground.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PostRepository.
 *
 * @author Vitalii Chura
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}

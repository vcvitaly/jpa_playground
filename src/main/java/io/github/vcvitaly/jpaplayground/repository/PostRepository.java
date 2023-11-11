package io.github.vcvitaly.jpaplayground.repository;

import io.github.vcvitaly.jpaplayground.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * PostRepository.
 *
 * @author Vitalii Chura
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByIdAndUserUsername(Long id, String username);

    List<Post> findAllByUserUsername(String username);
}

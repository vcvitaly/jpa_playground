package io.github.vcvitaly.jpaplayground.repository;

import io.github.vcvitaly.jpaplayground.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * PostCommentRepository.
 *
 * @author Vitalii Chura
 */
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    Optional<PostComment> findByIdAndUserUsername(Long id, String username);

    List<PostComment> findAllByPostId(Long postId);
}

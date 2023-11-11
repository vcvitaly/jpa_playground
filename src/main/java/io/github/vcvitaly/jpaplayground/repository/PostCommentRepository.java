package io.github.vcvitaly.jpaplayground.repository;

import io.github.vcvitaly.jpaplayground.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PostCommentRepository.
 *
 * @author Vitalii Chura
 */
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}

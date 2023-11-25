package io.github.vcvitaly.jpaplayground.repository;

import io.github.vcvitaly.jpaplayground.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Modifying
    @Query("DELETE FROM PostComment pc WHERE pc.post.id = :postId AND pc.user.username = :username")
    void deleteAllByPostIdAndUserUsername(@Param("postId") Long postId, @Param("username") String username);

    void deleteByIdAndUserUsername(Long id, String username);
}

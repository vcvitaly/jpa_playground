package io.github.vcvitaly.jpaplayground.service;

import io.github.vcvitaly.jpaplayground.dto.postcomment.PostCommentDto;
import io.github.vcvitaly.jpaplayground.dto.postcomment.PostCommentSummaryDto;
import io.github.vcvitaly.jpaplayground.model.Post;
import io.github.vcvitaly.jpaplayground.model.PostComment;
import io.github.vcvitaly.jpaplayground.repository.PostCommentRepository;
import io.github.vcvitaly.jpaplayground.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * PostCommentService.
 *
 * @author Vitalii Chura
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PostCommentService {

    private final MyUserService myUserService;
    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createPostComment(String username, Long postId, PostCommentDto dto) {
        final var user = myUserService.getUser(username);
        final var post = getPost(postId);
        final var postComment = PostComment.builder()
                .body(dto.body())
                .post(post)
                .user(user)
                .build();

        final var savedPostComment = postCommentRepository.save(postComment);
        log.info("Created a post comment {}", savedPostComment);
    }

    @Transactional
    public void updatePostComment(String username, Long id, PostCommentDto dto) {
        final var postComment = getPostComment(username, id);
        postComment.setBody(dto.body());
        final var savedPostComment = postCommentRepository.save(postComment);
        log.info("Updated a post comment {}", savedPostComment);
    }

    private PostCommentSummaryDto getPostCommentDto(String username, Long id) {
        final var postComment = getPostComment(username, id);
        return toPostCommentSummaryDto(postComment);
    }

    private PostComment getPostComment(String username, Long id) {
        return postCommentRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new EntityNotFoundException("No such post comment: " + id));
    }

    private PostCommentSummaryDto toPostCommentSummaryDto(PostComment postComment) {
        return PostCommentSummaryDto.builder()
                .id(postComment.getId())
                .body(postComment.getBody())
                .createdAt(postComment.getCreatedAt())
                .updatedAt(postComment.getUpdatedAt())
                .build();
    }

    public List<PostCommentSummaryDto> getPostComments(Long postId) {
        final var postComments = postCommentRepository.findAllByPostId(postId);
        return postComments.stream()
                .map(this::toPostCommentSummaryDto)
                .toList();
    }

    @Transactional
    public void deletePostComments(String username, Long postId) {
        postCommentRepository.deleteAllByPostIdAndUserUsername(postId, username);
        log.info("Deleted all comments for post id={}", postId);
    }

    private Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No such post: " + id));
    }

    @Transactional
    public void deletePostComment(String username, Long id) {
        postCommentRepository.deleteByIdAndUserUsername(id, username);
        log.info("Deleted a post comment with id={}", id);
    }
}

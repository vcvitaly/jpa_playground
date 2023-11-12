package io.github.vcvitaly.jpaplayground.service;

import io.github.vcvitaly.jpaplayground.dto.postcomment.PostCommentDto;
import io.github.vcvitaly.jpaplayground.dto.postcomment.PostCommentSummaryDto;
import io.github.vcvitaly.jpaplayground.model.Post;
import io.github.vcvitaly.jpaplayground.model.PostComment;
import io.github.vcvitaly.jpaplayground.repository.PostCommentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final PostService postService;
    private final PostCommentRepository postCommentRepository;

    public void createPostComment(String username, Long postId, PostCommentDto dto) {
        final var user = myUserService.getUser(username);
        final var post = postService.getPost(postId);
        final var postComment = PostComment.builder()
                .body(dto.body())
                .post(post)
                .user(user)
                .build();

        final var savedPostComment = postCommentRepository.save(postComment);
        log.info("Created a post comment {}", savedPostComment);
    }

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
}

package io.github.vcvitaly.jpaplayground.controller;

import io.github.vcvitaly.jpaplayground.dto.post.PostListViewDto;
import io.github.vcvitaly.jpaplayground.dto.postcomment.PostCommentDto;
import io.github.vcvitaly.jpaplayground.dto.postcomment.PostCommentSummaryDto;
import io.github.vcvitaly.jpaplayground.service.PostCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * PostCommentController.
 *
 * @author Vitalii Chura
 */
@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
@RequiredArgsConstructor
public class PostCommentController {

    private final PostCommentService postCommentService;

    @ResponseStatus(OK)
    @PostMapping
    public void createPostComment(Principal principal, @PathVariable Long postId, @RequestBody @Valid PostCommentDto dto) {
        postCommentService.createPostComment(principal.getName(), postId, dto);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public void updatePostComment(
            Principal principal, @PathVariable Long postId, @PathVariable Long id, @RequestBody @Valid PostCommentDto dto) {
        postCommentService.updatePostComment(principal.getName(), id, dto);
    }

    @ResponseStatus(OK)
    @GetMapping
    public List<PostCommentSummaryDto> getPostComments(@PathVariable Long postId) {
        return postCommentService.getPostComments(postId);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public void deletePostComment(Principal principal, @PathVariable Long postId, @PathVariable Long id) {
        postCommentService.deletePostComment(principal.getName(), id);
    }
}

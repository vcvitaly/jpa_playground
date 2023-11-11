package io.github.vcvitaly.jpaplayground.controller;

import io.github.vcvitaly.jpaplayground.dto.post.PostDto;
import io.github.vcvitaly.jpaplayground.dto.post.PostListViewDto;
import io.github.vcvitaly.jpaplayground.dto.post.PostSummaryDto;
import io.github.vcvitaly.jpaplayground.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
 * PostController.
 *
 * @author Vitalii Chura
 */
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @ResponseStatus(OK)
    @PostMapping
    public void createPost(Principal principal, @RequestBody @Valid PostDto dto) {
        postService.createPost(principal.getName(), dto);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public void updatePost(Principal principal, @PathVariable Long id, @RequestBody @Valid PostDto dto) {
        postService.updatePost(principal.getName(), id, dto);
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public PostSummaryDto getPost(Principal principal, @PathVariable Long id) {
        return postService.getPostDto(principal.getName(), id);
    }

    @ResponseStatus(OK)
    @GetMapping
    public List<PostListViewDto> getPosts(Principal principal) {
        return postService.getPosts(principal.getName());
    }
}

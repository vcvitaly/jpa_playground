package io.github.vcvitaly.jpaplayground.service;

import io.github.vcvitaly.jpaplayground.dto.post.PostDto;
import io.github.vcvitaly.jpaplayground.dto.post.PostListViewDto;
import io.github.vcvitaly.jpaplayground.dto.post.PostSummaryDto;
import io.github.vcvitaly.jpaplayground.model.MyUser;
import io.github.vcvitaly.jpaplayground.model.Post;
import io.github.vcvitaly.jpaplayground.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PostService.
 *
 * @author Vitalii Chura
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final MyUserService myUserService;
    private final PostRepository postRepository;

    public void createPost(String username, PostDto dto) {
        final var userId = myUserService.getUserDto(username).id();
        final var post = Post.builder()
                .user(MyUser.builder().id(userId).build())
                .title(dto.title())
                .body(dto.body())
                .build();
        final var savedPost = postRepository.save(post);
        log.info("Created a post {}", savedPost);
    }

    public void updatePost(String username, Long id, PostDto dto) {
        final var post = getPost(username, id);
        post.setTitle(dto.title());
        post.setBody(dto.body());
        final var savedPost = postRepository.save(post);
        log.info("Updated a post {}", savedPost);
    }

    private Post getPost(String username, Long id) {
        return postRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new EntityNotFoundException("No such port: " + id));
    }

    public PostSummaryDto getPostDto(String username, Long id) {
        final var post = getPost(username, id);
        return toPostSummaryDto(post);
    }

    private PostSummaryDto toPostSummaryDto(Post post) {
        return PostSummaryDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .body(post.getBody())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public List<PostListViewDto> getPosts(String username) {
        final var posts = postRepository.findAllByUserUsername(username);
        return posts.stream()
                .map(this::toPostListViewDto)
                .toList();
    }

    private PostListViewDto toPostListViewDto(Post post) {
        return PostListViewDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}

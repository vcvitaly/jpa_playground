package io.github.vcvitaly.jpaplayground.dto.post;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * PostListViewDto.
 *
 * @author Vitalii Chura
 */
@Builder
public record PostListViewDto(Long id, String title, LocalDateTime createdAt, LocalDateTime updatedAt) {
}

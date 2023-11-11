package io.github.vcvitaly.jpaplayground.dto.post;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * PostSummaryDto.
 *
 * @author Vitalii Chura
 */
@Builder
public record PostSummaryDto(Long id, String title, String body, LocalDateTime createdAt, LocalDateTime updatedAt) {
}

package io.github.vcvitaly.jpaplayground.dto.postcomment;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * PostCommentSummaryDto.
 *
 * @author Vitalii Chura
 */
@Builder
public record PostCommentSummaryDto(Long id, String body, LocalDateTime createdAt, LocalDateTime updatedAt) {
}

package io.github.vcvitaly.jpaplayground.dto.user;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * UserSummaryDto.
 *
 * @author Vitalii Chura
 */
@Builder
public record UserSummaryDto(Long id, String firstName, String lastName, LocalDateTime createdAt, LocalDateTime updatedAt) {
}

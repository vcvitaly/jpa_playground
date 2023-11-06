package io.github.vcvitaly.jpaplayground.dto;

import lombok.Builder;

/**
 * UserSummaryDto.
 *
 * @author Vitalii Chura
 */
@Builder
public record UserSummaryDto(Long id, String firstName, String lastName) {
}

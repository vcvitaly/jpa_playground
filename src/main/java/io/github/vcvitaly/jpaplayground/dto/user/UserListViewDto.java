package io.github.vcvitaly.jpaplayground.dto.user;

import lombok.Builder;

/**
 * UserListViewDto.
 *
 * @author Vitalii Chura
 */
@Builder
public record UserListViewDto(Long id, String firstName, String lastName) {
}

package io.github.vcvitaly.jpaplayground.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * UserDto.
 *
 * @author Vitalii Chura
 */
@Builder
public record UserDto(@NotNull String firstName, @NotNull String lastName, @NotNull String username, @NotNull String password) {
}

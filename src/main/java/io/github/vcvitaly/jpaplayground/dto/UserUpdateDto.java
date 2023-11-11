package io.github.vcvitaly.jpaplayground.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * UserUpdateDto.
 *
 * @author Vitalii Chura
 */
@Builder
public record UserUpdateDto(@NotNull String firstName, @NotNull String lastName, @NotNull String password) {
}

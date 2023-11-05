package io.github.vcvitaly.jpaplayground.dto;

import jakarta.validation.constraints.NotNull;

/**
 * UserDto.
 *
 * @author Vitalii Chura
 */
public record UserDto(@NotNull String firstName, @NotNull String lastName) {
}

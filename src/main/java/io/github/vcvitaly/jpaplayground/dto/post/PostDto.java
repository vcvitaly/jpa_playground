package io.github.vcvitaly.jpaplayground.dto.post;

import jakarta.validation.constraints.NotNull;

/**
 * PostDto.
 *
 * @author Vitalii Chura
 */
public record PostDto(@NotNull String title, @NotNull String body) {
}

package io.github.vcvitaly.jpaplayground.dto.postcomment;

import jakarta.validation.constraints.NotNull;

/**
 * PostCommentDto.
 *
 * @author Vitalii Chura
 */
public record PostCommentDto(@NotNull String body) {
}

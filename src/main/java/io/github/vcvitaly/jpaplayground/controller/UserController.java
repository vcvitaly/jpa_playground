package io.github.vcvitaly.jpaplayground.controller;

import io.github.vcvitaly.jpaplayground.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController.
 *
 * @author Vitalii Chura
 */
@RestController("/api/v1/users")
public class UserController {

    @PostMapping
    public void createUser(@RequestBody @Valid UserDto dto) {

    }
}

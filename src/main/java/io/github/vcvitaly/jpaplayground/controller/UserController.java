package io.github.vcvitaly.jpaplayground.controller;

import io.github.vcvitaly.jpaplayground.dto.UserDto;
import io.github.vcvitaly.jpaplayground.dto.UserListViewDto;
import io.github.vcvitaly.jpaplayground.dto.UserSummaryDto;
import io.github.vcvitaly.jpaplayground.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

/**
 * UserController.
 *
 * @author Vitalii Chura
 */
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(OK)
    @PostMapping
    public void createUser(@RequestBody @Valid UserDto dto) {
        userService.createUser(dto);
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public UserSummaryDto getUser(@PathVariable Long id) {
        return userService.getUserDto(id);
    }

    @ResponseStatus(OK)
    @PostMapping("/all")
    public List<UserListViewDto> getUsers(@RequestBody Collection<Long> ids) {
        return userService.getUserDtos(ids);
    }

    @ResponseStatus(OK)
    @GetMapping("/all")
    public List<UserListViewDto> getUsers() {
        return userService.getUserDtos();
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody @Valid UserDto dto) {
        userService.updateUser(id, dto);
    }
}

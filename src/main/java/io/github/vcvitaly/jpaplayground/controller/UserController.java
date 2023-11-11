package io.github.vcvitaly.jpaplayground.controller;

import io.github.vcvitaly.jpaplayground.dto.UserDto;
import io.github.vcvitaly.jpaplayground.dto.UserListViewDto;
import io.github.vcvitaly.jpaplayground.dto.UserSummaryDto;
import io.github.vcvitaly.jpaplayground.service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
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

    private final MyUserService myUserService;

    @ResponseStatus(OK)
    @PostMapping
    public void createUser(@RequestBody @Valid UserDto dto) {
        myUserService.createUser(dto);
    }

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public UserSummaryDto getUser(@PathVariable Long id) {
        return myUserService.getUserDto(id);
    }

    @ResponseStatus(OK)
    @GetMapping("/all")
    public List<UserListViewDto> getUsers(Principal principal) {
        System.out.println(principal.getName());
        return myUserService.getUserDtos();
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody @Valid UserDto dto) {
        myUserService.updateUser(id, dto);
    }
}

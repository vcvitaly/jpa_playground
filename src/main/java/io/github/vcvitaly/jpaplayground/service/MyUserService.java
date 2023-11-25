package io.github.vcvitaly.jpaplayground.service;

import io.github.vcvitaly.jpaplayground.dto.user.UserDto;
import io.github.vcvitaly.jpaplayground.dto.user.UserListViewDto;
import io.github.vcvitaly.jpaplayground.dto.user.UserSummaryDto;
import io.github.vcvitaly.jpaplayground.dto.user.UserUpdateDto;
import io.github.vcvitaly.jpaplayground.model.MyUser;
import io.github.vcvitaly.jpaplayground.repository.MyUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * MyUserService.
 *
 * @author Vitalii Chura
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MyUserService {

    private final MyUserRepository myUserRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(UserDto dto) {
        final var encodedPass = passwordEncoder.encode(dto.password());
        final var user = MyUser.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .username(dto.username())
                .password(encodedPass)
                .build();

        final var savedUser = myUserRepository.save(user);

        log.info("Created a user [id={},firstName={},lastName={}]",
                savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName());
    }

    @Transactional
    public void updateUser(String username, UserUpdateDto dto) {
        final var user = getByUsername(username);
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        final var encodedPass = passwordEncoder.encode(dto.password());
        user.setPassword(encodedPass);

        myUserRepository.save(user);

        log.info("Updated a user with id {}", user.getId());
    }

    public MyUser getUser(String username) {
        return getByUsername(username);
    }

    public UserSummaryDto getUserDto(String username) {
        final var user = getByUsername(username);
        return toUserSummaryDto(user);
    }

    private MyUser getUser(Long id) {
        return myUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user with id " + id));
    }

    public List<UserListViewDto> getUserDtos() {
        final var users = myUserRepository.findAll();

        return users.stream()
                .map(this::toUserListViewDto)
                .toList();
    }

    private UserListViewDto toUserListViewDto(MyUser myUser) {
        return UserListViewDto.builder()
                .id(myUser.getId())
                .firstName(myUser.getFirstName())
                .lastName(myUser.getLastName())
                .build();
    }

    private UserSummaryDto toUserSummaryDto(MyUser myUser) {
        return UserSummaryDto.builder()
                .id(myUser.getId())
                .firstName(myUser.getFirstName())
                .lastName(myUser.getLastName())
                .createdAt(myUser.getCreatedAt())
                .updatedAt(myUser.getUpdatedAt())
                .build();
    }

    public MyUser getByUsername(String username) {
        return myUserRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("No such user - " + username));
    }
}

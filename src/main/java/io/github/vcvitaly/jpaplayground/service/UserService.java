package io.github.vcvitaly.jpaplayground.service;

import io.github.vcvitaly.jpaplayground.dto.UserDto;
import io.github.vcvitaly.jpaplayground.dto.UserListViewDto;
import io.github.vcvitaly.jpaplayground.dto.UserSummaryDto;
import io.github.vcvitaly.jpaplayground.model.User;
import io.github.vcvitaly.jpaplayground.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * UserService.
 *
 * @author Vitalii Chura
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserDto dto) {
        final var user = User.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .build();

        final var savedUser = userRepository.save(user);

        log.info("Created a user [id={},firstName={},lastName={}]",
                savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName());
    }

    public void updateUser(Long id, UserDto dto) {
        final var user = getUser(id);
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());

        userRepository.save(user);

        log.info("Updated a user with id {}", user.getId());
    }

    public UserSummaryDto getUserDto(Long id) {
        final var user = getUser(id);
        return toUserSummaryDto(user);
    }

    private User getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No user with id " + id));
    }

    public List<UserListViewDto> getUserDtos(Collection<Long> ids) {
        final var users = userRepository.findAllByIdInOrderById(ids);

        return users.stream()
                .map(this::toUserListViewDto)
                .toList();
    }

    public List<UserListViewDto> getUserDtos() {
        final var users = userRepository.findAll();

        return users.stream()
                .map(this::toUserListViewDto)
                .toList();
    }

    private UserListViewDto toUserListViewDto(User user) {
        return UserListViewDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    private UserSummaryDto toUserSummaryDto(User user) {
        return UserSummaryDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}

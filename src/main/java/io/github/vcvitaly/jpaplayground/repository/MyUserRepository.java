package io.github.vcvitaly.jpaplayground.repository;

import io.github.vcvitaly.jpaplayground.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * MyUserRepository.
 *
 * @author Vitalii Chura
 */
public interface MyUserRepository extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByUsername(String username);
}

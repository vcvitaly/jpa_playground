package io.github.vcvitaly.jpaplayground.repository;

import io.github.vcvitaly.jpaplayground.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * UserRepository.
 *
 * @author Vitalii Chura
 */
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByIdInOrderById(Collection<Long> ids);
}

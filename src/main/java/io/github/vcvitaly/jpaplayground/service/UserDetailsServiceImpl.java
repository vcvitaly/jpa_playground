package io.github.vcvitaly.jpaplayground.service;

import io.github.vcvitaly.jpaplayground.model.MyUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserDetailsServiceImpl.
 *
 * @author Vitalii Chura
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MyUserService myUserService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserService.getByUsername(username);
        return User.builder()
                .username(myUser.getUsername())
                .password(myUser.getPassword())
                .passwordEncoder(passwordEncoder::encode)
                .build();
    }
}

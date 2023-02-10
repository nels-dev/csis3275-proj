package csis3275.project.seasell.user.service;

import csis3275.project.seasell.account.service.BalanceAccountService;
import csis3275.project.seasell.user.dto.UserDto;
import csis3275.project.seasell.user.model.AppUser;
import csis3275.project.seasell.user.model.UserRole;
import csis3275.project.seasell.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    BalanceAccountService accountService;

    public void register(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email exists. Please use another email address");
        }

        if (userRepository.existsByPreferredUserName(userDto.getPreferredUserName())) {
            throw new IllegalArgumentException("User name exists. Please choose another user name");
        }

        String encrypted = passwordEncoder.encode(userDto.getPassword());
        AppUser user = new AppUser();
        user.setPassword(encrypted);
        user.setEmail(userDto.getEmail());
        user.setPreferredUserName(userDto.getPreferredUserName());
        user.setAddress(userDto.getAddress());
        user.setMobile(userDto.getMobile());
        user.setRole(UserRole.CLIENT);
        user.setCreatedAt(OffsetDateTime.now());
        user = userRepository.save(user);

        accountService.createAccountForNewUser(user);
    }
}

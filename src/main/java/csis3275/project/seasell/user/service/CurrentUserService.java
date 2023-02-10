package csis3275.project.seasell.user.service;

import csis3275.project.seasell.user.model.AppUser;
import csis3275.project.seasell.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    @Autowired
    UserRepository userRepository;

    public AppUser getCurrentUser() {

        /*
         * Note: SecurityContextHolder uses a ThreadLocal to store authentication token Therefore this method always
         * return the authenticated user associated to the current thread/request
         */
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(jwt.getSubject())
                .orElseThrow(() -> new UsernameNotFoundException("Unable to load user"));
    }
}

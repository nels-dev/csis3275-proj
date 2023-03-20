package csis3275.project.seasell.user;

import csis3275.project.seasell.user.dto.UserDto;
import csis3275.project.seasell.user.model.AppUser;
import csis3275.project.seasell.user.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/user")
public class UserController {

    @Autowired
    CurrentUserService currentUserService;

    @PutMapping
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        currentUserService.saveUserProfile(userDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public AppUser getCurrentUser() {
        return currentUserService.getCurrentUser();
    }
}

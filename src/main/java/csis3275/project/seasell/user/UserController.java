package csis3275.project.seasell.user;

import csis3275.project.seasell.user.model.AppUser;
import csis3275.project.seasell.user.service.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client/user")
public class UserController {

    @Autowired
    CurrentUserService currentUserService;

    @GetMapping
    public AppUser getCurrentUser() {
        return currentUserService.getCurrentUser();
    }

}

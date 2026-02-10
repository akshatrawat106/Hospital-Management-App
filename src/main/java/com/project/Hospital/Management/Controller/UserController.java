package com.project.Hospital.Management.Controller;

import com.project.Hospital.Management.Entities.Users;
import com.project.Hospital.Management.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addUser(@RequestBody Users user)
    {
        userService.saveUser(user);
        return "User added successfully";
    }
}

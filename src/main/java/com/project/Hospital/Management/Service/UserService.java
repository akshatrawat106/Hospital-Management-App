package com.project.Hospital.Management.Service;

import com.project.Hospital.Management.Entities.Users;
import com.project.Hospital.Management.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public void saveUser(Users user)
    {
        Users Appuser = new Users();
        if(userRepo.existsByUsername(user.getUsername()))
        {
            throw new RuntimeException("User Already Exists");
        }
        Appuser.setUsername(user.getUsername());
        Appuser.setPassword(encoder.encode(user.getPassword()));
        Appuser.setRole(user.getRole());

         userRepo.save(Appuser);
    }

}

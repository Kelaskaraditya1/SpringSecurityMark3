package com.StarkIndustries.SpringSecurityMark3.Service;

import com.StarkIndustries.SpringSecurityMark3.Models.Users;
import com.StarkIndustries.SpringSecurityMark3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    public UserRepository userRepository;

    public boolean signUp(Users users){
        System.out.println(users.getUsername());
        if(userRepository.findByUsername(users.getUsername())==null){
            userRepository.save(users);
            return true;
        }
        return false;
    }

    public boolean login(String username){
        if(userRepository.findByUsername(username)==null)
            return false;
        return true;
    }
}

package com.StarkIndustries.SpringSecurityMark3.Service;

import com.StarkIndustries.SpringSecurityMark3.Models.Users;
import com.StarkIndustries.SpringSecurityMark3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JwtService jwtService;

    public boolean signUp(Users users){

        BCryptPasswordEncoder cryptPasswordEncoder = new BCryptPasswordEncoder(12);

        System.out.println(users.getUsername());
        if(userRepository.findByUsername(users.getUsername())==null){
            users.setPassword(cryptPasswordEncoder.encode(users.getPassword()));
            userRepository.save(users);
            return true;
        }
        return false;
    }

    public String login(Users users){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(),users.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateJwtToken(users.getUsername());
        return "false";
    }
}

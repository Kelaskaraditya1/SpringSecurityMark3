package com.StarkIndustries.SpringSecurityMark3.Service;

import com.StarkIndustries.SpringSecurityMark3.Models.UserPrinciples;
import com.StarkIndustries.SpringSecurityMark3.Models.Users;
import com.StarkIndustries.SpringSecurityMark3.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username);
        return new UserPrinciples(users);
    }
}

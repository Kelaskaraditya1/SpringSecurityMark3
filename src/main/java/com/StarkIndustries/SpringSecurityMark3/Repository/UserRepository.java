package com.StarkIndustries.SpringSecurityMark3.Repository;

import com.StarkIndustries.SpringSecurityMark3.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<Users,Integer> {

    public Users findByUsername(String username);
}

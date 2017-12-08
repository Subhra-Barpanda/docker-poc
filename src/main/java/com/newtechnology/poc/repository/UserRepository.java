package com.newtechnology.poc.repository;

import com.newtechnology.poc.model.User;
import com.newtechnology.poc.model.constants.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(@Param("userName") String userName);
    User findByRollNumber(@Param("userRollNumber")String userRollNumber);
    Set<User> findByRole(@Param("userRole")UserRole userRole);
}

package com.hcl.stockex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.stockex.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

}

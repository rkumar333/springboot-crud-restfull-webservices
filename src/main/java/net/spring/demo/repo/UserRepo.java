package net.spring.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.spring.demo.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

}

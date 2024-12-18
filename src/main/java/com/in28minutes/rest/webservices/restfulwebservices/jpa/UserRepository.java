package com.in28minutes.rest.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;
//JpaRepository extends한 인터페이스 생성 
public interface UserRepository extends JpaRepository<User, Integer> {

}

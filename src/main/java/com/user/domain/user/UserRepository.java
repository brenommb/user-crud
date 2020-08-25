package com.user.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface UserRepository extends JpaRepository<UserEntity, BigDecimal> {

}

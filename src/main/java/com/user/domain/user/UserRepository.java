package com.user.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface UserRepository extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findByEmail(String email);

	List<UserEntity> findByCompany_CompanyNumber(Long companyNumber);

}

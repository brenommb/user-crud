package com.user.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {

    Optional<CompanyEntity> findByCompanyNumber(Long companyNumber);

}

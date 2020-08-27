package com.user.domain.user;

import com.user.domain.common.exceptions.NotFoundException;
import com.user.domain.company.CompanyEntity;
import com.user.domain.company.CompanyService;
import com.user.infrastructure.MessageErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Collections.singletonList;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final CompanyService companyService;

	@Transactional
	public UserEntity persistUser(final UserEntity userEntity) {
		CompanyEntity company = companyService.findByCompanyNumber(userEntity.getCompany().getCompanyNumber());
		userEntity.setCompany(company);
		return userRepository.save(userEntity);
	}

	public List<UserEntity> getUserList() {
		return userRepository.findAll();
	}

	public UserEntity findByUserId(final Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException(MessageErrorCode.USER_NOT_FOUND, singletonList(userId.toString())));

	}

	public List<UserEntity> findByEmail(final String email) {
		List<UserEntity> listUsers = userRepository.findByEmail(email);
		return listUsers;
	}

}

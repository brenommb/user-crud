package com.user.domain.user;

import com.user.domain.common.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public UserEntity persistUser(final UserEntity userEntity) {
		checkEntityToPersist(userEntity);
		return userRepository.save(userEntity);
	}

	private void checkEntityToPersist(final UserEntity userEntity) {
		//TODO: Remover esse método, se for o caso usa optional com flatmap
		CommonUtils.verifyNullArgs(userEntity);
	}

	public List<UserEntity> getUserList() {
		return userRepository.findAll();
	}

}

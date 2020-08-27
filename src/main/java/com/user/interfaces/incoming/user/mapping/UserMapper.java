package com.user.interfaces.incoming.user.mapping;

import com.user.domain.company.CompanyEntity;
import com.user.domain.user.UserEntity;
import com.user.interfaces.incoming.user.request.PersistUserRequest;
import com.user.interfaces.incoming.user.response.UserResponse;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

	default UserEntity mapRequestToEntity(final PersistUserRequest request) {
		// @formatter: off
		return UserEntity.builder().company(CompanyEntity.builder().companyNumber(request.getCompanyId()).build())
				.birthDate(request.getBirthDate()).email(request.getEmail()).build();
		// @formatter: on
	}

	default UserResponse mapToResponse(UserEntity entity) {
		return UserResponse.builder().userId(entity.getUserId()).companyId(entity.getCompany().getCompanyNumber())
				.email(entity.getEmail()).birthDate(entity.getBirthDate()).build();
	}

	default List<UserResponse> mapEntityListToResponse(final List<UserEntity> userList) {
		List<UserResponse> responseList = new ArrayList<>();
		userList.forEach(userEntity -> responseList.add(mapToResponse(userEntity)));
		return responseList;
	}

}

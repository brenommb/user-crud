package com.user.interfaces.incoming.mapping;

import com.user.domain.user.UserEntity;
import com.user.interfaces.incoming.request.UserRequest;
import com.user.interfaces.incoming.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "userId", ignore = true)
	UserEntity mapRequestToEntity(final UserRequest request);

	UserResponse mapToResponse(UserEntity request);

	default List<UserResponse> mapEntityListToResponse(final List<UserEntity> userList) {
		List<UserResponse> responseList = new ArrayList<>();
		userList.forEach(userEntity -> responseList.add(mapToResponse(userEntity)));
		return responseList;
	}

}

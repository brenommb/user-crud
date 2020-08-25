package com.user.interfaces.incoming;

import com.user.domain.user.UserEntity;
import com.user.interfaces.incoming.mapping.UserMapper;
import com.user.interfaces.incoming.request.UserRequest;
import com.user.domain.user.UserService;
import com.user.interfaces.incoming.response.UserResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/point")
@RequiredArgsConstructor
public class UserResource {

	private final UserMapper userMapper;

	private final UserService userService;

	@ApiOperation(value = "This endpoint will save a user.", response = UserResponse.class)
	@PostMapping
	@ResponseStatus(CREATED)
	public ResponseEntity persist(@Valid @RequestBody final UserRequest request) {
		final UserEntity userEntity = userMapper.mapRequestToEntity(request);
		return ResponseEntity.status(CREATED).body(userMapper.mapToResponse(userService.persistUser(userEntity)));
	}

	@ApiOperation(value = "This endpoint will return a list of user.", response = UserResponse.class)
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity getAll() {
		final List<UserEntity> userEntityList = userService.getUserList();
		return ResponseEntity.ok(userMapper.mapEntityListToResponse(userEntityList));
	}

}

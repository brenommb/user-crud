package com.user.interfaces.incoming.user;

import com.user.domain.user.UserEntity;
import com.user.domain.user.UserService;
import com.user.interfaces.incoming.user.mapping.UserMapper;
import com.user.interfaces.incoming.user.request.PersistUserRequest;
import com.user.interfaces.incoming.user.response.UserResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource {

	private final UserMapper userMapper;

	private final UserService userService;

	@ApiOperation(value = "This endpoint will save a user.", response = UserResponse.class)
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity persist(@Valid @RequestBody final PersistUserRequest request) {
		final UserEntity userEntity = userMapper.mapRequestToEntity(request);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(userMapper.mapToResponse(userService.persistUser(userEntity)));
	}

	@ApiOperation(value = "This endpoint will return a list of user.", response = UserResponse.class)
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity getAll() {
		final List<UserEntity> userEntityList = userService.getUserList();
		return ResponseEntity.ok(userMapper.mapEntityListToResponse(userEntityList));
	}

	@ApiOperation(value = "This endpoint will return a user.", response = UserResponse.class)
	@GetMapping("/user-id/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity getUserById(@PathVariable final Long userId) {
		return ResponseEntity.ok(userMapper.mapToResponse(userService.findByUserId(userId)));
	}

	@ApiOperation(value = "This endpoint will return a user.", response = UserResponse.class)
	@GetMapping(value = "email/{email}/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity getByEmail(@PathVariable final String email) {
		return ResponseEntity.ok(userMapper.mapEntityListToResponse(userService.findByEmail(email)));
	}

	@ApiOperation(value = "This endpoint will return a user.", response = UserResponse.class)
	@GetMapping(value = "company-id/{companyId}/")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity getByCompanyId(@PathVariable final Long companyId) {
		return ResponseEntity.ok(userMapper.mapEntityListToResponse(userService.findByCompanyId(companyId)));
	}

	@ApiOperation(value = "This endpoint will delete a user.")
	@DeleteMapping(value = "user-id/{userId}/")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteByUserId(@PathVariable final Long userId) {
		userService.deleteByUserId(userId);
	}

}

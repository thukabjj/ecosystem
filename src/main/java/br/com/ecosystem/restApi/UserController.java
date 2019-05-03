package br.com.ecosystem.restApi;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecosystem.application.UserService;
import br.com.ecosystem.domain.user.UserEntity;
import br.com.ecosystem.infra.util.GsonUtil;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ecosystem/api/v1")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@GetMapping("/users")
	@ApiOperation(value = "Get list of users registred.")
	public ResponseEntity<List<UserEntity>> listUser() {
		logger.info("Start listUser.");
		List<UserEntity> response = userService.getUsers();
		logger.info("Finish listUser. Response: {}",GsonUtil.getInstance(response));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/users/{id}")
	@ApiOperation(value = "Get users registred by id.")
	public ResponseEntity<UserEntity> getUser(@PathVariable(value = "id") Long id) {
		logger.info("Start getUser. Entry: {}",GsonUtil.getInstance(id));
		UserEntity response = userService.getUser(id);
		logger.info("Finish getUser. Response {}",GsonUtil.getInstance(response));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/users")
	@ApiOperation(value = "Register user.")
	public ResponseEntity<UserEntity> registerUser(@RequestBody @Valid UserEntity request) {
		logger.info("Start registerUser. Entry: {}",GsonUtil.getInstance(request));
		UserEntity response = userService.saveUser(request);
		logger.info("Finish registerUser. Response {}",GsonUtil.getInstance(response));
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/users")
	@ApiOperation(value = "Update user.")
	public ResponseEntity<UserEntity> updateUser(@RequestBody @Valid UserEntity request) {
		logger.info("Start updateUser. Entry: {}",GsonUtil.getInstance(request));
		UserEntity response = userService.updateUser(request);
		logger.info("Finish updateUser. Response {}",GsonUtil.getInstance(request));
		return ResponseEntity.ok(response);
	}
	
	@ApiOperation(value = "Delete user.")
	@DeleteMapping("/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable(value = "id") Long id) {
		logger.info("Start deleteUser. Entry: {}",GsonUtil.getInstance(id));
		userService.deleteUser(id);
		logger.info("Finish deleteUser.");
	}

}

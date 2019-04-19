package br.com.ecosystem.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecosystem.application.business.UserBusiness;
import br.com.ecosystem.configuration.util.GsonUtil;
import br.com.ecosystem.domain.model.UserDto;
import br.com.ecosystem.domain.model.UserRequestDto;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ingresse/api/v1")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserBusiness userBusiness;

	@GetMapping("/users")
	@ResponseBody
	@ApiOperation(value = "Get list of users registred.")
	public ResponseEntity<List<UserDto>> listUser() {
		logger.info("Start listUser.");
		List<UserDto> response = userBusiness.getListOfUsers();
		logger.info("Finish listUser. Response: {}",GsonUtil.getInstance(response));
		return ResponseEntity.ok(response);
	}
	@GetMapping("/users/{id}")
	@ResponseBody
	@ApiOperation(value = "Get users registred by id.")
	public ResponseEntity<UserDto> getUser(@PathVariable(value = "id") Long id) {
		logger.info("Start getUser. Entry: {}",GsonUtil.getInstance(id));
		UserDto response = userBusiness.getUser(id);
		logger.info("Finish getUser. Response {}",GsonUtil.getInstance(response));
		return ResponseEntity.ok(response);
	}

	@PostMapping("/users")
	@ResponseBody
	@ApiOperation(value = "Register user.")
	public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserRequestDto userDto) {
		logger.info("Start registerUser. Entry: {}",GsonUtil.getInstance(userDto));
		UserDto response = userBusiness.registerUser(userDto);
		logger.info("Finish registerUser. Response {}",GsonUtil.getInstance(response));
		return ResponseEntity.ok(response);
	}

	@PutMapping("/users")
	@ResponseBody
	@ApiOperation(value = "Update user.")
	public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto) {
		logger.info("Start updateUser. Entry: {}",GsonUtil.getInstance(userDto));
		UserDto response = userBusiness.updateUser(userDto);
		logger.info("Finish updateUser. Response {}",GsonUtil.getInstance(userDto));
		return ResponseEntity.ok(response);
	}
	@ApiOperation(value = "Delete user.")
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id) {
		logger.info("Start deleteUser. Entry: {}",GsonUtil.getInstance(id));
		userBusiness.deleteUser(id);
		logger.info("Finish deleteUser.");
		return ResponseEntity.ok(HttpStatus.NO_CONTENT);
	}

}

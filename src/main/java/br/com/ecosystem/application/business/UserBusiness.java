package br.com.ecosystem.application.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.ecosystem.application.service.UserService;
import br.com.ecosystem.configuration.util.GsonUtil;
import br.com.ecosystem.configuration.validation.CustomValidationException;
import br.com.ecosystem.domain.model.UserDto;
import br.com.ecosystem.domain.model.UserRequestDto;

@Component
public class UserBusiness {
	
	private static final Logger logger = LoggerFactory.getLogger(UserBusiness.class);
	
	@Autowired
	private UserService userService;

	
	public List<UserDto> getListOfUsers() {
		logger.debug("Start getListOfUsers.");
		List<UserDto> response = new ArrayList<>();
		try {
			response = userService.getUsers();	
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the consult of the users",e);
		}finally {
			logger.debug("Finish getListOfUsers. Response: {} ",GsonUtil.getInstance(response));
		}
		return response;
	}
	public UserDto getUser(Long id) {
		logger.debug("Start getUser. Entry: {} ",GsonUtil.getInstance(id));
		UserDto response = null;
		try {
			response = userService.getUser(id);	
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the consult of the user",e);
		}finally {
			logger.debug("Finish getUser. Response: {} ",GsonUtil.getInstance(response));
		}
		return response;
	}
	
	
	public UserDto registerUser(@Valid UserRequestDto userRequest) {
		logger.debug("Start registerUser. Entry: {} ",GsonUtil.getInstance(userRequest));
		UserDto response = null;
		try {
			UserDto userDto = new UserDto();
			userDto.setName(userRequest.getName());
			userDto.setEmail(userRequest.getEmail());
			userDto.setLogin(userRequest.getLogin());
			userDto.setPassword(userRequest.getPassword());
			response = userService.saveUser(userDto);
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the register a new user",e);
		}finally {
			logger.debug("Finish registerUser. Response: {} ", GsonUtil.getInstance(response));
			
		}
		return response;
	}

	public UserDto updateUser(@Valid UserDto userDto) {
		UserDto response = null;
		logger.debug("Start updateUser. Entry: {} ",GsonUtil.getInstance(userDto));
		try {
			UserDto user = userService.getUser(userDto.getId());
			if(Objects.nonNull(user)) {
				response = userService.updateUser(userDto);
			}
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the update a user",e);
		}finally {
			logger.debug("Finish updateUser. Response: {} ", GsonUtil.getInstance(response));
		}
		return response;
	}
	public void deleteUser(Long id) {
		logger.debug("Start deleteUser. Entry: {} ",GsonUtil.getInstance(id));
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the delete a user",e);
		}finally {
			logger.debug("Finish deleteUser.");
		}
		
	}


	

}

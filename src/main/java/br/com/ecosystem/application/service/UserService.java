package br.com.ecosystem.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecosystem.configuration.repository.UserRepository;
import br.com.ecosystem.configuration.util.GsonUtil;
import br.com.ecosystem.configuration.validation.CustomValidationException;
import br.com.ecosystem.domain.entity.UserEntity;
import br.com.ecosystem.domain.model.UserDto;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public UserDto getUser(Long id) {
		logger.debug("Start getUser. Entry: {} ",GsonUtil.getInstance(id));
		UserDto response = new UserDto();
		try {
			Optional<UserEntity> userEntity = userRepository.findById(id);
				if(Objects.nonNull(userEntity.get())) response = new UserDto(userEntity.get());
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the method getUser",e);
		} finally {
			logger.debug("Finish getUser. Response: {} ", GsonUtil.getInstance(response));
		}
		return response;
	}

	public List<UserDto> getUsers() {
		List<UserDto> response = new ArrayList<>();
		logger.debug("Start getUsers.");
		try {
			List<UserEntity> usersEntity = userRepository.findAll();
			usersEntity.stream().forEach(e -> response.add(new UserDto(e)));
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the method getUsers",e);
		}finally {
			logger.debug("Finish getUsers. Response: {} ", GsonUtil.getInstance(response));
		}
		return response;
	}

	public UserDto saveUser(@Valid UserDto userDto) {
		logger.debug("Start saveUser. Entry: {} ",GsonUtil.getInstance(userDto));
		UserDto response = new UserDto();
		try {
			UserEntity entity = new UserEntity();
			entity.setEmail(userDto.getEmail());
			entity.setLogin(userDto.getLogin());
			entity.setName(userDto.getName());
			entity.setPassword(userDto.getPassword());
			entity = userRepository.saveAndFlush(entity);
			response = new UserDto(entity);

		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the method saveUser",e);
		} finally {
			logger.debug("Finish saveUser. Response: {} ", GsonUtil.getInstance(response));
		}
		return response;
	}

	public UserDto updateUser(@Valid UserDto userDto) {
		logger.debug("Start updateUser. Entry: {} ",GsonUtil.getInstance(userDto));
		UserDto response = new UserDto();
		try {
			UserEntity entity = new UserEntity();
			entity.setId(userDto.getId());
			entity.setEmail(userDto.getEmail());
			entity.setLogin(userDto.getLogin());
			entity.setName(userDto.getName());
			entity.setPassword(userDto.getPassword());
			entity = userRepository.saveAndFlush(entity);
			response = new UserDto(entity);
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the method updateUser",e);
		} finally {
			logger.debug("Finish updateUser. Response: {} ", GsonUtil.getInstance(response));
		}
		return response;
	}

	public void deleteUser(Long id) {
		logger.debug("Start deleteUser. Entry: {} ",GsonUtil.getInstance(id));
		try {
			userRepository.deleteById(id);
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the method deleteUser",e);
		} finally {
			logger.debug("Finish deleteUser.");
		}
		
	}

}

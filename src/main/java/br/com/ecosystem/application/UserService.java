package br.com.ecosystem.application;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecosystem.domain.user.UserEntity;
import br.com.ecosystem.infra.repository.UserRepository;
import br.com.ecosystem.infra.util.GsonUtil;
import br.com.ecosystem.infra.validation.CustomValidationException;

@Service
public class UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public UserEntity getUser(Long id) {
		logger.debug("Start getUser. Entry: {} ",GsonUtil.getInstance(id));
		UserEntity response = null;
		try {
			Optional<UserEntity> userEntity = userRepository.findById(id);
				if(Objects.nonNull(userEntity.get())) response = userEntity.get();
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the method getUser",e);
		} finally {
			logger.debug("Finish getUser. Response: {} ", GsonUtil.getInstance(response));
		}
		return response;
	}

	public List<UserEntity> getUsers() {
		logger.debug("Start getUsers.");
		List<UserEntity> response = null;
		try {
			response = userRepository.findAll();
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the method getUsers",e);
		}finally {
			logger.debug("Finish getUsers. Response: {} ", GsonUtil.getInstance(response));
		}
		return response;
	}
	
	@Transactional(rollbackOn = {PersistenceException.class})
	public UserEntity saveUser(@Valid UserEntity request) {
		logger.debug("Start saveUser. Entry: {} ",GsonUtil.getInstance(request));
		UserEntity response = null;
		try {
			response = userRepository.saveAndFlush(request);
		} catch (Exception e) {
			throw new CustomValidationException("There was an error on the method saveUser",e);
		} finally {
			logger.debug("Finish saveUser. Response: {} ", GsonUtil.getInstance(response));
		}
		return response;
	}
	
	@Transactional(rollbackOn = {PersistenceException.class})
	public UserEntity updateUser(@Valid UserEntity request) {
		logger.debug("Start updateUser. Entry: {} ",GsonUtil.getInstance(request));
		UserEntity response = null;
		try {
			response = userRepository.saveAndFlush(request);
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

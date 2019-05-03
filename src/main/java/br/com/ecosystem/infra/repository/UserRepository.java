package br.com.ecosystem.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ecosystem.domain.user.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

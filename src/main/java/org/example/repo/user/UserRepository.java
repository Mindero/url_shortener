package org.example.repo.user;

import org.example.jdbc.jdbcUtils;
import org.example.repo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    int getIdByLoginAndPassword(String login, String password);
    boolean existsByLogin(String login);

    Optional<UserEntity> findOptionalByLoginAndPassword(String login, String password);
}

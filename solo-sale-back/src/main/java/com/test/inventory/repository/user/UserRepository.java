package com.test.inventory.repository.user;

import com.test.inventory.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndIsActiveTrue(Long id);

    Optional<User> findByUsername(String username);

    @Query(value = "SELECT u FROM User as u WHERE lower(u.email) = lower(:email) AND u.isActive = true")
    Optional<User> findByEmail(String email);

    Set<User> findAllByIdInAndIsActive(Set<Long> ids, Boolean isActive);

    @Query(value = """
            SELECT u FROM User as u 
            WHERE (:userName IS NULL OR lower(u.username) like concat(lower(:userName), '%'))
            AND u.isActive = :isActive
            """)
    Page<User> findAllUserByUserName(String userName, Boolean isActive, Pageable pageable);


    @Query("SELECT u from User u WHERE " +
            "(u.username = :username or u.email = :email)")
    User findByUsernameOrEmail(@Param("username") String username,
                                    @Param("email") String email);

    List<User> findByEmailIgnoreCaseIn(Set<String> emails);

    List<User> findByUsernameIgnoreCaseIn(Set<String> userNames);
}
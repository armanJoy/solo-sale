package com.test.inventory.repository.user;


import com.test.inventory.model.user.LogInHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LogInHistoryRepository extends JpaRepository<LogInHistory, Long>, JpaSpecificationExecutor<LogInHistory> {
}
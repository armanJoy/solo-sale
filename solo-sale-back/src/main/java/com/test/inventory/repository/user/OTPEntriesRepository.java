package com.test.inventory.repository.user;

import com.test.inventory.model.user.OTPEntries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPEntriesRepository extends JpaRepository<OTPEntries, Long> {
    OTPEntries findByUserEmail(String userEmail);

    //    @Query(value = "DELETE FROM OTPEntries as o WHERE o.userEmail=:userEmail")
    void deleteByUserEmailIgnoreCase(String userEmail);
}
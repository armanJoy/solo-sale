package com.test.inventory.model.employee;

import com.test.inventory.common.constant.ApplicationConstant;
import com.test.inventory.generic.model.BaseEntity;
import com.test.inventory.model.designation.Designation;
import com.test.inventory.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "employee", indexes = {
        @Index(name = "in_employee_user_id", columnList = "user_id"),
        @Index(name = "in_employee_designation_id", columnList = "designation_id")
})
public class Employee extends BaseEntity {

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "employee_id", unique = true, nullable = false, length = 50)
    private String employeeId;

    @Column(name = "phone_number", nullable = false)
    @Pattern(regexp = ApplicationConstant.PHONE_NUMBER_PATTERN)
    private String phoneNumber;

    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_employee_user_id"))
    private User user;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "designation_id", foreignKey = @ForeignKey(name = "fk_employee_designation_id"))
    private Designation designation;
}
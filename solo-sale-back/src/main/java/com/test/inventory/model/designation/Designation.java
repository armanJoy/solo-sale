package com.test.inventory.model.designation;

import com.test.inventory.generic.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_designation")
public class Designation extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

}
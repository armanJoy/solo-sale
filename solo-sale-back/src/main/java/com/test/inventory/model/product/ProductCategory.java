package com.test.inventory.model.product;

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
@Table(name = "ProductCategory")
public class ProductCategory extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

}
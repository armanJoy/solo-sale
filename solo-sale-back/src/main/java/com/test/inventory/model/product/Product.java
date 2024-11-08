package com.test.inventory.model.product;

import com.test.inventory.generic.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Product")
public class Product extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_category_id"))
    private ProductCategory category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", foreignKey = @ForeignKey(name = "fk_brand_id"))
    private Brand brand;

    private Double sellingPrice;

    private Double mrp;

    private Integer stock = 0;

    public void addToStock(Integer quantity){
        this.stock += Math.abs(quantity);
    }

    public void reduceToStock(Integer quantity){
        this.stock -= Math.abs(quantity);
    }
}
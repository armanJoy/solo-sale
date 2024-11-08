package com.test.inventory.model.sale;

import com.test.inventory.generic.model.BaseEntity;
import com.test.inventory.model.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SoldProduct", indexes = {
        @Index(name = "in_sold_product_sale_id", columnList = "sale_id")
})
public class SoldProduct extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_sold_product_id"))
    private Product product;

    private Integer quantity;

    private Double mrp;

    private Double sellingPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", foreignKey = @ForeignKey(name = "fk_sold_product_sale_id"))
    private Sale sale;
}
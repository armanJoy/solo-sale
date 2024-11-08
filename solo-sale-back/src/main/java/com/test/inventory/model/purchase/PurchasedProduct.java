package com.test.inventory.model.purchase;

import com.test.inventory.generic.model.BaseEntity;
import com.test.inventory.model.product.Product;
import com.test.inventory.model.sale.Sale;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PurchasedProduct", indexes = {
        @Index(name = "in_purchased_product_purchase_id", columnList = "purchase_id")
})
public class PurchasedProduct extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fk_purchase_product_id"))
    private Product product;

    private Integer quantity;

    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", foreignKey = @ForeignKey(name = "fk_purchased_product_purchase_id"))
    private Purchase purchase;
}
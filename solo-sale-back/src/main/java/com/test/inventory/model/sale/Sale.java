package com.test.inventory.model.sale;

import com.test.inventory.generic.model.BaseEntity;
import com.test.inventory.model.purchase.PurchasedProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Sale")
public class Sale extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SoldProduct> soldProducts = new HashSet<>();

    private LocalDate saleDate;

    @Column(name = "customerName", length = 100)
    private String customerName;

    @Column(name = "customerPhone", length = 14)
    private String customerPhone;

    @Column(name = "customerAddress", length = 150)
    private String customerAddress;

    private Double discountPercentage = 0.0;

    private Double deliveryCharge = 0.0;

    private Double totalProductPrice;

    private Double totalPrice;

    private boolean paid = false;

    private boolean delivered = false;

    public void addSoldProduct(SoldProduct soldProduct) {
        this.soldProducts.add(soldProduct);
    }

    @PrePersist
    @PreUpdate
    public void calculateTotalPrice() {
        this.totalProductPrice = this.soldProducts.stream().mapToDouble(item->item.getQuantity()*item.getSellingPrice()).sum();
        this.totalPrice = this.totalProductPrice - this.totalProductPrice * ((discountPercentage > 0) ?
                (discountPercentage / 100) : 0) +  deliveryCharge;
    }
}
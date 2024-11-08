package com.test.inventory.model.purchase;

import com.test.inventory.generic.model.BaseEntity;
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
@Table(name = "Purchase")
public class Purchase extends BaseEntity {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PurchasedProduct> purchasedProducts = new HashSet<>();

    @Column(name = "vendor", length = 100)
    private String vendor;

    private LocalDate purchaseDate;

    private Double totalPrice;

    public void addPurchasedProduct(PurchasedProduct purchasedProduct) {
        this.purchasedProducts.add(purchasedProduct);
    }

    @PrePersist
    @PreUpdate
    public void calculateTotalPrice() {
        this.totalPrice = this.purchasedProducts.stream().mapToDouble(item->item.getQuantity()*item.getPrice()).sum();
    }
}
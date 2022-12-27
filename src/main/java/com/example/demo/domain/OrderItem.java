package com.example.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private UUID uuid;

    @NotNull

    @ManyToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    @NotNull
    private Integer quantity;
    @NotNull(message = "price is mandatory")
    private Double price;

    @ManyToOne
    private Order order;

//    public OrderItem() {
//    }
//
//    public OrderItem(UUID uuid, Integer quantity, Double price, Order order) {
//        this.uuid = uuid;
//        this.quantity = quantity;
//        this.price = price;
//        this.order = order;
//    }
//
//    public UUID getRefProduct() {
//        return uuid;
//    }
//
//    public void setRefProduct(UUID uuid) {
//        this.uuid = uuid;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
}

// package com.likelion.web.model;

// import java.math.BigDecimal;

// import jakarta.persistence.Entity;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Data
// @NoArgsConstructor
// public class Product {
//     @Id
//     private Long id;
//     private Long customerOrderId; // Assuming customerOrder_id is a foreign key in the Product table
//     private String productName;
//     private BigDecimal price;

//     @ManyToOne
//     @JoinColumn(name = "customer_id")
//     private Customer customer;
// }
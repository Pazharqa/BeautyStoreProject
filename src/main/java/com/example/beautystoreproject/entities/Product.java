package com.example.beautystoreproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_name", length = 255)
    private String name;
    @Column(name = "product_image_url")
    private String imageURL;
    @Column(name = "product_brand", length = 100)
    private String brand;
    @Column(name = "product_description", length = 1000)
    private String description;
    @Column(name = "product_ingredients")
    private String ingredients;
    @Column(name = "product_price")
    private int price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    @ManyToOne(fetch = FetchType.EAGER)
    private SubCategory subcategory;
    @ManyToOne(fetch = FetchType.EAGER)
    private Gender gender;
}

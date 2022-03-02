package com.example.beautystoreproject.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "genders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "gender_name", length = 255)
    private String name;
}


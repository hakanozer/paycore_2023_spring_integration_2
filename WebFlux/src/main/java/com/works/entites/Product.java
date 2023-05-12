package com.works.entites;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private Long pid;

    private String title;
    private Integer price;

}

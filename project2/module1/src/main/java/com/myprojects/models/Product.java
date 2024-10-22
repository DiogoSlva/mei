package com.myprojects.models;

import com.myprojects.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private String code;
    private String description;
    private Category category;
    private double price;

}

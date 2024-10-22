package com.myprojects.data;

import com.myprojects.enums.Category;
import com.myprojects.exceptions.AlreadyExistingException;
import com.myprojects.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.ArrayList;
import java.util.List;

public class Products {

    private static List<Product> products = new ArrayList<>();

    private static Logger logger = LoggerFactory.getLogger(Products.class);

    private static Marker readMarker = MarkerFactory.getMarker("product-read-operation");
    private static Marker addMarker = MarkerFactory.getMarker("product-add-operation");

    public static List<Product> get() {
        logger.info(readMarker, "Getting all products.");
        return products;
    }

    public static List<Product> get(Category category) {
        logger.info(readMarker, "Getting products from category {}.", category.toString());
        return products.stream()
                .filter(p -> p.getCategory().equals(category))
                .toList();
    }

    public static Product get(String code) {
        return products.stream()
                .filter(p -> p.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    public static void add(Product product) throws IllegalArgumentException, AlreadyExistingException {
        logger.info(addMarker, "Adding product...pending.");

        if (product == null) {
            logger.trace(addMarker, "Product object cannot be null.");
            throw new IllegalArgumentException();
        }

        if (product.getCode().isEmpty()) {
            logger.debug(addMarker, "Product code cannot be empty.");
            throw new IllegalArgumentException();
        }

        if (products.stream().anyMatch(p -> p.getCode().equals(product.getCode()))) {
            logger.error(addMarker, "Product {} would be duplicated in the list", product.getCode());
            throw new AlreadyExistingException("Products.add()", product.getCode());
        }

        logger.info(addMarker, "Adding product...done.");
        products.add(product);
    }

    public static void add(Product... products) throws IllegalArgumentException, AlreadyExistingException {
        // ... = varargs
        for (Product product : products) {
            add(product);
        }
    }
}


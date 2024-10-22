package com.myprojects.controllers;

import com.myprojects.data.Products;
import com.myprojects.enums.Category;
import com.myprojects.exceptions.AlreadyExistingException;
import com.myprojects.models.Product;

import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/catalog")
public class CatalogController {

    private final Marker marker = MarkerFactory.getMarker("catalog-app");
    private final Logger logger = LoggerFactory.getLogger(CatalogController.class);

    public CatalogController() {
        generateDummyData();  // dummy data generation...
    }

    private void generateDummyData() {
        logger.info(marker, "Catalog dummy data generation...pending.");
        Product product = new Product("C001", "Computer A001", Category.COMPUTERS, 925.4f);

        try {
            Products.add(product);
            Products.add(new Product("C002", "Computer A002", Category.COMPUTERS, 841.94f));
            Products.add(
                    new Product("P001", "Printer P001", Category.PRINTERS, 321.32f),
                    new Product("P002", "Printer P002", Category.PRINTERS, 128.98f)
            );

            // uncomment the following line to force a DEBUG message
            // Products.add(new Product("", "whatever", Category.PRINTERS, 23f));

            // uncomment the following line to force the AlreadyExistingException
            // Products.add(new Product("C002", "...", Category.COMPUTERS, 111.11f));

        } catch (AlreadyExistingException e) {
            logger.error(marker, e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error(marker, "Error: {} | stacktrace: {}", e.getMessage(), e.getStackTrace());
            return;
        }

        logger.info(marker, "Catalog dummy data generation...done.");
    }

    @GetMapping("")
    public ResponseEntity<String> landing(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Catalog is working fine!");
    }

    @GetMapping("/products")
    ResponseEntity<List<Product>> getProducts(){
        List<Product> products;
        logger.info(marker, "getProducts(id) request received... pending.");
        products = Products.get();
        logger.info(marker, "getProducts(id) request received... 200Ok ({}).", products);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/products-by-category/{category}")
    ResponseEntity<List<Product>> getProductsByCategory(@PathVariable("category") Category category){
        List<Product> products;
        logger.info(marker, "getProductsByCategory(id) request received... pending.");
        products = Products.get(category);
        logger.info(marker, "getProductsByCategory(id) request received... 200Ok ({}).", products);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/product/{id}")
    ResponseEntity<Product> getProduct(@PathVariable("id") String id){
        Product product;
        logger.info(marker, "getProduct(id) request received... pending.");
        product = Products.get(id);
        logger.info(marker, "getProduct(id) request received... 200Ok ({}).", product);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}

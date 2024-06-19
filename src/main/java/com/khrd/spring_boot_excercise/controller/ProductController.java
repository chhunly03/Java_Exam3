package com.khrd.spring_boot_excercise.controller;

import com.khrd.spring_boot_excercise.model.entity.Product;
import com.khrd.spring_boot_excercise.model.request.ProductRequest;
import com.khrd.spring_boot_excercise.model.request.ProductRequestForUpdateProduct;
import com.khrd.spring_boot_excercise.model.response.ProductResponse;
import com.khrd.spring_boot_excercise.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("")
    public ResponseEntity<ProductResponse<Product>>addNewProduct(@RequestBody ProductRequest productRequest){
        Integer storeID = productService.addNewProduct(productRequest);
        ProductResponse<Product>response=null;
        if (storeID != null) {
            response = ProductResponse.<Product>builder()
                    .message("Post data product success")
                    .payload(productService.getProductByID(storeID))
                    .httpStatus(HttpStatus.OK)
                    .build();
        } else {
            response = ProductResponse.<Product>builder()
                    .message("Post data product not success")
                    .payload(productService.getProductByID(storeID))
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse<Product>>getProductByID(@PathVariable("id") Integer id){
        ProductResponse<Product>response=ProductResponse.<Product>builder()
                .message("Get product success"+id)
                .payload(productService.getProductByID(id))
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/All")
    public ResponseEntity<ProductResponse<List<Product>>>getAllProduct(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size
    ) {
        int offset = (page - 1) * size;
        ProductResponse<List<Product>> response = ProductResponse.<List<Product>>builder()
                .message("Get product Successfully")
                .payload(productService.getAllProduct(offset, size))
                .httpStatus(HttpStatus.OK)

                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse<Product>> updateProduct(@PathVariable Integer id, @RequestBody ProductRequestForUpdateProduct productRequest) {
        Integer Store = productService.updateProductById(id, productRequest);

        if (Store != null) {
            ProductResponse<Product> response = ProductResponse.<Product>builder()
                    .message("Update product successfully " + id)
                    .payload(productService.getProductByID(id))

                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            System.out.println(response);
            return ResponseEntity.ok(response);
        }else {
            ProductResponse<Product> response = ProductResponse.<Product>builder()
                    .message("Update product not successfully" + id)
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .payload(productService.getProductByID(id))
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }


    }

}

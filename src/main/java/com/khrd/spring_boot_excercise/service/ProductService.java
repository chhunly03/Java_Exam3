package com.khrd.spring_boot_excercise.service;

import com.khrd.spring_boot_excercise.model.entity.Product;
import com.khrd.spring_boot_excercise.model.request.ProductRequest;
import com.khrd.spring_boot_excercise.model.request.ProductRequestForUpdateProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Integer addNewProduct(ProductRequest productRequest);

    Product getProductByID(Integer storeID);

    List<Product> getAllProduct(Integer offset, Integer size);

    Integer updateProductById(Integer id, ProductRequestForUpdateProduct productRequest);
}

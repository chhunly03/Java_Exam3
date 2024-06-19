package com.khrd.spring_boot_excercise.service.serviceImpl;

import com.khrd.spring_boot_excercise.model.entity.Product;
import com.khrd.spring_boot_excercise.model.request.ProductRequest;
import com.khrd.spring_boot_excercise.model.request.ProductRequestForUpdateProduct;
import com.khrd.spring_boot_excercise.repository.ProductRepository;
import com.khrd.spring_boot_excercise.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplement implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImplement(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Integer addNewProduct(ProductRequest productRequest) {
        return productRepository.addNewProduct(productRequest);
    }

    @Override
    public Product getProductByID(Integer storeID) {
        return productRepository.getProductByID(storeID);
    }

    @Override
    public List<Product> getAllProduct(Integer offset, Integer size) {
        return productRepository.getAllProduct(offset,size);
    }

    @Override
    public Integer updateProductById(Integer id, ProductRequestForUpdateProduct productRequest) {
        System.out.println(id);
        return productRepository.editProduct(id,productRequest);
    }
}

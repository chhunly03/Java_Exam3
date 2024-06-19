package com.khrd.spring_boot_excercise.repository;

import com.khrd.spring_boot_excercise.model.entity.Product;
import com.khrd.spring_boot_excercise.model.request.ProductRequest;
import com.khrd.spring_boot_excercise.model.request.ProductRequestForUpdateProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductRepository {

    @Select("""
        INSERT INTO product VALUES (#{req.id},#{req.name},#{req.price_per_unit},#{req.active_for_sell}) RETURNING id
    """)
    Integer addNewProduct(@Param("req") ProductRequest productRequest);

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product getProductByID(Integer storeID);

    @Select("""
        SELECT * FROM product LIMIT #{size} OFFSET #{offset}
    """)
    List<Product> getAllProduct(Integer offset, Integer size);


    @Update("""
        UPDATE product
        SET name = #{req.name},
            price_per_unit = #{req.price_per_unit},
            active_for_sell = #{req.active_for_sell}
        WHERE id = #{id}
        RETURNING id
    """)
    Integer editProduct(Integer id,@Param("req") ProductRequestForUpdateProduct productRequest);

}

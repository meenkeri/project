package com.volusion.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.volusion.service.entity.ProductEntity;
import com.volusion.service.repository.ProductRepository;

/**
 * Data access object for Product.
 * @author shankar meenkeri
 *
 */
@Component
public class ProductDao {
    
    @Autowired
    private ProductRepository productRespository;

    /**
     * Finds a product record by id.
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public ProductEntity findProductById(Long id) {
        return productRespository.findOne(id);
    }

    /**
     * Creates a product record.
     * @param entity
     * @return
     */
    @Transactional
    public ProductEntity createProduct(ProductEntity entity) {
        return productRespository.save(entity);
    }
}

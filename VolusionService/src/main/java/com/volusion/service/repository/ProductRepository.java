package com.volusion.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.volusion.service.entity.ProductEntity;

/**
 * Spring data sample CRUD repository.
 * @author shankar meenkeri
 *
 */
@Repository
public interface ProductRepository  extends CrudRepository<ProductEntity, Long> {

}

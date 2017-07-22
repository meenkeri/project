package com.volusion.service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.volusion.service.entity.OrderEntity;

/**
 * Spring data sample CRUD repository.
 * @author shankar meenkeri
 * 
 */
@Repository
public interface OrderRepository  extends CrudRepository<OrderEntity, Long> {

}

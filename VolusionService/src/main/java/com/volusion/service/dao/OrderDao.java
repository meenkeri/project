package com.volusion.service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.volusion.service.entity.OrderEntity;
import com.volusion.service.repository.OrderRepository;

/**
 * Data access object for Order
 * @author shankar meenkeri
 * 
 */
@Component
public class OrderDao {
    
    @Autowired
    private OrderRepository orderRespository;

    /**
     * Finds a order record by id.
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public OrderEntity findOrderById(Long id) {
        return orderRespository.findOne(id);
    }

    /**
     * Creates a order record.
     * @param entity
     * @return
     */
    @Transactional
    public OrderEntity createOrder(OrderEntity entity) {
        return orderRespository.save(entity);
    }
    
    /**
     * Updates a order record.
     * @param entity
     * @return
     */
    @Transactional
    public OrderEntity updateOrder(OrderEntity entity) {
    	return orderRespository.save(entity);
    }
    
    /**
     * Delete a order record by id
     * @param id
     */
    @Transactional
    public void deleteOrder(Long id) {
    	if(orderRespository.exists(id)) { 
    		orderRespository.delete(id);
    	}
    }
}

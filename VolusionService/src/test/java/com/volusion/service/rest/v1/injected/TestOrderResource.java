package com.volusion.service.rest.v1.injected;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.volusion.service.ApplicationConfig;
import com.volusion.service.rest.v1.OrderResource;
import com.volusion.service.rest.v1.ProductResource;
import com.volusion.service.rest.v1.dto.OrderDto;
import com.volusion.service.rest.v1.dto.ProductDto;

/**
 * Integration test class for the order resource endpoints.
 * @author shankar meenkeri
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
public class TestOrderResource {

    @Autowired
    private OrderResource orderResource;
    @Autowired
    private ProductResource productResource;
    
    @Test
    public void TestCRUDOrder() {
    	//Create sample Products
    	List<ProductDto> list = new ArrayList<>();
    	list.add(createProduct("Product1"));
    	list.add(createProduct("Product2"));
    	
    	//Test create and find a order 
    	String orderName = "Create_Order";
        OrderDto createOrder = new OrderDto.Builder().name(orderName).products(list).build();
        Response createOrderResponse = orderResource.createOrder(createOrder);
        assertNotNull(createOrderResponse.getEntity());
        
        Response findResponse = orderResource.getOrder((Long) createOrderResponse.getEntity());
        assertNotNull(findResponse.getEntity());
        OrderDto foundOrder = (OrderDto) findResponse.getEntity();
        assertEquals(orderName, foundOrder.getName());
        assertEquals(list.get(0).getId(), foundOrder.getProductDtos().get(0).getId());
        assertEquals(list.get(1).getId(), foundOrder.getProductDtos().get(1).getId());
        
        //Test update a order
        String updateOrderName = "Update_Order";
        list.remove(1); //Modify the products list and test the results
        list.add(createProduct("Product3"));
        OrderDto updateOrder = new OrderDto.Builder().id(foundOrder.getId()).name(updateOrderName).products(list).build();
        Response updateOrderResponse = orderResource.updateOrder(updateOrder);
        assertNotNull(updateOrderResponse.getEntity());
        
        findResponse = orderResource.getOrder((Long) updateOrderResponse.getEntity());
        assertNotNull(findResponse.getEntity());
        foundOrder = (OrderDto) findResponse.getEntity();
        assertEquals(updateOrderName, foundOrder.getName());
        assertEquals(list.get(0).getId(), foundOrder.getProductDtos().get(0).getId());
        assertEquals(list.get(1).getId(), foundOrder.getProductDtos().get(1).getId());
        
        //Test delete a order
        Response deleteOrderResponse = orderResource.deleteOrder(foundOrder.getId());
        assertTrue(deleteOrderResponse.getStatus() == HttpStatus.OK.value());

        findResponse = orderResource.getOrder(foundOrder.getId());
        assertTrue(findResponse.getStatus() == HttpStatus.NO_CONTENT.value());
    }
    
    /**
     * Create a Product
     * @param name
     * @return
     */
    public ProductDto createProduct(String name) {
    	ProductDto createProduct = new ProductDto.Builder().name(name).build();
    	Long id = (Long) productResource.createProduct(createProduct).getEntity();
    	createProduct = (ProductDto)productResource.getProduct(id).getEntity();
    	
    	return createProduct;
    }
}

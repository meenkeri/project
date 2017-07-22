package com.volusion.service.rest.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.volusion.service.dao.OrderDao;
import com.volusion.service.dao.ProductDao;
import com.volusion.service.entity.OrderEntity;
import com.volusion.service.entity.ProductEntity;
import com.volusion.service.rest.v1.dto.OrderDto;
import com.volusion.service.rest.v1.dto.ProductDto;

/**
 * Order resource implementation.
 * @author shankar meenkeri
 * 
 */
@Service
public class OrderResourceImpl implements OrderResource {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private ProductDao productDao;

	@Override
	public Response getOrder(Long id) {
		if (id == null) {
			return Response.status(HttpStatus.BAD_REQUEST.value()).build();
		}

		OrderEntity entity = orderDao.findOrderById(id);
		if (entity == null) {
			return Response.status(HttpStatus.NO_CONTENT.value()).build();
		}

		List<ProductDto> productDtos = new ArrayList<>();
		for (ProductEntity pe : entity.getProductEntities()) {
			ProductDto productDto = new ProductDto(pe.getId(), pe.getName());
			productDtos.add(productDto);
		}
		OrderDto dto = new OrderDto.Builder().id(entity.getId()).name(entity.getName()).products(productDtos).build();

		return Response.status(HttpStatus.OK.value()).entity(dto).build();
	}

	@Override
	public Response createOrder(OrderDto orderDto) {
		if (orderDto == null) {
			return Response.status(HttpStatus.BAD_REQUEST.value()).build();
		}

		List<ProductEntity> productEntities = new ArrayList<>();
		for (ProductDto pd : orderDto.getProductDtos()) {
			ProductEntity productEntity = productDao.findProductById(pd.getId());
			if (productEntity == null) {
				return Response.status(HttpStatus.BAD_REQUEST.value()).build();
			}
			productEntities.add(productEntity);
		}
		OrderEntity entity = new OrderEntity.Builder().id(orderDto.getId()).name(orderDto.getName())
				.products(productEntities).build();
		entity = orderDao.createOrder(entity);

		return Response.status(HttpStatus.OK.value()).entity(entity.getId()).build();
	}

	@Override
	public Response updateOrder(OrderDto orderDto) {
		if (orderDto == null) {
			return Response.status(HttpStatus.NO_CONTENT.value()).build();
		}
		
		OrderEntity orderEntity = orderDao.findOrderById(orderDto.getId());
		if (orderEntity == null) {
			return Response.status(HttpStatus.BAD_REQUEST.value()).build();
		}
		
		//Update the Order name
		orderEntity.setName(orderDto.getName());
		
		//Copy the existing ProductEntities
		List<ProductEntity> existingProductEntities = orderEntity.getProductEntities();
		Map<Long, ProductEntity> existingProductEntityMap = new HashMap<Long, ProductEntity>();
		for (ProductEntity productEntity : existingProductEntities)
			existingProductEntityMap.put(productEntity.getId(), productEntity);
		
		//Create the updated list of ProductEntities
		List<ProductEntity> newProductEntities = new ArrayList<>();
		if (orderDto.getProductDtos() != null) {
			for (ProductDto pd : orderDto.getProductDtos()) {
				ProductEntity productEntity = existingProductEntityMap.get(pd.getId());
				if (productEntity == null) {
					productEntity = productDao.findProductById(pd.getId());
					if (productEntity == null)
						return Response.status(HttpStatus.BAD_REQUEST.value()).build();
				}
				newProductEntities.add(productEntity);
			}
		}
		orderEntity.setProductEntities(newProductEntities);
		orderEntity = orderDao.updateOrder(orderEntity);

		return Response.status(HttpStatus.OK.value()).entity(orderEntity.getId()).build();
	}

	@Override
	public Response deleteOrder(Long id) {
		if (id == null) {
			return Response.status(HttpStatus.BAD_REQUEST.value()).build();
		}

		OrderEntity entity = orderDao.findOrderById(id);
		if (entity == null) {
			return Response.status(HttpStatus.NO_CONTENT.value()).build();
		}
		orderDao.deleteOrder(id);

		return Response.status(HttpStatus.OK.value()).build();
	}

}

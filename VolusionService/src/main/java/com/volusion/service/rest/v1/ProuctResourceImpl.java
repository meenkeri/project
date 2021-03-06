package com.volusion.service.rest.v1;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.volusion.service.dao.ProductDao;
import com.volusion.service.entity.ProductEntity;
import com.volusion.service.rest.v1.dto.ProductDto;

/**
 * Product resource implementation.
 * @author shankar meenkeri
 *
 */
@Service
public class ProuctResourceImpl implements ProductResource {
    
    @Autowired
    private ProductDao productDao;

    @Override
    public Response getProduct(Long id) {
        if (id == null) {
            return Response.status(HttpStatus.BAD_REQUEST.value()).build();
        }
        ProductEntity entity = productDao.findProductById(id);
        if (entity == null) {
            return Response.status(HttpStatus.NO_CONTENT.value()).build();
        }
        ProductDto dto = new ProductDto.Builder()
            .id(entity.getId())
            .name(entity.getName())
            .build();
        return Response.status(HttpStatus.OK.value()).entity(dto).build();
    }

    @Override
    public Response createProduct(ProductDto sampleDto) {
        if (sampleDto == null) {
            return Response.status(HttpStatus.BAD_REQUEST.value()).build();
        }
        ProductEntity entity = new ProductEntity.Builder()
            .id(sampleDto.getId())
            .name(sampleDto.getName())
            .build();
        entity = productDao.createProduct(entity);
        return Response.status(HttpStatus.OK.value()).entity(entity.getId()).build();
    }
    
}

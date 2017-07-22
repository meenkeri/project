package com.volusion.service.rest.v1.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Order data transfer object.
 * @author shankar meenkeri
 * 
 */
@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderDto implements Serializable {

    private Long id;
    private String name;
    private List<ProductDto> products;
    
    public OrderDto() {}

	public OrderDto(Long id, String name, List<ProductDto> products) {
		super();
		this.id = id;
		this.name = name;
		this.products = products;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrdertDto(List<ProductDto> productDto) {
    	this.products = productDto;
    }
    
    public List<ProductDto> getProductDtos() {
    	return products;
    }
    
    @Override
    public String toString() {
        return "OrderDto [id=" + id + ", name=" + name + "]";
    }

    /**
     * 
     * @author shankar meenkeri
     *
     */
    public static class Builder {
        private Long id;
        private String name;
        private List<ProductDto> productDtos;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder products(List<ProductDto> productDtos) {
        	this.productDtos = productDtos;
			return this;
        }

        public OrderDto build() {
            return new OrderDto(this);
        }
    }

    private OrderDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.products = builder.productDtos;
    }

}

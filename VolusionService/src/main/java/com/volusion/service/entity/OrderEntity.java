package com.volusion.service.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 * The order entity object.
 * @author shankar meenkeri
 * 
 */
@Entity
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProductEntity> products;
    
    public OrderEntity() {
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
    
    public void setProductEntities(List<ProductEntity> products) {
    	this.products = products;
    }
    
    public List<ProductEntity> getProductEntities() {
    	return products;
    }
    
    public static class Builder {
        private Long id;
        private String name;
        private List<ProductEntity> products;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder products(List<ProductEntity> products){
        	this.products = products;
        	return this;
        }

        public OrderEntity build() {
            return new OrderEntity(this);
        }
    }

    private OrderEntity(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.products = builder.products;
    }
}

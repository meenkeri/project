package com.volusion.service.rest.v1.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Product data transfer object.
 * @author shankar meenkeri
 *
 */
@SuppressWarnings("serial")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductDto implements Serializable {

    private Long id;
    private String name;

    public ProductDto() {}
    
    public ProductDto(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

    @Override
    public String toString() {
        return "ProductDto [id=" + id + ", name=" + name + "]";
    }

    /**
     * 
     * @author shankar meenkeri
     *
     */
    public static class Builder {
        private Long id;
        private String name;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public ProductDto build() {
            return new ProductDto(this);
        }
    }

    private ProductDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }
}

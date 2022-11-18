package com.emart.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {

	private Long productId;
	private String productName;
	private String description;
	private Double pricePerQty;
	private Integer AvailableQty;
	private CategoryDTO categoryDTO;
	
	
	
	public Long getProductId() {
		return productId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Double getPricePerQty() {
		return pricePerQty;
	}
	
	public void setPricePerQty(Double pricePerQty) {
		this.pricePerQty = pricePerQty;
	}
	
	public CategoryDTO getCategoryDTO() {
		return categoryDTO;
	}
	
	public void setCategoryDTO(CategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}

	public Integer getAvailableQty() {
		return AvailableQty;
	}

	public void setAvailableQty(Integer availableQty) {
		AvailableQty = availableQty;
	}

	
}

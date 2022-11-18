package com.emart.repository;

import java.util.List;

import com.emart.dto.ProductDTO;
import com.emart.entity.ProductEntity;


public interface ProductRepository {

	public ProductEntity addProduct(ProductEntity productEntity);
	public ProductEntity getProduct(Long productId);
	public List<ProductEntity> getAllProducts();
	
	public ProductEntity updateProductPrice(Long productId, Double newPrice);
	public List<ProductEntity> searchProductByName(String productName);
	public ProductEntity deleteProductById(Long productId);
}

package com.emart.service;

import java.util.List;

import com.emart.dto.ProductDTO;
import com.emart.exceptions.BusinessException;

public interface ProductService {

	public ProductDTO addProduct(ProductDTO dto) throws BusinessException;
	public ProductDTO getProduct(Long productId);
	public List<ProductDTO> getAllProducts();
	public ProductDTO updateProductPrice(Long productId, Double newPrice);
	public List<ProductDTO> searchProductByName(String productName);
	public ProductDTO deleteProductById(Long productId);
	
}

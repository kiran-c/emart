package com.emart.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.emart.converter.ProductConverter;
import com.emart.dto.ProductDTO;
import com.emart.entity.CategoryEntity;
import com.emart.entity.ProductEntity;
import com.emart.exceptions.BusinessException;
import com.emart.repository.ProductRepository;
import com.emart.repository.impl.ProductRepositoryImpl;
import com.emart.service.ProductService;

public class ProductServiceImpl implements ProductService {

	List<ProductDTO> productList = new ArrayList<>();

	 ProductRepository productRepository;
	 
	public ProductServiceImpl()
	{
		productRepository = new ProductRepositoryImpl();
	}
	
	@Override
	public ProductDTO addProduct(ProductDTO productDto) throws BusinessException 
	{

		/*
		 * for (ProductDTO dto : productList) { if
		 * (dto.getProductName().equalsIgnoreCase(productDto.getProductName())) {
		 * BusinessException be = new BusinessException();
		 * be.setErrorCode("ERR_PRD_007"); be.setErrorMsg("Product Already Exist");
		 * 
		 * throw be; }
		 * 
		 * }
		 */
		
		ProductEntity pe = ProductConverter.productDtoToProductEntityConverter(productDto);
		
		pe = productRepository.addProduct(pe); 
		//productList.add(productDto);
		// DataSerializer.productSerializer(productDto);

		ProductDTO pDTO = ProductConverter.ProductEntityToProductDTO(pe);
		
		System.out.println("Product added Successfully");

		return pDTO;
	}

	@Override
	public ProductDTO getProduct(Long productId) {

		for (ProductDTO dto : productList) {

			if (dto.getProductId().equals(productId)) {
				System.out.println("Porduct Found...");

				return dto;
			}
		}

		return null;

		// ProductDTO dto = DataSerializer.productDeserializer(productId);

		/*
		 * if(dto != null) { return dto; }
		 * 
		 * return null;
		 */
	}

	@Override
	public List<ProductDTO> getAllProducts() {

		List<ProductEntity> productEntities = productRepository.getAllProducts();
		
		for(ProductEntity entity : productEntities)
		{
			ProductDTO dto = ProductConverter.ProductEntityToProductDTO(entity);
			productList.add(dto);
			
		}
		
		return productList;

	}

	@Override
	public List<ProductDTO> searchProductByName(String productName) {

		List<ProductDTO> foundProducts = new ArrayList<>();

		for (ProductDTO product : productList) {
			if (product.getProductName().contains(productName)) {
				foundProducts.add(product);
			}
		}

		if (!foundProducts.isEmpty()) {
			return foundProducts;
		}

		return null;
	}
	
	

	@Override
	public ProductDTO updateProductPrice(Long productId, Double newPrice) {

		ProductEntity pe =  productRepository.updateProductPrice(productId, newPrice);
		ProductDTO dto = ProductConverter.ProductEntityToProductDTO(pe);
		
		return dto;
	}

	@Override
	public ProductDTO deleteProductById(Long productId) {

		ProductEntity pe =  productRepository.deleteProductById(productId);
		ProductDTO dto = ProductConverter.ProductEntityToProductDTO(pe);
		
		return dto;
	}

	// the below code is other way to delete the code by Id
	//@Override
	/*
	 * public ProductDTO deleteProductByIdV2(Long productId) {
	 * 
	 * ProductDTO toBeDeleted = null; List<ProductDTO> newList = new ArrayList<>();
	 * 
	 * for(ProductDTO dto : productList) { if(dto.getProductId().equals(productId))
	 * { toBeDeleted = dto; } else { newList.add(dto); } }
	 * 
	 * return toBeDeleted; }
	 */
	
	
	/*
	 * @Override public ProductDTO updateProductPrice(Long productId, Double
	 * newPrice) {
	 * 
	 * ProductDTO dto = getProduct(productId);
	 * 
	 * if (null != dto) { dto.setPricePerQty(newPrice);
	 * 
	 * for (int i = 0; i < productList.size(); i++) { if
	 * (productList.get(i).getProductId().equals(dto.getProductId())) {
	 * productList.set(i, dto); // productList.add(dto); return dto; } } }
	 * 
	 * return null; }
	 */	
	
	
	
	/*
	 * @Override public ProductDTO deleteProductById(Long productId) {
	 * 
	 * Iterator<ProductDTO> iterator = productList.iterator();
	 * 
	 * ProductDTO product = null;
	 * 
	 * while (iterator.hasNext()) { product = iterator.next();
	 * 
	 * if (product.getProductId().equals(productId)) { iterator.remove(); break; } }
	 * 
	 * return product; }
	 */
	
}

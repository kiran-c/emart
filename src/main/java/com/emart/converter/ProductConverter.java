package com.emart.converter;

import com.emart.dto.CategoryDTO;
import com.emart.dto.ProductDTO;
import com.emart.entity.CategoryEntity;
import com.emart.entity.ProductEntity;

public class ProductConverter {

	public static ProductEntity productDtoToProductEntityConverter(ProductDTO productDto) {
		
		ProductEntity pe = new ProductEntity();

		pe.setProductId(productDto.getProductId());
		pe.setProductName(productDto.getProductName());
		pe.setDescription(productDto.getDescription());
		pe.setPricePerQty(productDto.getPricePerQty());
		pe.setAvailableQty(productDto.getAvailableQty());
		//pe.setCategoryEntity(productDto.getCategoryDTO());

		CategoryEntity categoryEntity = new CategoryEntity();

		categoryEntity.setCategoryId(productDto.getCategoryDTO().getCategoryId());
		/*categoryEntity.setCategoryName(productDto.getCategoryDTO().getCategoryName());
		categoryEntity.setCategoryDescription(productDto.getCategoryDTO().getCategoryDescription());*/

		pe.setCategoryEntity(categoryEntity);

		return pe;
	}

	
	public static ProductDTO ProductEntityToProductDTO(ProductEntity pe)
	{
		ProductDTO pDto = new ProductDTO();

		pDto.setProductId(pe.getProductId());
		pDto.setProductName(pe.getProductName());
		pDto.setDescription(pe.getDescription());
		pDto.setDescription(pe.getDescription());
		pDto.setPricePerQty(pe.getPricePerQty());
		pDto.setAvailableQty(pe.getAvailableQty());

		CategoryDTO categoryDto = new CategoryDTO();

		categoryDto.setCategoryId(pe.getCategoryEntity().getCategoryId());
		categoryDto.setCategoryName(pe.getCategoryEntity().getCategoryName());
		categoryDto.setCategoryDescription(pe.getCategoryEntity().getCategoryDescription());

		pDto.setCategoryDTO(categoryDto);

		return pDto;
	}
}


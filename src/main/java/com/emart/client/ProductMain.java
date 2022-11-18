package com.emart.client;

import java.util.List;
import java.util.Scanner;

import com.emart.dto.CategoryDTO;
import com.emart.dto.ProductDTO;
import com.emart.exceptions.BusinessException;
import com.emart.service.ProductService;
import com.emart.service.impl.ProductServiceImpl;

public class ProductMain {

	
	private static void createMenu() {

		System.out.println("********************************");
		System.out.println("Enter 1 to add the product");
		System.out.println("Enter 2 to get the product");
		System.out.println("Enter 3 to get all the products");
		System.out.println("Enter 4 to update the product");
		System.out.println("Enter 5 to Search the product by name");
		System.out.println("Enter 6 to delete the product");
		System.out.println("Enter 99 to exit the app");
	}
	
	
	public static void main(String[] args) {

		ProductService productService = new ProductServiceImpl();
		
		
		while (true) {
			
			createMenu();
			Scanner sc = new Scanner(System.in);
			Integer in = sc.nextInt();
			
			
			if (in.equals(1)) {
				addProduct(productService);

			} else if (in.equals(2)) {
				getProductById(productService);
			} else if (in.equals(3)) {
				getAllProducts(productService);
			}else if (in.equals(4)) {
				updateProductPrice(productService);
			}else if (in.equals(5)) {
				searchProductByName(productService);
			}else if (in.equals(6)) {
				deleteProduct(productService);
			}else if (in.equals(99)) {
				System.out.println("Exiting the Application");
				System.exit(0); 
			}

		}

	}

	
	private static void deleteProduct(ProductService productService) {
	
		System.out.println("Enter the product id to be deleted");
		
		Scanner sc = new Scanner(System.in);
		Long id = sc.nextLong();
		
		ProductDTO deletedProduct =  productService.deleteProductById(id);
		
		if(deletedProduct !=null)
		{
				System.out.println("Product "+ deletedProduct.getProductName()  +" by id  "+deletedProduct.getProductId()+" has been Deleted...!");
				
		}
		else
		{
			System.out.println("No Product Found with Product id: "+id );
		}

		
	}
	
		


	private static void searchProductByName(ProductService productService) {
		
		System.out.println("Enter the product Name want to search for");
		
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		
		List<ProductDTO> foundProducts =  productService.searchProductByName(name);
		
		if(foundProducts !=null)
		{
			for(ProductDTO product : foundProducts )
			{ 
				System.out.println("ProductName: "+product.getProductName());
				System.out.println("ProductId: "+product.getProductId());
				System.out.println("Price: "+product.getPricePerQty());
				System.out.println("CategoryName: "+product.getCategoryDTO().getCategoryName());
				System.out.println("**********************************");
			}
		}
		else
		{
			System.out.println("No Product Found with Name: "+name);
		}
	}


	private static void updateProductPrice(ProductService productService) {
		getAllProducts(productService);
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the product id of product for price update");
		Long pid = sc.nextLong();
		
		System.out.println("Enter the new product price to be updated");
		Double newPrice = sc.nextDouble();
		
		ProductDTO dto = productService.updateProductPrice(pid, newPrice);
		
		if(dto != null)
		{
			
				System.out.println("Product Id : " + dto.getProductId());
				System.out.println("Poduct Name : " + dto.getProductName());
				System.out.println("Updated Price : " + dto.getPricePerQty());
				System.out.println("Category Name : " + dto.getCategoryDTO().getCategoryName());
				
				System.out.println("************************************");
			 
		}
		
	}


	private static ProductDTO getProductById(ProductService productService) {
		
		
		getAllProducts(productService);
		System.out.println("Enter the Product Id which you are looking for...");
		
		Scanner sc = new Scanner(System.in);
		Long id = sc.nextLong();
		
		ProductDTO product = productService.getProduct(id);
		
		if(null != product)		
		{
			System.out.println("ProductId: "+product.getProductId());
			System.out.println("ProductName: "+product.getProductName());
			System.out.println("Poduct Price: "+product.getPricePerQty());
			System.out.println("CategoryName: "+product.getCategoryDTO().getCategoryName());
			
			return product;

		}
		
		return null;
		
		
	}

	

	
	private static List<ProductDTO> getAllProducts(ProductService productService) {

		List<ProductDTO> dto = productService.getAllProducts();

		if (dto != null) {

			for (ProductDTO productDTO : dto) {
				System.out.println("Product Id : " + productDTO.getProductId());
				System.out.println("Poduct Name : " + productDTO.getProductName());
				System.out.println("Product Price : " + productDTO.getPricePerQty());
				System.out.println("Available Qty : "+productDTO.getAvailableQty());
				System.out.println("Product Description : "+productDTO.getDescription());
				System.out.println("Category Name : "+productDTO.getCategoryDTO().getCategoryName());
				System.out.println("Category Description : "+productDTO.getCategoryDTO().getCategoryDescription());
				
				System.out.println("************************************");
			}

		}
		else
		{
			System.out.println("Product List is Empty...!");
		}
	
	return dto;

	}

	private static ProductDTO addProduct(ProductService productService) {

		ProductDTO productDTO = new ProductDTO();

		Scanner sc = new Scanner(System.in);
		
		productDTO.setProductId(System.currentTimeMillis());
		
		System.out.println("Enter the Product Name");
		productDTO.setProductName(sc.nextLine());
		
		System.out.println("Enter the Product Price");
		productDTO.setPricePerQty(sc.nextDouble());
		
		System.out.println("Enter the Available Qty");
		productDTO.setAvailableQty(sc.nextInt());
		
		//sc.nextLine();
		
		System.out.println("Enter the product Description");
		productDTO.setDescription(sc.nextLine());
		
		
		CategoryDTO catDto = new CategoryDTO();
		catDto.setCategoryId(1L);
		
		sc.nextLine();
		
		System.out.println("Enter the Product Category Name");
		
		catDto.setCategoryName(sc.nextLine());
		
		/* System.out.println("Enter the Product Category Description");
		 * catDto.setCategoryDescription(sc.nextLine());
		 */
		 
		 productDTO.setCategoryDTO(catDto);
	
		ProductDTO dto = null;
		
		try {
		
			 dto = productService.addProduct(productDTO);

		} catch (BusinessException exp) {
			
			System.out.println(exp.getErrorCode());
			System.out.println(exp.getErrorMsg());			
		}
		
		return null;

		
		
	}

}

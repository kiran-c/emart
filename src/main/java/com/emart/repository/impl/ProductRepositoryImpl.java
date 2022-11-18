 package com.emart.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emart.Configuration.DBConfiguration;
import com.emart.entity.CategoryEntity;
import com.emart.entity.ProductEntity;
import com.emart.repository.ProductRepository;

public class ProductRepositoryImpl implements ProductRepository {

	@Override
	public ProductEntity addProduct(ProductEntity productEntity) {
		
		Connection conn = DBConfiguration.getDBConnection();
		
		String sql = "INSERT INTO product(product_name, product_description, price_per_qty, available_qty, category_id_fk) VALUES (?,?,?,?,?)";
		
		try {
		
			PreparedStatement statement = conn.prepareStatement(sql);
		
			
			statement.setString(1, productEntity.getProductName());
			statement.setString(2, productEntity.getDescription());
			statement.setDouble(3, productEntity.getPricePerQty());
			statement.setInt(4, productEntity.getAvailableQty());
			statement.setLong(5, productEntity.getCategoryEntity().getCategoryId());
			
			
			int rowsInserted = statement.executeUpdate();
			
			if(rowsInserted > 0)
			{
				
				System.out.println("A new product has been added Sucessfully");
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return productEntity;
	}

	@Override
	public ProductEntity getProduct(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductEntity> getAllProducts() {
		
	
		List<ProductEntity> productList = new ArrayList<>();
		
		Connection conn = DBConfiguration.getDBConnection();
			
		String sql  = "SELECT p.product_id,  p.product_name, p.product_description, p.price_per_qty, p.available_qty, c.category_name, c.category_desc\r\n"
				+ "FROM product p , category c\r\n"
				+ "WHERE p.category_id_fk = c.category_id";
		
		try {
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery(sql);
			
			
			while(result.next())
			{
				Long productId = result.getLong("product_id");
				String productName = result.getString("product_name");
				String productDescription = result.getString("product_description");
				Double pricePerQty = result.getDouble("price_per_qty");
				Integer availableQty = result.getInt("available_qty");
				String categoryName = result.getString("category_name");
				String categoryDescription = result.getString("category_desc");
				
				
				ProductEntity pe = new ProductEntity();
				pe.setProductId(productId);
				pe.setProductName(productName);
				pe.setDescription(productDescription);
				pe.setPricePerQty(pricePerQty);
				pe.setAvailableQty(availableQty);
				
				CategoryEntity ce = new CategoryEntity();
				ce.setCategoryName(categoryName);
				ce.setCategoryDescription(categoryDescription);
				
				pe.setCategoryEntity(ce);
							
				productList.add(pe);
			}
			
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return productList;
	}

	@Override
	public ProductEntity updateProductPrice(Long productId, Double newPrice) {
		
		Connection conn = DBConfiguration.getDBConnection();
		
		String sql = "UPDATE `product` SET `price_per_qty` = ? WHERE `product_id`= ? ";
		
		ProductEntity pe = null;
		
		try {
			
			PreparedStatement statement =  conn.prepareStatement(sql);
			
			statement.setDouble(1, newPrice);
			statement.setLong(2, productId); 
			
            int rowsInserted = statement.executeUpdate();
            
			if(rowsInserted > 0)
			{
				
				System.out.println("A product has been updated Sucessfully");
				
				sql =  "SELECT p.product_id,  p.product_name, p.product_description, p.price_per_qty, p.available_qty, c.category_name, c.category_desc\r\n"
						+ "FROM product p , category c\r\n"
						+ "WHERE p.category_id_fk = c.category_id AND product_id = ?";
			
				statement = conn.prepareStatement(sql);
				statement.setLong(1, productId);
				ResultSet result = statement.executeQuery();
	
				pe = new ProductEntity();
				
				while(result.next())
				{
					Long pId = result.getLong("product_id");
					String productName = result.getString("product_name");
					String productDescription = result.getString("product_description");
					Double pricePerQty = result.getDouble("price_per_qty");
					Integer availableQty = result.getInt("available_qty");
					String categoryName = result.getString("category_name");
					String categoryDescription = result.getString("category_desc");
					
	
					pe.setProductId(pId);
					pe.setProductName(productName);
					pe.setDescription(productDescription);
					pe.setPricePerQty(pricePerQty);
					pe.setAvailableQty(availableQty);
					
					CategoryEntity ce = new CategoryEntity();
					ce.setCategoryName(categoryName);
					ce.setCategoryDescription(categoryDescription);
					
					pe.setCategoryEntity(ce);
				}
				
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		return pe;
	}

	@Override
	public List<ProductEntity> searchProductByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductEntity deleteProductById(Long productId) {
		
		
		Connection conn = DBConfiguration.getDBConnection();
		ProductEntity pe = null;
		
		
		 String sql =  "SELECT p.product_id,  p.product_name, p.product_description, p.price_per_qty, p.available_qty, c.category_name, c.category_desc\r\n"
				+ "FROM product p , category c\r\n"
				+ "WHERE p.category_id_fk = c.category_id AND product_id = ?";
	 
		PreparedStatement statement;
	
		try {
		
			statement = conn.prepareStatement(sql);
			statement.setLong(1, productId);
			ResultSet result = statement.executeQuery();
			

			pe = new ProductEntity();
			
			while(result.next())
			{
				Long pId = result.getLong("product_id");
				String productName = result.getString("product_name");
				String productDescription = result.getString("product_description");
				Double pricePerQty = result.getDouble("price_per_qty");
				Integer availableQty = result.getInt("available_qty");
				String categoryName = result.getString("category_name");
				String categoryDescription = result.getString("category_desc");
				

				pe.setProductId(pId);
				pe.setProductName(productName);
				pe.setDescription(productDescription);
				pe.setPricePerQty(pricePerQty);
				pe.setAvailableQty(availableQty);
				
				CategoryEntity ce = new CategoryEntity();
				ce.setCategoryName(categoryName);
				ce.setCategoryDescription(categoryDescription);
				
				pe.setCategoryEntity(ce);
			}
		
			if(pe.getProductId() != null)
			{
				sql = "DELETE FROM `product`  WHERE `product_id`= ? ";	
				statement = conn.prepareStatement(sql);
				statement.setLong(1, productId);
				int rowsDeleted = statement.executeUpdate();
				
				if(rowsDeleted > 0)
				{
					System.out.println("product with id "+pe.getProductId() + " has been deleted Successfully...!");
				}
			}
			else
			{
				System.out.println(" No product found with product Id: "+pe.getProductId());
			}
	
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		return pe;
	}

	
}

package com.emart.serializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.emart.dto.ProductDTO;

public class DataSerializer 
{
	public static void productSerializer(ProductDTO dto)
	{
		File file = new File(dto.getProductId()+".ser");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(dto);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	
		finally
		{
			if(fos != null || oos != null)
			{
				try {
					oos.close();
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
		}
	}
	
	public static ProductDTO productDeserializer(Long productId)
	{
		File file = new File(productId+".ser");
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try
		{
			 fis = new FileInputStream(file);
			 ois = new ObjectInputStream(fis); 
			 ProductDTO dto = (ProductDTO)ois.readObject();
			 return dto;	
			 
		}
		catch(FileNotFoundException ex)
		{
			ex.printStackTrace();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}
		
	
		finally
		{
			if(fis != null || ois != null)
			{
				try
				{
					ois.close();
					fis.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
		
		
		
	}
}
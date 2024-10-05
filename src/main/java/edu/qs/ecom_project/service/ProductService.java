package edu.qs.ecom_project.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.qs.ecom_project.model.Product;
import edu.qs.ecom_project.repo.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo repo;
	
	public List<Product> getAllProductsDao(){
		
		return repo.findAll();
	}
	
	public Product getProductByIdDao(int id) {
		//Find By Id return you the optional product not the actual product
		//Product can be null in case there is no matching ID so we should handle that 
		Optional<Product> opt=repo.findById(id);//It is a container which has a method ispresent return boolean
		if(opt.isPresent())
		{
			return opt.get();
		}
		else {
			return null;
		}
		
	}

	public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());
		return repo.save(product);
		
	}

	public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		repo.save(product);
		product.setImageData(imageFile.getBytes());
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		return repo.save(product);
	}

	public void deleteProduct(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}
	
	public List<Product> searchProducts(String keyword)
	{
		return repo.searchProducts(keyword);
	}

	
}

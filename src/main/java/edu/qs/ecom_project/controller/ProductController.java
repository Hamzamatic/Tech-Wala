package edu.qs.ecom_project.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.qs.ecom_project.model.Product;
import edu.qs.ecom_project.service.ProductService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
// Cross orign helps with CORS=Cross orign Resource sharing helps react to send req on same port
@RequestMapping("/api")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@GetMapping("/")
	public String Greet()
	{
		return "hello";
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>>  getAllProducts(){
		return new ResponseEntity<>(service.getAllProductsDao(), HttpStatus.OK);
		
	}
	@GetMapping("/product/{id}")
	public ResponseEntity<Product>  getProductById(@PathVariable int id) {
		Product product=service.getProductByIdDao(id);
		if(product==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<>(product,HttpStatus.OK);
		}
		
	}
	
	@PostMapping("/product")
	ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
		try {
			Product p1=service.addProduct(product,imageFile);
			return new ResponseEntity<>(p1,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/product/{productId}/image")
	public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId)
	{
		Product product=service.getProductByIdDao(productId);
		byte[] imageFile=product.getImageData();
		return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile)
	{
		Product product1;
		try {
			product1 = service.updateProduct(id, product, imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("Failed To Update", HttpStatus.BAD_REQUEST);	

		}
		if(product1 != null)
		{
			return new ResponseEntity<>("Updated", HttpStatus.OK);
		}
		else
		return new ResponseEntity<>("Failed To Update", HttpStatus.BAD_REQUEST);	
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		Product product=service.getProductByIdDao(id);
		if(product !=null) {
			service.deleteProduct(id);
			return new ResponseEntity<>("Deleted",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);	

		}
		
	}
	
	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
		List<Product> products=service.searchProducts(keyword);
		 System.out.println("searching with " + keyword);
	        return new ResponseEntity<>(products, HttpStatus.OK);
		
	}
	
}
	 
	
	


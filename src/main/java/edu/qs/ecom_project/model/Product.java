package edu.qs.ecom_project.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	
	private String name;
	private String description;
	private BigDecimal price; 
	private String brand;
	private String category;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date releaseDate;
	private boolean productAvailable;
	private int stockQuantity;
	
	//IMAGE ATTRIBUTES
	private String imageName;
	private String imageType;
	
	@Lob 
	//large object
	private byte[] imageData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public boolean isProductAvailable() {
		return productAvailable;
	}

	public void setProductAvailable(boolean productAvailable) {
		this.productAvailable = productAvailable;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
	

	

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", Brand="
				+ brand + ", category=" + category + ", releaseDate=" + releaseDate + ", productAvailable="
				+ productAvailable + ", stockQuantity=" + stockQuantity + ", imageName=" + imageName + ", imageType="
				+ imageType + ", imageData=" + Arrays.toString(imageData) + "]";
	}

	
	
	
}


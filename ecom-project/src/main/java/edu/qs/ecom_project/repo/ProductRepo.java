package edu.qs.ecom_project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.qs.ecom_project.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}

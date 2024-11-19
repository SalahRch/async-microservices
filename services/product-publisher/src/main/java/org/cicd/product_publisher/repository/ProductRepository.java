package org.cicd.product_publisher.repository;

import org.cicd.product_publisher.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProductRepository  extends MongoRepository<Product, String>,
        PagingAndSortingRepository<Product,String>
 {
  Optional<Product> findByProductid(Integer product_id);
}

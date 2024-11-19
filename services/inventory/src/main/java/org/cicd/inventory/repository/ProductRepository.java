package org.cicd.inventory.repository;

import org.cicd.inventory.model.ProductPublished;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends MongoRepository<ProductPublished, String> ,
        PagingAndSortingRepository<ProductPublished,String>{
}

package org.cicd.category.Repository;

import org.cicd.category.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String >,
        PagingAndSortingRepository<Category, String>
{

    Optional<Category> findByCategoryid(Integer Categoryid);
    boolean existsByCategoryid(Integer Categoryid);
}

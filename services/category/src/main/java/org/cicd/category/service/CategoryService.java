package org.cicd.category.service;


import org.cicd.category.model.Category;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;


public interface CategoryService {

    Category Save(Category category);
    boolean existsById(Integer categoryId);
}

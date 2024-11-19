package org.cicd.category.service.Impl;


import org.cicd.category.Repository.CategoryRepository;
import org.cicd.category.exception.DuplicatedException;
import org.cicd.category.model.Category;
import org.cicd.category.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category Save(Category category) {
        Optional<Category> existingCategory = repository.findByCategoryid(category.getCategoryid());
        if (existingCategory.isPresent()) {
            throw new DuplicatedException("Category with id :"+ category.getCategoryid()+" already exists.");
        }
        return repository.save(category);
    }

    @Override
    public boolean existsById(Integer categoryid) {
        return repository.existsByCategoryid(categoryid);
    }
}

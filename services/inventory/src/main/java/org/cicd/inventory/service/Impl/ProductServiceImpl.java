package org.cicd.inventory.service.Impl;

import org.cicd.inventory.model.ProductPublished;
import org.cicd.inventory.repository.ProductRepository;
import org.cicd.inventory.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductPublished save(ProductPublished productPublished) {
        return productRepository.save(productPublished);
    }

    @Override
    public Page<ProductPublished> listProducts(final Pageable pageable) {
        return productRepository.findAll(pageable);

    }
}

package org.cicd.inventory.service;

import org.cicd.inventory.model.ProductPublished;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ProductService {
    ProductPublished save(ProductPublished productPublished);

    Page<ProductPublished> listProducts(Pageable pageable);
}

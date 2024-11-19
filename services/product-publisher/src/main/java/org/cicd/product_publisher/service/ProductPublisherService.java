package org.cicd.product_publisher.service;


import org.cicd.product_publisher.model.Product;
import org.springframework.stereotype.Service;


public interface ProductPublisherService {

    void publish (Product product);
}

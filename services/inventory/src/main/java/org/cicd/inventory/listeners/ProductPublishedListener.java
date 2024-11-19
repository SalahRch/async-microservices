package org.cicd.inventory.listeners;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cicd.inventory.exception.InvalidMessageException;
import org.cicd.inventory.model.Notification;
import org.cicd.inventory.model.ProductPublished;
import org.cicd.inventory.service.NotificationService;
import org.cicd.inventory.service.ProductService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ProductPublishedListener {

    private final ObjectMapper objectMapper;

    private final ProductService productService;

    private final NotificationService notificationService;


    public ProductPublishedListener(ObjectMapper objectMapper, ProductService productService, NotificationService notificationService) {
        this.objectMapper = objectMapper;
        this.productService = productService;
        this.notificationService = notificationService;
    }


    @KafkaListener(topics = "product.published")
    public String Listens(final String in) {
        log.info("Product Received {}", in);
        Map<String, Object> payload = readJsonAsMap(in);
        ProductPublished productPublished = productFromPayload(payload);
        productService.save(productPublished);
        final String message = String.format(
                "Product '%s' [%s] persisted!",
                productPublished.getProduct_id(),
                productPublished.getProduct_name()
        );
        notificationService.publishNotification(Notification.builder().message(message)
                .service("inventory.service").timestamp(LocalDateTime.now()).build());
        return in;
    }

    private Map<String, Object> readJsonAsMap(final String json) {
        try{
            final TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
            return objectMapper.readValue(json, typeRef);
        } catch(JsonProcessingException ex) {
            throw new InvalidMessageException();
        }
    }

    private ProductPublished productFromPayload(final Map<String, Object> payload) {
        final Integer category_id = (Integer)((HashMap<String, Object>)payload.get("category")).get("category_id");
        return ProductPublished.builder()
                .product_id((Integer)payload.get("productid"))
                .product_name(payload.get("product_name").toString())
                .price((Double) payload.get("price"))
                .category_id(category_id)
                .build();
    }
}

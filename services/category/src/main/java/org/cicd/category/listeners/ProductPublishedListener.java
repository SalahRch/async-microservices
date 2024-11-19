package org.cicd.category.listeners;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cicd.category.exception.InvalidMessageException;
import org.cicd.category.model.Category;
import org.cicd.category.model.Notification;
import org.cicd.category.service.CategoryService;
import org.cicd.category.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ProductPublishedListener {
    
    private final ObjectMapper objectMapper;
    
    private final CategoryService categoryService;
    
    private final NotificationService notificationService;


    public ProductPublishedListener(ObjectMapper objectMapper, CategoryService categoryService, NotificationService notificationService) {
        this.objectMapper = objectMapper;
        this.categoryService = categoryService;
        this.notificationService = notificationService;
    }
    
    @KafkaListener(topics = "product.published")
    public void listens(final String in){
        log.info("Category received {}", in);
        Map<String, Object> payload = readJsonAsMap(in);
        Category category = categoryFromPayload(payload);
        if (categoryService.existsById(category.getCategoryid())) {
            log.info("Category with id {} already exists. Skipping...", category.getCategoryid());
            return;
        }
        Category savedcategory = categoryService.Save(category);

        final String message = String.format(
                "Category '%s' [%d] persisted!",
                savedcategory.getCategory_name(),
                savedcategory.getCategoryid()
        );
        notificationService.publishNotification(Notification.builder().
                message(message)
                .timestamp(LocalDateTime.now())
                .service("category-persistence")
                .build()
        );
    }

    private Category categoryFromPayload(Map<String, Object> payload) {
        // Extract the nested 'category' map
        Map<String, Object> categoryMap = (Map<String, Object>) payload.get("category");

        // Extract values from the category map
        Integer category_id = (Integer) categoryMap.get("category_id");
        String category_name = categoryMap.get("category_name").toString();
        String category_description = categoryMap.get("category_description").toString();

        // Build and return the Category object
        return Category.builder()
                .categoryid(category_id)
                .category_name(category_name)
                .category_description(category_description)
                .build();
    }


    private Map<String, Object> readJsonAsMap(final String json) {
        try{
            final TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
            return objectMapper.readValue(json, typeRef);
        } catch(JsonProcessingException ex) {
            throw new InvalidMessageException();
        }
    }
}

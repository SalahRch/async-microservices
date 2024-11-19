package org.cicd.product_publisher.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.cicd.product_publisher.repository.ProductRepository;
import org.cicd.product_publisher.service.ProductPublisherService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;



@Component
@Slf4j
@EnableScheduling
public class ScheduledProductPublisher {

    private Boolean isFirstExecution = true;
    private Integer counter;
    private long maxProductCount =15;
    private final MongoTemplate mongoTemplate;
    private final ProductRepository productRepository;
    private final ProductPublisherService productPublisherService;
    private final ThreadPoolTaskScheduler taskScheduler;

    public ScheduledProductPublisher(MongoTemplate mongoTemplate, ProductRepository productRepository,
                                     ProductPublisherService productPublisherService, ThreadPoolTaskScheduler taskScheduler) {
        resetCounter();
        this.mongoTemplate = mongoTemplate;
        this.productRepository = productRepository;
        this.productPublisherService = productPublisherService;
        this.taskScheduler = taskScheduler;
    }

    @Scheduled(cron = "0/20 * * * * *")
    public void publishProduct() {
        if (isFirstExecution) {
            initApp();
            isFirstExecution = false;
        }
        log.info("Searching for product with ID: {} ...", counter);

        while (counter <= maxProductCount) {
            productRepository.findByProductid(counter).ifPresentOrElse(product -> {
                counter += 1;
                productPublisherService.publish(product);
                log.info("Attempting to publish product '{}' to Kafka", product.getProduct_name());
                log.info("Product '{}' [{}] published.", product.getProduct_name(), product.getProductid());
            }, this::resetCounter);
        }

        if (counter > maxProductCount) {
            stopScheduler();
            log.info("Maximum product count reached. Stopping the scheduler.");
        }

    }

    private void initApp() {
        log.info("CommandLineRunner: Starting cleanup tasks...");
        mongoTemplate.dropCollection("product_persisted");
        mongoTemplate.dropCollection("category_persisted");
        log.info("MongoDB collections dropped successfully.");
        taskScheduler.setPoolSize(1);
    }

    private void resetCounter() {
        this.counter = 1;
    }

    private void stopScheduler() {
        log.info("Stopping the product publishing scheduler...");
        log.info("Total number of products published : [{}]", maxProductCount);
        taskScheduler.shutdown();

    }
}

package org.cicd.product_publisher.config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "kafka.product")
@Builder
@AllArgsConstructor
@Data
@NoArgsConstructor
public class KafkaConfigProps {

    String topic;

}

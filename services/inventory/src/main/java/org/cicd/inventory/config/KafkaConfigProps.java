package org.cicd.inventory.config;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.notification")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class KafkaConfigProps {
    private String topic ;
}

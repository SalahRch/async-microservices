package org.cicd.inventory.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value="product_persisted")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPublished {
    @Id
    private Integer product_id;
    private String product_name;
    private Double price;
    private int category_id;
}

package eu.tcitsolutions.dietApp.core.domain.dto;

import eu.tcitsolutions.dietApp.core.domain.entity.Product;

import java.util.Set;

public class MealDTO {

    private Long id;
    private Set<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}

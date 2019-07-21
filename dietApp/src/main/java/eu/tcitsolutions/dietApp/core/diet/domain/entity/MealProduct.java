package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meal_product")
public class MealProduct implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn
    private Meal meal;

    @Id
    @ManyToOne
    @JoinColumn
    private Product product;

    private int weight;

    public MealProduct(Product product, int weight){
        this.product = product;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealProduct)) return false;
        MealProduct that = (MealProduct) o;
        return Objects.equals(meal.getMealNo(), that.meal.getMealNo()) &&
                Objects.equals(product.getName(), that.product.getName()) &&
                Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meal.getMealNo(), product.getName(), weight);
    }
}

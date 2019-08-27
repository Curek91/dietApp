package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_prod_seq_generator")
    @SequenceGenerator(name="meal_prod_seq_generator", sequenceName = "meal_prod_seq", allocationSize=1)
    private Long id;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Meal meal;

    @ManyToOne
    @JoinColumn
    private Product product;

    @Column(name = "WEIGHT")
    private int weight;

    public MealProduct(Product product, int weight){
        this.product = product;
        this.weight = weight;
    }

    public MealProduct(Meal meal, Product product, int weight){
        this.meal = meal;
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

package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "meal_product")
public class MealProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meal_prod_seq_generator")
    @SequenceGenerator(name="meal_prod_seq_generator", sequenceName = "meal_prod_seq", allocationSize=1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @JsonIgnore
    private Meal meal;

    @ManyToOne
    @JoinColumn
    private Product product;

    private int weight;


    public MealProduct(Meal meal, Product product, int weight){
        this.meal = meal;
        this.product = product;
        this.weight = weight;
    }

    public MealProduct() {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}

package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "meal_product")
public class MealProduct {

    @EmbeddedId
    private MealProductId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("mealId")
    private Meal meal;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @Column(name = "weight")
    private int weight = 0;

    public MealProduct(){

    }

    public MealProduct(Meal meal, Product product){
        this.meal = meal;
        this.product = product;
        this.id = new MealProductId(meal.getId(), product.getId());
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MealProduct that = (MealProduct) o;
        return Objects.equals(meal, that.product) && Objects.equals(product, that.meal);
    }

    @Override
    public int hashCode(){
        return Objects.hash(meal, product);
    }
}

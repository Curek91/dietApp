package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MealProductId implements Serializable {
    @Column(name = "meal_id")
    private Long mealId;

    @Column(name = "product_id")
    private Long productId;

    public MealProductId() {

    }

    public MealProductId(Long mealId, Long productId){
        this.mealId = mealId;
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        MealProductId that = (MealProductId) o;
        return Objects.equals(mealId, that.productId) && Objects.equals(productId, that.mealId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(mealId, productId);
    }
}

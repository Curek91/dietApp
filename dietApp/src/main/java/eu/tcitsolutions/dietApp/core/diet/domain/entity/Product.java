package eu.tcitsolutions.dietApp.core.diet.domain.entity;

import eu.tcitsolutions.dietApp.core.common.entity.BaseLogEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Product extends BaseLogEntity implements Serializable {

    private String name;
    private Long protein;
    private Long carbs;
    private Long fat;
    private Long kcal;
    private String imageName;

    @OneToOne
    private Type type;

    public Product(String name, Long protein, Long carbs, Long fat, Long kcal, Type type, String imageName) {
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.kcal = kcal;
        this.type = type;
        this.imageName = imageName;

    }

    public Product(Long id, String name, Long protein, Long carbs, Long fat, Long kcal, Type type, String imageName) {
        this.id = id;
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.kcal = kcal;
        this.type = type;
        this.imageName = imageName;
    }
}

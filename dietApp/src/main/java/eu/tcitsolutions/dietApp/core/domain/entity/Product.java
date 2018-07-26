package eu.tcitsolutions.dietApp.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseLogEntity implements Serializable {

    private String name;
    private Long protein;
    private Long carb;
    private Long fat;
    private Long kcal;

    @OneToOne
    private Type type;


}

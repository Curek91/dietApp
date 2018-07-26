package eu.tcitsolutions.dietApp.core.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class Type extends BaseLogEntity implements Serializable {

    @Column(nullable = false, unique = true)
    private String name;

    public Type(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Type(String name){
        this.name = name;
    }
}

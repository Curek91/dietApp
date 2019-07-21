package eu.tcitsolutions.dietApp.core.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseLogEntity extends BaseEntity{

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    public String createdBy;

    @LastModifiedBy
    @Column(name = "MODIFIED_BY")
    public String modifiedBy;

    @CreatedDate
    @Column(name = "CREATION_TIMESTAMP", updatable = false)
    public LocalDateTime creationTime;

    @LastModifiedDate
    @Column(name = "MODIFICATION_TIMESTAMP")
    public LocalDateTime modificationTime;

    @PrePersist
    public void prePersist(){
        this.creationTime = LocalDateTime.now();
        this.createdBy = "Curek";
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = LocalDateTime.now();
        this.modifiedBy = "Curek";
    }
}

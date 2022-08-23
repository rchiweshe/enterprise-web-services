package zw.co.econet.servicepromotions.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name ="promotions" , indexes = {
        @Index(name = "promotions_name_index", columnList = "name")
})
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime dateCreated;
    private LocalDateTime dateLastModified;
    @Column(name = "status", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private EntityStatus entityStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(LocalDateTime dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime enndDate) {
        this.endDate = enndDate;
    }

    @PrePersist
    public void setUp() {
        dateCreated = LocalDateTime.now();
        entityStatus = EntityStatus.ACTIVE;
    }

    @PreUpdate
    public void update() {
        dateLastModified = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Promotions{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description
                + '\'' + ", startDate=" + startDate + ", enndDate=" + endDate + ", dateCreated=" + dateCreated
                + ", dateLastModified=" + dateLastModified + ", entityStatus=" + entityStatus + '}';
    }
}

package PetShop.BarkingCat.base.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
abstract public class Base {

    private LocalDateTime createdDateTime;

    private LocalDateTime updatedDateTime;

    private LocalDateTime deletedDateTime;

    protected Base() {
        this.createdDateTime = LocalDateTime.now();
    }

    public void delete() {
        this.deletedDateTime = LocalDateTime.now();
    }

    @PreUpdate
    public void update() {
        this.updatedDateTime = LocalDateTime.now();
    }
}

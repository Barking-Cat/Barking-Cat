package PetShop.BarkingCat.board.model;

import PetShop.BarkingCat.base.model.Base;
import lombok.Builder;

import javax.persistence.*;

@Entity
public class Category extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    protected Category() {
    }

    @Builder
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

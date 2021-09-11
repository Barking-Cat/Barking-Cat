package PetShop.BarkingCat.board.model;

import PetShop.BarkingCat.base.model.Base;

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

    public Category(String name) {
        this.name = name;
    }
}

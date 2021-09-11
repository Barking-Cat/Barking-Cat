package PetShop.BarkingCat.Board.model;

import PetShop.BarkingCat.base.Base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category extends Base {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    protected Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}

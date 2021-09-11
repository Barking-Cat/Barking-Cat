package PetShop.BarkingCat.Board.model;

import PetShop.BarkingCat.base.Attachment;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Photos {

    @OneToMany
    private List<Attachment> pictures = new ArrayList<>();
}

package PetShop.BarkingCat.domain.board.model;

import PetShop.BarkingCat.common.base.converter.TagConverter;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Tags {
    @Convert(converter = TagConverter.class)
    private List<Tag> tags = new ArrayList<>();

    protected Tags() {
    }

    public Tags(String tagString) {
        this(Arrays.stream(tagString.split("\\|"))
                .map(String::trim)
                .map(Tag::new)
                .collect(Collectors.toList()));
    }

    public Tags(List<Tag> tags) {
        this.tags = tags;
    }
}

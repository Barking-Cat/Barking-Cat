package PetShop.BarkingCat.common.security.annotations;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authenticated {
}
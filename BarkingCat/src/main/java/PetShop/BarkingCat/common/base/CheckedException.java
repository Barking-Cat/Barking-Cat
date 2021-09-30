package PetShop.BarkingCat.common.base;

import java.util.concurrent.Callable;

public class CheckedException {
    public <T> T wrap(Callable<T> thingThatMightGoBoom) {
        try {
            return thingThatMightGoBoom.call();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

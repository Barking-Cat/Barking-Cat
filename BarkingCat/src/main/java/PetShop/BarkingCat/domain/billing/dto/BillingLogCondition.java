package PetShop.BarkingCat.domain.billing.dto;

import lombok.Data;

import java.time.Month;

@Data
public class BillingLogCondition {
    private int year;
    private Month month;
}

package com.courses.barbie;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountBalanceDiffDTO {
    private int accountId;
    private int balanceDifference;
}

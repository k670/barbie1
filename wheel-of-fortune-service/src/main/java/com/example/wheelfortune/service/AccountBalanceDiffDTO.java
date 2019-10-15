package com.example.wheelfortune.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountBalanceDiffDTO {
    private int accountId;
    private int balanceDifference;
}


package com.example.wheelfortune.web;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class WheelFortuneStatusModel {
    private long price;
    private long timeToWait;
    private boolean enoughMoney;
}

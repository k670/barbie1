package com.example.wheelfortune.repository.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class WheelFortuneModel {
    @Id
    private long userId;
    private long timeLastSpin;
    private List<String> clothes;
}

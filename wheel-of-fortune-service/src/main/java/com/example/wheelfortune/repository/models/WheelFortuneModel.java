package com.example.wheelfortune.repository.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "wheelfortunemodel")
@Data
public class WheelFortuneModel {
    @Id
    private long userId;
    private long timeLastSpin;
//    private List<String> clothes;
}

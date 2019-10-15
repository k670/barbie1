package com.example.wheelfortune.repository.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wheelfortunemodel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WheelFortuneModel {
    @Id
    private long userId;
    private long timeLastSpin;
//    private List<String> clothes;
}

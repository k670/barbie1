package com.example.wheelfortune.web;

import com.example.wheelfortune.service.BonusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/wheelfortune")
@Slf4j
public class WheelFortuneController {

    private BonusService bonusService;


    public WheelFortuneController(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    @GetMapping(path = "/spin/{id}")
    public ResponseEntity<UserWinModel> startGenerateBonus(@PathVariable int id) {
        return ResponseEntity.ok().body(UserWinModel.builder().clothesName(bonusService.generateWinClothes(id)).build());
    }

    @GetMapping(path = "/{id}")
    public WheelFortuneStatusModel getAccess(@PathVariable int id) {
        return bonusService.getPrice(id);
    }

}

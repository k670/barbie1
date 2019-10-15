package com.example.wheelfortune.web;

import com.example.wheelfortune.service.BonusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/wheelfortune", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class WheelFortuneController {

    private BonusService bonusService;


    public WheelFortuneController(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    @GetMapping(path = "/spin/{id}")
    public ResponseEntity<UserWinModel> startGenerateBonus(@PathVariable long id) {
        return ResponseEntity.ok().body(UserWinModel.builder().clothesName(bonusService.generateWinClothes(id)).build());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<WheelFortuneStatusModel> getAccess(@PathVariable long id) {
        return ResponseEntity.ok(bonusService.getPrice(id));
    }

}

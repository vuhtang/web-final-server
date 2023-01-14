package com.vuhtang.lesha.controller;

import com.vuhtang.lesha.model.Coordinates;
import com.vuhtang.lesha.model.Shot;
import com.vuhtang.lesha.service.impl.ShotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/shots")
@CrossOrigin
public class ShotController {

    private final ShotServiceImpl shotServiceImpl;

    @Autowired
    public ShotController(ShotServiceImpl shotServiceImpl) {
        this.shotServiceImpl = shotServiceImpl;
    }

    @GetMapping
    public List<Shot> getShots() {
        return shotServiceImpl.getAllShots();
    }

    @PostMapping
    public void addNewShot(@RequestBody Coordinates coordinates) {
        shotServiceImpl.addShot(coordinates);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Shot> getShotsPage(@PathVariable int offset, @PathVariable int pageSize) {
        return shotServiceImpl.getShotsWithPagination(offset, pageSize);
    }

    @DeleteMapping
    public void deleteShots() {
        shotServiceImpl.deleteAllShots();
    }
}

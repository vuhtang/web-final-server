package com.vuhtang.lesha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/shots")
@CrossOrigin
public class ShotController {

    private final ShotService shotService;

    @Autowired
    public ShotController(ShotService shotService) {
        this.shotService = shotService;
    }

    @GetMapping
    public List<Shot> getShots() {
        return shotService.getShots();
    }

    @PostMapping
    public void addNewShot(@RequestBody Coordinates coordinates) {
        shotService.registerNewShot(coordinates);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Shot> getShotsPage(@PathVariable int offset, @PathVariable int pageSize) {
        return shotService.getShotsWithPagination(offset, pageSize);
    }

    @DeleteMapping
    public void deleteShots() {
        shotService.deleteAllShots();
    }
}

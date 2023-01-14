package com.vuhtang.lesha.service;

import com.vuhtang.lesha.model.Coordinates;
import com.vuhtang.lesha.model.Shot;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ShotService {

    List<Shot> getAllShots();
    Page<Shot> getShotsWithPagination(int offset, int page);
    void addShot(Coordinates coordinates);
    void deleteAllShots();
}

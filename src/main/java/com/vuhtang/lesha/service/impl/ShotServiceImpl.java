package com.vuhtang.lesha.service.impl;

import com.vuhtang.lesha.repository.ShotRepository;
import com.vuhtang.lesha.model.Coordinates;
import com.vuhtang.lesha.model.Shot;
import com.vuhtang.lesha.service.ShotService;
import com.vuhtang.lesha.util.ShotChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShotServiceImpl implements ShotService {

    private final ShotRepository shotRepository;
    private final ShotChecker shotChecker;

    @Autowired
    public ShotServiceImpl(ShotRepository shotRepository, ShotChecker shotChecker) {
        this.shotChecker = shotChecker;
        this.shotRepository = shotRepository;
    }

    public List<Shot> getAllShots() {
        return shotRepository.findAll();
    }

    public Page<Shot> getShotsWithPagination(int offset, int page) {
        return shotRepository.findAll(PageRequest.of(offset, page));
    }

    public void addShot(Coordinates coordinates) throws IllegalStateException {
        Shot shot = shotChecker.checkShot(coordinates);
        shotRepository.save(shot);
    }

    public void deleteAllShots() {
        shotRepository.deleteAll();
    }
}

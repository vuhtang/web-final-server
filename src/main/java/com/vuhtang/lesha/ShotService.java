package com.vuhtang.lesha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShotService {

    private final ShotRepository shotRepository;

    @Autowired
    public ShotService(ShotRepository shotRepository) {
        this.shotRepository = shotRepository;
    }

    public List<Shot> getShots() {
        return shotRepository.findAll();
    }

    public Page<Shot> getShotsWithPagination(int offset, int page) {
        return shotRepository.findAll(PageRequest.of(offset, page));
    }

    public void registerNewShot(Coordinates coordinates) {
        long start = System.nanoTime();
        Double x = coordinates.getX();
        Double y = coordinates.getY();
        Double r = coordinates.getR();
        if (x == null || y == null || r == null) {
            throw new IllegalStateException("Incorrect coordinates passed");
        }
        String result = check(x, y, r) ? "HIT" : "MISS";
        Long execTime = System.nanoTime() - start;
        LocalDate currTime = LocalDate.now();
        Shot shot = new Shot(x, y, r, result, currTime, execTime);
        shotRepository.save(shot);
    }

    private boolean check(Double x, Double y, Double r) {
        return (x >= 0 && y >= 0 && x <= r / 2 && y <= r)
                || (x <= 0 && y <= 0 && x <= r && y <= r && y >= -x - r)
                || (x >= 0 && y <= 0 && x * x + y * y <= r * r);
    }

    public void deleteAllShots() {
        shotRepository.deleteAll();
    }
}

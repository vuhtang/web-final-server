package com.vuhtang.lesha.util;

import com.vuhtang.lesha.model.Coordinates;
import com.vuhtang.lesha.model.Shot;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ShotChecker {
    public Shot checkShot(Coordinates coordinates) throws IllegalStateException {
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
        return new Shot(x, y, r, result, currTime, execTime);
    }

    private boolean check(Double x, Double y, Double r) {
        return (x >= 0 && y >= 0 && x <= r / 2 && y <= r)
                || (x <= 0 && y <= 0 && x <= r && y <= r && y >= -x - r)
                || (x >= 0 && y <= 0 && x * x + y * y <= r * r);
    }
}

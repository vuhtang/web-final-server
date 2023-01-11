package com.vuhtang.lesha;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shots")
public class Shot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double x;
    private Double y;
    private Double r;
    private String result;
    private LocalDate currDate;
    private Long execTime;

    public Shot(Double x, Double y, Double r, String result, LocalDate currDate, Long execTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.result = result;
        this.currDate = currDate;
        this.execTime = execTime;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

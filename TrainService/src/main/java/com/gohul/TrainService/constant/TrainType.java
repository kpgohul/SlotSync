package com.gohul.TrainService.constant;

import lombok.Getter;

@Getter
public enum TrainType {

    EXPRESS(50),        // km/h
    LOCAL(30),
    SUPER_FAST(70),
    INTERCITY(60);

    private final int averageSpeed;

    TrainType(int averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

}

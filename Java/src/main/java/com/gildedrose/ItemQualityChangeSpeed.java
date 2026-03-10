package com.gildedrose;

public enum ItemQualityChangeSpeed {

    REGULAR(1),
    HASTY(2);

    final int speed;

    ItemQualityChangeSpeed(int speed) {
        this.speed = speed;
    }
}

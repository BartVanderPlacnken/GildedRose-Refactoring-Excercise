package com.gildedrose.strategies;

import com.gildedrose.Item;

public class ConjuredItemQualityStrategy implements ItemQualityStrategy {
    @Override
    public void updateQuality(Item item) {
        item.quality = Math.max(0, item.quality - ItemQualityChangeSpeed.HASTY.speed);
    }
}

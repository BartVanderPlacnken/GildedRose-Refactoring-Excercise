package com.gildedrose.strategies;

import com.gildedrose.Item;

public class BackStagePassQualityStrategy implements ItemQualityStrategy {

    @Override
    public void updateQuality(Item item) {
        if(item.sellIn <= 0) {
            item.quality = MINIMUM_QUALITY;
        } else {
            ItemQualityChangeSpeed itemQualityChangeSpeed = ItemQualityChangeSpeed.REGULAR;
            if (item.sellIn <= 5) {
                itemQualityChangeSpeed = ItemQualityChangeSpeed.URGENT;
            } else if (item.sellIn <= 10) {
                itemQualityChangeSpeed = ItemQualityChangeSpeed.HASTY;
            }
            item.quality = Math.min(MAXIMUM_QUALITY, item.quality + itemQualityChangeSpeed.speed);
        }
    }
}

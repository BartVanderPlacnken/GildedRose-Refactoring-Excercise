package com.gildedrose.strategies.qualityStrategies;

import com.gildedrose.ItemTypeDecorator;

public abstract class ItemQualityStrategy {

    int EXPIRATION_DATE = 0;

    int MAXIMUM_QUALITY = 50;
    int MINIMUM_QUALITY = 0;

    public void initializeItemInBounds(ItemTypeDecorator item) {
        item.setQuality(Math.max(MINIMUM_QUALITY, Math.min(MAXIMUM_QUALITY, item.getQuality())));
    }

    public abstract void updateQuality(ItemTypeDecorator item);

    boolean itemHasExpired(ItemTypeDecorator item) {
        return item.getSellIn() <= EXPIRATION_DATE;
    }
}

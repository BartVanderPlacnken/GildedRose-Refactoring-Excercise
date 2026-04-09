package com.gildedrose.strategies.qualityStrategies;

import com.gildedrose.ItemTypeDecorator;

public class AgedBrieQualityStrategy extends ItemQualityStrategy{

    private static final int REGULAR_SPEED_APPRECIATION = 1;
    private static final int HASTY_SPEED_APPRECIATION = 2;

    @Override
    public void updateQuality(ItemTypeDecorator item) {
        int qualityModifier = itemHasExpired(item)
            ? HASTY_SPEED_APPRECIATION
            : REGULAR_SPEED_APPRECIATION;
        item.setQuality(Math.min(MAXIMUM_QUALITY, item.getQuality() + qualityModifier));
    }

}

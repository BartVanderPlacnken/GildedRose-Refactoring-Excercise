package com.gildedrose.strategies.qualityStrategies;

import com.gildedrose.ItemTypeDecorator;

public class NormalItemQualityStrategy extends ItemQualityStrategy{

    private static final int REGULAR_QUALITY_DEGRADATION = 1;
    private static final int HASTY_QUALITY_DEGRADATION = 2;

    @Override
    public void updateQuality(ItemTypeDecorator item) {
        int degradationFactor = item.hasExpired()
            ? HASTY_QUALITY_DEGRADATION
            : REGULAR_QUALITY_DEGRADATION;
        item.setQuality(Math.max(MINIMUM_QUALITY, item.getQuality() - degradationFactor));
    }
}

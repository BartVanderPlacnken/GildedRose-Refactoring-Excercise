package com.gildedrose.strategies.qualityStrategies;

import com.gildedrose.ItemTypeDecorator;

public class ConjuredItemQualityStrategy extends ItemQualityStrategy {

    private static final int DEGRADATION_FACTOR = 2;

    @Override
    public void updateQuality(ItemTypeDecorator item) {
        item.setQuality(Math.max(0, item.getQuality() - DEGRADATION_FACTOR));
    }
}

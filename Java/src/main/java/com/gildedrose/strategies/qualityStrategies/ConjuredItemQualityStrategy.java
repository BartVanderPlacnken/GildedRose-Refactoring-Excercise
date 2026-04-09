package com.gildedrose.strategies.qualityStrategies;

import com.gildedrose.ItemTypeDecorator;

public class ConjuredItemQualityStrategy extends ItemQualityStrategy {

    @Override
    public void updateQuality(ItemTypeDecorator item) {
        item.setQuality(Math.max(0, item.getQuality() - ItemQualityChangeSpeed.HASTY.speed));
    }
}

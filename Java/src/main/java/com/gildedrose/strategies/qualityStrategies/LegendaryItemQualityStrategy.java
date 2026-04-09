package com.gildedrose.strategies.qualityStrategies;

import com.gildedrose.ItemTypeDecorator;

public class LegendaryItemQualityStrategy extends ItemQualityStrategy{

    public static final int LEGENDARY_QUALITY = 80;

    @Override
    public void initializeItemInBounds(ItemTypeDecorator item) {
        item.setQuality(LEGENDARY_QUALITY);
    }

    @Override
    public void updateQuality(ItemTypeDecorator item) {
        //no-op, quality for legendary items never change
    }
}

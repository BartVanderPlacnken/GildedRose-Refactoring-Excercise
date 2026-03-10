package com.gildedrose.strategies;

import com.gildedrose.Item;

public class LegendaryItemQualityStrategy implements ItemQualityStrategy{

    public static final int LEGENDARY_QUALITY = 80;

    @Override
    public void initializeItemInBounds(Item item) {
        item.quality = LEGENDARY_QUALITY;
    }

    @Override
    public void updateQuality(Item item) {
        //no-op, quality for legendary items never change
    }

    @Override
    public void updateSellIn(Item item) {
        //no-op, sell in date for legendary items never changes
    }
}

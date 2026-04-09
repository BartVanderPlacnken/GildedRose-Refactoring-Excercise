package com.gildedrose.strategies.sellInStrategies;

import com.gildedrose.ItemTypeDecorator;

public class NormalItemSellInStrategy extends ItemSellInStrategy{

    private static final int DEGRADATION_RATE = 1;

    @Override
    public void updateSellIn(ItemTypeDecorator item) {
        item.setSellIn(item.getSellIn() - DEGRADATION_RATE);
    }
}

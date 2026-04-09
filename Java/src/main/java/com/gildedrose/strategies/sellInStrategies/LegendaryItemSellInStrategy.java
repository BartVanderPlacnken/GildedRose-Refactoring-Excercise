package com.gildedrose.strategies.sellInStrategies;

import com.gildedrose.ItemTypeDecorator;

public class LegendaryItemSellInStrategy extends ItemSellInStrategy{

    @Override
    public void updateSellIn(ItemTypeDecorator item) {
        //no-op, sell in date for legendary items never changes
    }
}

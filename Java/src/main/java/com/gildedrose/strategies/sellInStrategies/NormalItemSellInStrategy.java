package com.gildedrose.strategies.sellInStrategies;

import com.gildedrose.ItemTypeDecorator;

public class NormalItemSellInStrategy extends ItemSellInStrategy{

    @Override
    public void updateSellIn(ItemTypeDecorator item) {
        item.setSellIn(item.getSellIn() - ItemSellInChangeSpeed.REGULAR.speed);
    }
}

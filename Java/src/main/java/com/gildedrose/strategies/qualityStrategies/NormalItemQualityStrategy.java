package com.gildedrose.strategies.qualityStrategies;

import com.gildedrose.ItemTypeDecorator;

public class NormalItemQualityStrategy extends ItemQualityStrategy{

    @Override
    public void updateQuality(ItemTypeDecorator item) {
        ItemQualityChangeSpeed qualityModifier = itemHasExpired(item) ? ItemQualityChangeSpeed.HASTY : ItemQualityChangeSpeed.REGULAR;
        item.setQuality(Math.max(MINIMUM_QUALITY, item.getQuality() - qualityModifier.speed));
    }
}

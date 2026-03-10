package com.gildedrose;

public class AgedBrieQualityStrategy implements ItemQualityStrategy{

    public static final int QUALITY_MAXIMUM = 50;

    @Override
    public void updateQuality(Item item) {
        ItemQualityChangeSpeed qualityModifier = itemHasExpired(item) ? ItemQualityChangeSpeed.HASTY : ItemQualityChangeSpeed.REGULAR;
        item.quality = Math.min(QUALITY_MAXIMUM, item.quality + qualityModifier.speed);
    }

    @Override
    public void updateSellIn(Item item) {
        item.sellIn = item.sellIn - ItemSellInChangeSpeed.REGULAR.speed;
    }
}

package com.gildedrose;

public class AgedBrieQualityStrategy implements ItemQualityStrategy{

    @Override
    public void updateQuality(Item item) {
        ItemQualityChangeSpeed qualityModifier = itemHasExpired(item) ? ItemQualityChangeSpeed.HASTY : ItemQualityChangeSpeed.REGULAR;
        item.quality = Math.min(MAXIMUM_QUALITY, item.quality + qualityModifier.speed);
    }

}

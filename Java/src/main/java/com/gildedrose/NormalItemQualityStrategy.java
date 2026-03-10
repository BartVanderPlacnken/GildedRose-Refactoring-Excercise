package com.gildedrose;

public class NormalItemQualityStrategy implements ItemQualityStrategy{

    @Override
    public void updateQuality(Item item) {
        ItemQualityChangeSpeed qualityModifier = itemHasExpired(item) ? ItemQualityChangeSpeed.HASTY : ItemQualityChangeSpeed.REGULAR;
        item.quality = Math.max(MINIMUM_QUALITY, item.quality - qualityModifier.speed);
    }
}

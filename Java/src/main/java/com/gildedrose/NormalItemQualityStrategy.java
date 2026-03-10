package com.gildedrose;

public class NormalItemQualityStrategy implements ItemQualityStrategy{

    @Override
    public void updateQuality(Item item) {
        ItemQualityChangeSpeed qualityModifier = itemHasExpired(item) ? ItemQualityChangeSpeed.HASTY : ItemQualityChangeSpeed.REGULAR;
        item.quality = Math.max(0, item.quality - qualityModifier.speed);
    }

    private boolean itemHasExpired(Item item) {
        return item.sellIn <= 0;
    }

    @Override
    public void updateSellIn(Item item) {
        item.sellIn = item.sellIn - ItemSellInChangeSpeed.REGULAR.speed;
    }
}

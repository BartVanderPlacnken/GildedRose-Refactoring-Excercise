package com.gildedrose.strategies;

import com.gildedrose.Item;

public interface ItemQualityStrategy {

    int EXPIRATION_DATE = 0;

    int MAXIMUM_QUALITY = 50;
    int MINIMUM_QUALITY = 0;

    default void setQualityIfBoundsAreExceeded(Item item) {
        item.quality = Math.max(MINIMUM_QUALITY, Math.min(MAXIMUM_QUALITY, item.quality));
    }

    default void updateItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
    }

    void updateQuality(Item item);

    default boolean itemHasExpired(Item item) {
        return item.sellIn <= EXPIRATION_DATE;
    }

    default void updateSellIn(Item item) {
        item.sellIn = item.sellIn - ItemSellInChangeSpeed.REGULAR.speed;
    }
}

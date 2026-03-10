package com.gildedrose;

public interface ItemQualityStrategy {

    int EXPIRATION_DATE = 0;

    int MAXIMUM_QUALITY = 50;
    int MINIMUM_QUALITY = 0;

    default void updateItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
    }

    default boolean itemHasExpired(Item item) {
        return item.sellIn <= EXPIRATION_DATE;
    }

    void updateQuality(Item item);

    default void updateSellIn(Item item) {
        item.sellIn = item.sellIn - ItemSellInChangeSpeed.REGULAR.speed;
    }
}

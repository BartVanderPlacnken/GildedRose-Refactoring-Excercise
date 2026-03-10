package com.gildedrose;

public interface ItemQualityStrategy {

    int EXPIRATION_DATE = 0;

    default void updateItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
    }

    default boolean itemHasExpired(Item item) {
        return item.sellIn <= EXPIRATION_DATE;
    }

    void updateQuality(Item item);

    void updateSellIn(Item item);
}

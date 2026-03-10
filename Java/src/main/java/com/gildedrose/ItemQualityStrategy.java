package com.gildedrose;

public interface ItemQualityStrategy {

    default void updateItem(Item item) {
        updateQuality(item);
        updateSellIn(item);
    }

    void updateQuality(Item item);

    void updateSellIn(Item item);
}

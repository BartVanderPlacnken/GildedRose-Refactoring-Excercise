package com.gildedrose.strategies;

import com.gildedrose.Item;

public class BackStagePassQualityStrategy implements ItemQualityStrategy {

    public static final int HASTILY_EXPIRATION_THRESHOLD = 10;
    public static final int URGENTLY_EXPIRATION_THRESHOLD = 5;

    @Override
    public void updateQuality(Item item) {
        if(itemHasExpired(item)) {
            item.quality = MINIMUM_QUALITY;
        } else {
            ItemQualityChangeSpeed itemQualityChangeSpeed = ItemQualityChangeSpeed.REGULAR;
            if (itemWillExpireUrgently(item)) {
                itemQualityChangeSpeed = ItemQualityChangeSpeed.URGENT;
            } else if (itemWillExpireHastily(item)) {
                itemQualityChangeSpeed = ItemQualityChangeSpeed.HASTY;
            }
            item.quality = Math.min(MAXIMUM_QUALITY, item.quality + itemQualityChangeSpeed.speed);
        }
    }

    private static boolean itemWillExpireHastily(Item item) {
        return item.sellIn <= HASTILY_EXPIRATION_THRESHOLD;
    }

    private static boolean itemWillExpireUrgently(Item item) {
        return item.sellIn <= URGENTLY_EXPIRATION_THRESHOLD;
    }
}

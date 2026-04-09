package com.gildedrose.strategies.qualityStrategies;

import com.gildedrose.ItemTypeDecorator;

public class BackStagePassQualityStrategy extends ItemQualityStrategy {

    private static final int HASTILY_EXPIRATION_THRESHOLD = 10;
    private static final int URGENTLY_EXPIRATION_THRESHOLD = 5;

    @Override
    public void updateQuality(ItemTypeDecorator item) {
        if(itemHasExpired(item)) {
            item.setQuality(MINIMUM_QUALITY);
        } else {
            ItemQualityChangeSpeed itemQualityChangeSpeed = determineItemQualityChangeSpeed(item);
            item.setQuality(Math.min(MAXIMUM_QUALITY, item.getQuality() + itemQualityChangeSpeed.speed));
        }
    }

    private static ItemQualityChangeSpeed determineItemQualityChangeSpeed(ItemTypeDecorator item) {
        ItemQualityChangeSpeed itemQualityChangeSpeed = ItemQualityChangeSpeed.REGULAR;
        if (itemWillExpireUrgently(item)) {
            itemQualityChangeSpeed = ItemQualityChangeSpeed.URGENT;
        } else if (itemWillExpireHastily(item)) {
            itemQualityChangeSpeed = ItemQualityChangeSpeed.HASTY;
        }
        return itemQualityChangeSpeed;
    }

    private static boolean itemWillExpireHastily(ItemTypeDecorator item) {
        return item.getSellIn() <= HASTILY_EXPIRATION_THRESHOLD;
    }

    private static boolean itemWillExpireUrgently(ItemTypeDecorator item) {
        return item.getSellIn() <= URGENTLY_EXPIRATION_THRESHOLD;
    }
}

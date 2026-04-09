package com.gildedrose.strategies.qualityStrategies;

import com.gildedrose.ItemTypeDecorator;

public class BackStagePassQualityStrategy extends ItemQualityStrategy {

    private static final int HASTILY_EXPIRATION_THRESHOLD = 10;
    private static final int URGENTLY_EXPIRATION_THRESHOLD = 5;
    private static final int REGULAR_APPRECIATION = 1;
    private static final int HASTILY_APPRECIATION = 2;
    private static final int URGENTLY_APPRECIATION = 3;


    @Override
    public void updateQuality(ItemTypeDecorator item) {
        if(item.hasExpired()) {
            item.setQuality(MINIMUM_QUALITY);
        } else {
            int itemQualityAppreciationFactor = itemQualityAppreciationFactor(item);
            item.setQuality(Math.min(MAXIMUM_QUALITY, item.getQuality() + itemQualityAppreciationFactor));
        }
    }

    private static int itemQualityAppreciationFactor(ItemTypeDecorator item) {
        int appreciationFactor = REGULAR_APPRECIATION;
        if (itemWillExpireUrgently(item)) {
            appreciationFactor = URGENTLY_APPRECIATION;
        } else if (itemWillExpireHastily(item)) {
            appreciationFactor = HASTILY_APPRECIATION;
        }
        return appreciationFactor;
    }

    private static boolean itemWillExpireHastily(ItemTypeDecorator item) {
        return item.getSellIn() <= HASTILY_EXPIRATION_THRESHOLD;
    }

    private static boolean itemWillExpireUrgently(ItemTypeDecorator item) {
        return item.getSellIn() <= URGENTLY_EXPIRATION_THRESHOLD;
    }
}

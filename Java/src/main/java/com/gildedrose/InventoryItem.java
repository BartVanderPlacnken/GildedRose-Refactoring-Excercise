package com.gildedrose;

import com.gildedrose.strategies.*;

public class InventoryItem {

    private final Item item;

    private ItemQualityStrategy itemQualityStrategy;

    public InventoryItem(Item item) {
        this.item = item;
        determineAndSetItemQualityStrategy();
        itemQualityStrategy.initializeItemInBounds(item);
    }

    private void determineAndSetItemQualityStrategy() {
        if(isLegendaryItem()) {
            itemQualityStrategy = new LegendaryItemQualityStrategy();
        } else if (isConjuredItem()) {
            itemQualityStrategy = new ConjuredItemQualityStrategy();
        } else if (isBackStagePass()) {
            itemQualityStrategy = new BackStagePassQualityStrategy();
        } else if (isAgedBrie()) {
            itemQualityStrategy = new AgedBrieQualityStrategy();
        } else {
            itemQualityStrategy =  new NormalItemQualityStrategy();
        }
    }

    public void updateQuality() {
        itemQualityStrategy.updateItem(item);
    }

    private boolean isConjuredItem() {
        return item.name.toLowerCase().startsWith("conjured");
    }

    private boolean isBackStagePass() {
        return item.name.toLowerCase().startsWith("backstage");
    }

    private boolean isLegendaryItem() {
        return item.name.toLowerCase().startsWith("sulfuras");
    }

    private boolean isAgedBrie() {
        return item.name.equals("Aged Brie");
    }
}

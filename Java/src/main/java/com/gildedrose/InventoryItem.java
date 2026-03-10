package com.gildedrose;

import com.gildedrose.strategies.*;

public class InventoryItem {

    private final Item item;

    final ItemQualityStrategy normalItemQualityStrategy = new NormalItemQualityStrategy();
    final ItemQualityStrategy agedBrieQualityStrategy = new AgedBrieQualityStrategy();
    final ItemQualityStrategy legendaryQualityStrategy = new LegendaryItemQualityStrategy();
    final ItemQualityStrategy backStagePassQualityStrategy = new BackStagePassQualityStrategy();
    final ItemQualityStrategy conjuredItemQualityStrategy = new ConjuredItemQualityStrategy();

    public InventoryItem(Item item) {
        this.item = item;
    }

    public void updateQuality() {
        if(isBackStagePass()) {
            backStagePassQualityStrategy.setQualityIfBoundsAreExceeded(item);
            backStagePassQualityStrategy.updateItem(item);
        } else if(isAgedBrie()) {
            agedBrieQualityStrategy.setQualityIfBoundsAreExceeded(item);
            agedBrieQualityStrategy.updateItem(item);
        } else if(isLegendaryItem()) {
            legendaryQualityStrategy.setQualityIfBoundsAreExceeded(item);
            legendaryQualityStrategy.updateItem(item);
        } else if(isConjuredItem()) {
            conjuredItemQualityStrategy.setQualityIfBoundsAreExceeded(item);
            conjuredItemQualityStrategy.updateItem(item);
        } else {
            normalItemQualityStrategy.setQualityIfBoundsAreExceeded(item);
            normalItemQualityStrategy.updateItem(item);
        }
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

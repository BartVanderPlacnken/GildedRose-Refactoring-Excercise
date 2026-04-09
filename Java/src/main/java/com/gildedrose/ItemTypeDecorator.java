package com.gildedrose;

import com.gildedrose.strategies.qualityStrategies.*;
import com.gildedrose.strategies.sellInStrategies.ItemSellInStrategy;
import com.gildedrose.strategies.sellInStrategies.LegendaryItemSellInStrategy;
import com.gildedrose.strategies.sellInStrategies.NormalItemSellInStrategy;

public class ItemTypeDecorator {

    private final Item item;
    private final ItemQualityStrategy itemQualityStrategy;
    private final ItemSellInStrategy itemSellInStrategy;

    public ItemTypeDecorator(Item item) {
        this.item = item;
        this.itemQualityStrategy = determineItemQualityStrategy();
        this.itemSellInStrategy = determineItemSellInStrategy();
        itemQualityStrategy.initializeItemInBounds(this);
    }

    public int getSellIn() {
        return item.sellIn;
    }

    public void setSellIn(int sellIn) {
        item.sellIn = sellIn;
    }

    public int getQuality() {
        return item.quality;
    }

    public void setQuality(int quality) {
        item.quality = quality;
    }

    private ItemQualityStrategy determineItemQualityStrategy() {
        if(isLegendaryItem()) {
            return new LegendaryItemQualityStrategy();
        } else if (isConjuredItem()) {
            return new ConjuredItemQualityStrategy();
        } else if (isBackStagePass()) {
            return new BackStagePassQualityStrategy();
        } else if (isAgedBrie()) {
            return new AgedBrieQualityStrategy();
        } else {
            return new NormalItemQualityStrategy();
        }
    }

    private ItemSellInStrategy determineItemSellInStrategy() {
        if(isLegendaryItem())  {
            return new LegendaryItemSellInStrategy();
        } else {
            return new NormalItemSellInStrategy();
        }
    }

    public void updateItem() {
        itemQualityStrategy.updateQuality(this);
        itemSellInStrategy.updateSellIn(this);
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

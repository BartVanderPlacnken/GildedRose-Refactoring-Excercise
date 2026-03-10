package com.gildedrose;

public class InventoryItem {

    private final Item item;

    final ItemQualityStrategy normalItemQualityStrategy = new NormalItemQualityStrategy();
    final ItemQualityStrategy agedBrieQualityStrategy = new AgedBrieQualityStrategy();
    final ItemQualityStrategy legendaryQualityStrategy = new LegendaryItemQualityStrategy();

    public InventoryItem(Item item) {
        this.item = item;
    }

    public void updateQuality() {
        if(isNormalItem()) {
            normalItemQualityStrategy.updateItem(item);
        } else if(isAgedBrie()) {
            agedBrieQualityStrategy.updateItem(item);
        } else if(isLegendaryItem()) {
            legendaryQualityStrategy.updateItem(item);
        }

        if (isBackStagePass()) {
            if (qualityCanIncrease()) {
                increaseQualityAtIndexByOne();

                if (item.sellIn < 11) {
                    if (qualityCanIncrease()) {
                        increaseQualityAtIndexByOne();
                    }
                }

                if (item.sellIn < 6) {
                    if (qualityCanIncrease()) {
                        increaseQualityAtIndexByOne();
                    }
                }
            }
            decreaseSellInAtIndexByOne();
            if(item.sellIn < 0) {
                item.quality = 0;
            }
        }
    }

    private boolean qualityCanIncrease() {
        return item.quality < 50;
    }

    private boolean isBackStagePass() {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private void decreaseSellInAtIndexByOne() {
        item.sellIn = item.sellIn - 1;
    }

    private void increaseQualityAtIndexByOne() {
        item.quality = item.quality + 1;
    }

    private boolean isLegendaryItem() {
        return item.name.toLowerCase().startsWith("sulfuras");
    }

    private boolean isAgedBrie() {
        return item.name.equals("Aged Brie");
    }

    private boolean isNormalItem() {
        return !isLegendaryItem() && !isAgedBrie() && !isBackStagePass();
    }
}

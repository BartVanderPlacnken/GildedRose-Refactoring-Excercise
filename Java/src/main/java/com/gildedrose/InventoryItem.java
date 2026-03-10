package com.gildedrose;

public class InventoryItem {

    private final Item item;

    final ItemQualityStrategy normalItemQualityStrategy = new NormalItemQualityStrategy();

    public InventoryItem(Item item) {
        this.item = item;
    }

    public void updateQuality() {
        if(isNormalItem()) {
            normalItemQualityStrategy.updateItem(item);
        }

        if (isIncreasingQualityItem()) {
            if (qualityCanIncrease()) {
                increaseQualityAtIndexByOne();

                if (isBackStagePass()) {
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
            }
        }

        if (!isLegendaryItem() && !isNormalItem()) {
            decreaseSellInAtIndexByOne();
        }

        if (item.sellIn < 0) {
            if (!isAgedBrie()) {
                if (isBackStagePass()) {
                    item.quality = 0;
                }
            } else {
                if (qualityCanIncrease()) {
                    increaseQualityAtIndexByOne();
                }
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
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isIncreasingQualityItem() {
        return isAgedBrie() || isBackStagePass();
    }

    private boolean isAgedBrie() {
        return item.name.equals("Aged Brie");
    }

    private boolean isNormalItem() {
        return !isLegendaryItem() && !isAgedBrie() && !isBackStagePass();
    }
}

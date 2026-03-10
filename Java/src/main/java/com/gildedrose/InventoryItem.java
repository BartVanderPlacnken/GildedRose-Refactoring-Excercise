package com.gildedrose;

public class InventoryItem {

    private final Item item;

    public InventoryItem(Item item) {
        this.item = item;
    }

    public void updateQuality() {
        if (isNotIncreasingQualityItem()) {
            if (item.quality > 0) {
                if (!isLegendaryItem()) {
                    decreaseQualityAtIndexByOne();
                }
            }
        } else {
            if (item.quality < 50) {
                increaseQualityAtIndexByOne();

                if (isBackStagePass()) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            increaseQualityAtIndexByOne();
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            increaseQualityAtIndexByOne();
                        }
                    }
                }
            }
        }

        if (!isLegendaryItem()) {
            decreaseSellInAtIndexByOne();
        }

        if (item.sellIn < 0) {
            if (!isAgedBrie()) {
                if (!isBackStagePass()) {
                    if (item.quality > 0) {
                        if (!isLegendaryItem()) {
                            decreaseQualityAtIndexByOne();
                        }
                    }
                } else {
                    item.quality = 0;
                }
            } else {
                if (item.quality < 50) {
                    increaseQualityAtIndexByOne();
                }
            }
        }
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

    private void decreaseQualityAtIndexByOne() {
        item.quality = item.quality - 1;
    }

    private boolean isLegendaryItem() {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isNotIncreasingQualityItem() {
        return !isAgedBrie()
            && !isBackStagePass();
    }

    private boolean isAgedBrie() {
        return item.name.equals("Aged Brie");
    }
}

package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (isNotIncreasingQualityItem(item)) {
                if (item.quality > 0) {
                    if (!isLegendaryItem(item)) {
                        decreaseQualityAtIndexByOne(item);
                    }
                }
            } else {
                if (item.quality < 50) {
                    increaseQualityAtIndexByOne(item);

                    if (isBackStagePass(item)) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                increaseQualityAtIndexByOne(item);
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                increaseQualityAtIndexByOne(item);
                            }
                        }
                    }
                }
            }

            if (!isLegendaryItem(item)) {
                decreaseSellInAtIndexByOne(item);
            }

            if (item.sellIn < 0) {
                if (!isAgedBrie(item)) {
                    if (!isBackStagePass(item)) {
                        if (item.quality > 0) {
                            if (!isLegendaryItem(item)) {
                                decreaseQualityAtIndexByOne(item);
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        increaseQualityAtIndexByOne(item);
                    }
                }
            }
        }
    }

    private boolean isBackStagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private void decreaseSellInAtIndexByOne(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void increaseQualityAtIndexByOne(Item item) {
        item.quality = item.quality + 1;
    }

    private void decreaseQualityAtIndexByOne(Item item) {
        item.quality = item.quality - 1;
    }

    private boolean isLegendaryItem(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isNotIncreasingQualityItem(Item item) {
        return !isAgedBrie(item)
            && !isBackStagePass(item);
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals("Aged Brie");
    }
}

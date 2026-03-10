package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void passDayInTheStore() {
        Arrays.stream(items)
            .map(InventoryItem::new)
            .forEach(InventoryItem::updateQuality);

    }

}

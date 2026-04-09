package com.gildedrose;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GildedRose {
    Item[] items;
    List<ItemTypeDecorator> itemTypeDecorators;

    public GildedRose(Item[] items) {
        this.items = items;
        this.itemTypeDecorators = Arrays.stream(items)
            .map(ItemTypeDecorator::new)
            .collect(Collectors.toList());
    }

    public void passDayInTheStore() {
        itemTypeDecorators.forEach(ItemTypeDecorator::updateItem);
    }

}

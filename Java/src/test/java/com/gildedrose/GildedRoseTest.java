package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    GildedRose store;

    @Test
    void randomItemSellinAndQualityZero() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals("foo", app.items[0].name);
        assertEquals(0, app.items[0].quality, "Item quality is never negative");
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void randomItemsDecreaseInQuality() {
        int initialQuality = 10;
        int initialSellIn = 10;
        Item randomItem = new Item("random-item", initialSellIn, initialQuality);
        Item[] items = new Item[] {randomItem};
        initializeStore(items);

        daysPass(1);

        assertEquals(initialQuality - 1, randomItem.quality, "The quality has decreased with 1");
        assertEquals(initialSellIn - 1, randomItem.sellIn, "The sell in time has decreased with 1");
    }

    @Test
    void itemQualityIsNeverNegative() {
        int initialQuality = 1;
        int initialSellIn = 10;
        Item randomItem = new Item("random-item", initialSellIn, initialQuality);
        Item[] items = new Item[]{randomItem};
        initializeStore(items);

        daysPass(initialQuality + 1);

        assertEquals(0, randomItem.quality, "The quality has decreased to zero and not below");
        assertEquals(initialSellIn - initialQuality - 1, randomItem.sellIn, "More days than the quality has passed");
    }

    @Test
    void qualityDegradesFasterAfterSellByDate() {
        int initialQuality = 60;
        int initialSellIn = 2;
        Item randomItem = new Item("random-item", initialSellIn, initialQuality);
        Item[] items = new Item[]{randomItem};
        initializeStore(items);

        daysPass(initialSellIn);

        assertEquals(initialQuality - initialSellIn, randomItem.quality, "Quality is decreasing normally");
        assertEquals(0, randomItem.sellIn, "Two days have passed, sell by date reached");

        daysPass(1);

        assertEquals(initialQuality - initialSellIn - 2, randomItem.quality, "quality has started to degrade faster");
        assertEquals(-1, randomItem.sellIn, "The sell by date has passed");
    }

    @Test
    void qualityDegradesFasterAfterSellByDateButIsCloseToZero() {
        int initialQuality = 3;
        int initialSellIn = 2;
        Item randomItem = new Item("random-item", initialSellIn, initialQuality);
        Item[] items = new Item[]{randomItem};
        initializeStore(items);

        daysPass(initialSellIn);

        assertEquals(initialQuality - initialSellIn, randomItem.quality, "Quality is decreasing normally");
        assertEquals(0, randomItem.sellIn, "Two days have passed, sell by date reached");

        daysPass(1);

        assertEquals(0, randomItem.quality, "quality has started to degrade faster, but still cannot get below zero");
        assertEquals(-1, randomItem.sellIn, "The sell by date has passed");
    }

    @Test
    void agedBrieIncreasesInQuality() {
        int initialQuality = 10;
        int initialSellIn = 2;
        Item agedBrie = new Item("Aged Brie", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{agedBrie};

        initializeStore(inventory);

        daysPass(initialSellIn);

        assertEquals(12, agedBrie.quality, "Quality increases when brie ages");
        assertEquals(0, agedBrie.sellIn);

        daysPass(1);

        assertEquals(14, agedBrie.quality, "Quality ages twice as fast now");
        assertEquals(-1, agedBrie.sellIn);
    }

    @Test
    void agedBrieNeverAgesBeyond50Quality() {
        int initialQuality = 49;
        int initialSellIn = 2;
        Item agedBrie = new Item("Aged Brie", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{agedBrie};

        initializeStore(inventory);

        daysPass(initialSellIn);

        assertEquals(50, agedBrie.quality, "Quality does not increase beyond 50");
        assertEquals(0, agedBrie.sellIn);

        daysPass(1);

        assertEquals(50, agedBrie.quality);
        assertEquals(-1, agedBrie.sellIn);
    }

    @Test
    void sulfurasQualityAndSellInNeverchanges() {
        int initialQuality = 80;
        int initialSellIn = 2;
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{sulfuras};

        initializeStore(inventory);

        daysPass(initialSellIn);

        assertEquals(80, sulfuras.quality, "Quality is always 80");
        assertEquals(2, sulfuras.sellIn);

        daysPass(1);

        assertEquals(80, sulfuras.quality);
        assertEquals(2, sulfuras.sellIn);
    }

    @Test
    void backStagePassesQualityIncreasesBy1WhenMoreThan10DaysRemaining() {
        int initialQuality = 10;
        int initialSellIn = 15;
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{backStagePass};

        initializeStore(inventory);

        daysPass(3);

        assertEquals(initialQuality + 3, backStagePass.quality);
        assertEquals(initialSellIn - 3, backStagePass.sellIn);
    }

    @Test
    void backStagePassesQualityIncreasesBy2WhenLessThan10DaysRemaining() {
        int initialQuality = 10;
        int initialSellIn = 10;
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{backStagePass};

        initializeStore(inventory);

        daysPass(3);

        assertEquals(initialQuality + 6, backStagePass.quality);
        assertEquals(initialSellIn - 3, backStagePass.sellIn);
    }

    @Test
    void backStagePassesQualityIncreasesBy3WhenLessThan5DaysRemaining() {
        int initialQuality = 10;
        int initialSellIn = 5;
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{backStagePass};

        initializeStore(inventory);

        daysPass(3);

        assertEquals(initialQuality + 9, backStagePass.quality);
        assertEquals(initialSellIn - 3, backStagePass.sellIn);
    }

    @Test
    void backStagePassesQualityIsZeroWhenExpires() {
        int initialQuality = 10;
        int initialSellIn = 2;
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{backStagePass};

        initializeStore(inventory);

        daysPass(3);

        assertEquals(0, backStagePass.quality);
        assertEquals(initialSellIn - 3, backStagePass.sellIn);
    }

    @Test
    void backStagePassesQualityCanNeverBeGreaterThan50() {
        int initialQuality = 49;
        int initialSellIn = 5;
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{backStagePass};

        initializeStore(inventory);

        daysPass(1);

        assertEquals(50, backStagePass.quality);
        assertEquals(initialSellIn - 1, backStagePass.sellIn);
    }

    @Test
    void backStagePassesQualityCannotIncreaseIf50() {
        int initialQuality = 50;
        int initialSellIn = 5;
        Item backStagePass = new Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{backStagePass};

        initializeStore(inventory);

        daysPass(1);

        assertEquals(50, backStagePass.quality);
        assertEquals(initialSellIn - 1, backStagePass.sellIn);
    }

    /*
    Extra tests for full branch coverage
     */

    @Test
    /*
     * Does this test make sense?
     * The requirements mention nothing about Sulfuras's sellIn besides "it never has to be sold"
     * What does "never has to be sold" mean in terms of the sell in value?
     * Requirements say "All items have a SellIn value which denotes the number of days we have to sell the items"
     * If it never has to be sold, that means it does not have a sell in date
     * The sell in of Sulfuras means nothing but it can't be made nullable
     */
    void sulfurasSellInCanBeNegative() {
        int initialQuality = 80;
        int initialSellIn = -1;
        Item sulfuras = new Item("Sulfuras, Hand of Ragnaros", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{sulfuras};

        initializeStore(inventory);

        daysPass(initialSellIn);

        assertEquals(80, sulfuras.quality, "Quality is always 80");
        assertEquals(initialSellIn, sulfuras.sellIn);

        daysPass(1);

        assertEquals(80, sulfuras.quality);
        assertEquals(initialSellIn, sulfuras.sellIn);
    }

    @Test
    void sulfurasQualityIsAlways80() {
        int initialQuality = 49;
        int initialSellIn = 2;
        Item sulfuras = new Item("Sulfuras", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{sulfuras};

        initializeStore(inventory);

        daysPass(initialSellIn);

        assertEquals(80, sulfuras.quality, "Quality is always 80");
        assertEquals(initialSellIn, sulfuras.sellIn);

        daysPass(1);

        assertEquals(80, sulfuras.quality);
        assertEquals(initialSellIn, sulfuras.sellIn);
    }

    @Test
    void backstagePassesCanExistForDifferentEvents() {
        int initialQuality = 49;
        int initialSellIn = 5;
        Item backStagePass = new Item("Backstage passes to an Alestorm concert", initialSellIn, initialQuality);
        Item[] inventory = new Item[]{backStagePass};

        initializeStore(inventory);

        daysPass(1);

        assertEquals(50, backStagePass.quality);
        assertEquals(initialSellIn - 1, backStagePass.sellIn);
    }

    /*
    New functionality tests
     */

    private void initializeStore(Item[] inventory) {
        store = new GildedRose(inventory);
    }

    private void daysPass(int days) {
        for(int i = 0; i<days; i++) {
            store.updateQuality();
        }
    }

}

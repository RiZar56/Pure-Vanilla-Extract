package net.purevanillaextract.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.DyedCarpetBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.purevanillaextract.PureVanillaExtract;
import net.purevanillaextract.block.PureVanillaExtractBlocks;
import net.purevanillaextract.entity.PureVanillaExtractEntities;

public class PureVanillaExtractItems {

    //Modded Items
    public static final Item SKELETON_WOLF_SPAWN_EGG = registerItem("skeleton_wolf_spawn_egg", new SpawnEggItem(PureVanillaExtractEntities.SKELETON_WOLF, 0xc4c4c4, 0xff0000, new FabricItemSettings()));
    public static final Item BLUEFISH_SPAWN_EGG = registerItem("bluefish_spawn_egg", new SpawnEggItem(PureVanillaExtractEntities.BLUEFISH, 0x699493, 0x3f5f5f, new FabricItemSettings()));
    public static final Item WOOLY_COW_SPAWN_EGG = registerItem("wooly_cow_spawn_egg", new SpawnEggItem(PureVanillaExtractEntities.WOOLY_COW, 0xb86132, 0xf8bd82, new FabricItemSettings()));
    public static final Item CLUCKSHROOM_SPAWN_EGG = registerItem("cluckshroom_spawn_egg", new SpawnEggItem(PureVanillaExtractEntities.CLUCKSHROOM, 0xbf1316, 0xee9034, new FabricItemSettings()));
    public static final Item FANCY_CHICKEN_SPAWN_EGG = registerItem("fancy_chicken_spawn_egg", new SpawnEggItem(PureVanillaExtractEntities.FANCY_CHICKEN, 0xffd06b, 0x682d82, new FabricItemSettings()));



    public static final Item WOLF_COLLAR = registerItem("wolf_collar", new DyeableCollarItem(new FabricItemSettings()));

    public static final Item BLUEFISH = registerItem("bluefish", new Item(new FabricItemSettings().food(FoodComponents.COD)));
    public static final Item COOKED_BLUEFISH = registerItem("cooked_bluefish", new Item(new FabricItemSettings().food(FoodComponents.COOKED_COD)));
    public static final Item BLUEFISH_BUCKET = registerItem("bluefish_bucket", new EntityBucketItem(PureVanillaExtractEntities.BLUEFISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, new Item.Settings().maxCount(1)));

    public static final Item YARN = registerItem("yarn", new Item(new FabricItemSettings()));




    //ItemGroup Adders
    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(BLUEFISH_BUCKET);
    }

    private static void addItemsToNaturalItemGroup(FabricItemGroupEntries entries) {
        entries.add(PureVanillaExtractBlocks.WOLF_COCOON);
    }

    private static void addItemsToSpawnEggItemGroup(FabricItemGroupEntries entries) {
        entries.add(SKELETON_WOLF_SPAWN_EGG);
        entries.add(BLUEFISH_SPAWN_EGG);
        entries.add(WOOLY_COW_SPAWN_EGG);
        entries.add(CLUCKSHROOM_SPAWN_EGG);
        entries.add(FANCY_CHICKEN_SPAWN_EGG);
    }

    private static void addItemsToIngredientsItemGroup(FabricItemGroupEntries entries) {
        entries.add(WOLF_COLLAR);
        entries.add(YARN);
    }

    private static void addItemsToFoodItemGroup(FabricItemGroupEntries entries) {
        entries.add(BLUEFISH);
        entries.add(COOKED_BLUEFISH);
    }

    //Helper Methods
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PureVanillaExtract.MOD_ID, name), item);
    }

    public static void registerPveItems() {
        PureVanillaExtract.LOGGER.info("Registering Mod Items for " + PureVanillaExtract.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(PureVanillaExtractItems::addItemsToNaturalItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(PureVanillaExtractItems::addItemsToSpawnEggItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(PureVanillaExtractItems::addItemsToIngredientsItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(PureVanillaExtractItems::addItemsToFoodItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(PureVanillaExtractItems::addItemsToToolsItemGroup);
    }
}

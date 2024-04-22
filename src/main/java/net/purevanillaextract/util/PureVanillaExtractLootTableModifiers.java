package net.purevanillaextract.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.purevanillaextract.PureVanillaExtract;
import net.purevanillaextract.item.PureVanillaExtractItems;
import net.purevanillaextract.loot.condition.PureVanillaExtractEntityPropertiesLootCondition;
import net.purevanillaextract.predicate.entity.PureVanillaExtractEntityFlagsPredicate;
import net.purevanillaextract.predicate.entity.PureVanillaExtractEntityPredicate;

public class PureVanillaExtractLootTableModifiers {

    private static  final Identifier WOLF_ID = EntityType.WOLF.getLootTableId();

    private static  final Identifier HOGLIN_ID = EntityType.HOGLIN.getLootTableId();


    public static void modifyLootTables() {
        PureVanillaExtract.LOGGER.info("Modifying Loot Tables for " + PureVanillaExtract.MOD_ID);

        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (source.isBuiltin() && WOLF_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .with(ItemEntry.builder(PureVanillaExtractItems.WOLF_COLLAR)
                                .conditionally(RandomChanceLootCondition.builder(1f))
                                .conditionally(PureVanillaExtractEntityPropertiesLootCondition
                                        .builder(LootContext.EntityTarget.THIS,
                                                PureVanillaExtractEntityPredicate.Builder.create()
                                                        .pveFlags(PureVanillaExtractEntityFlagsPredicate.Builder
                                                                .create().isTamed(true)))));
                tableBuilder.pool(poolBuilder);
            }
            if (source.isBuiltin() && HOGLIN_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                    .with(ItemEntry.builder(PureVanillaExtractItems.HAM));
                tableBuilder.pool(poolBuilder);
            }
        }));
    }
}

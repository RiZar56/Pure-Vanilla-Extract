package net.purevanillaextract;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.data.server.loottable.EntityLootTableGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.text.EntityNbtDataSource;
import net.minecraft.util.Identifier;
import net.purevanillaextract.block.PureVanillaExtractBlocks;
import net.purevanillaextract.entity.PureVanillaExtractEntities;
import net.purevanillaextract.entity.effect.PureVanillaExtractStatusEffects;
import net.purevanillaextract.item.PureVanillaExtractItems;
import net.purevanillaextract.loot.condition.PureVanillaExtractEntityPropertiesLootCondition;
import net.purevanillaextract.predicate.entity.PureVanillaExtractEntityFlagsPredicate;
import net.purevanillaextract.predicate.entity.PureVanillaExtractEntityPredicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static net.minecraft.world.biome.BiomeKeys.SOUL_SAND_VALLEY;

public class PureVanillaExtract implements ModInitializer {



	public static final String MOD_ID = "purevanillaextract";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	private static  final Identifier WOLF_LOOT_TABLE_ID = EntityType.WOLF.getLootTableId();





	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.

		//Registers all custom effects, blocks, items, and entities
		PureVanillaExtractStatusEffects.registerPveEffects();
		PureVanillaExtractBlocks.registerPveBlocks();
		PureVanillaExtractItems.registerPveItems();
		PureVanillaExtractEntities.registerPveEntities();



		//this stuff needs to be moved into dedicated classes vvvvv


		//Sets custom spawn rules for phantoms
		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(SOUL_SAND_VALLEY), SpawnGroup.MONSTER, EntityType.PHANTOM, 5, 3, 5);

		//Assigns custom drops to vanilla entities
		LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
			if (source.isBuiltin() && WOLF_LOOT_TABLE_ID.equals(id)) {
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
		}));


	}
}
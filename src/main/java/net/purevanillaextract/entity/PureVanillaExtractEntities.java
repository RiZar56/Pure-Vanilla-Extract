package net.purevanillaextract.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.BlueIceFeature;
import net.purevanillaextract.PureVanillaExtract;
import net.purevanillaextract.entity.passive.BluefishEntity;
import net.purevanillaextract.entity.passive.CluckshroomEntity;
import net.purevanillaextract.entity.passive.SkeletonWolfEntity;
import net.purevanillaextract.entity.passive.WoolyCowEntity;

import java.util.Optional;

import static net.purevanillaextract.PureVanillaExtract.MOD_ID;

public class PureVanillaExtractEntities {
    //Skeleton Wolf
    public static final EntityType<SkeletonWolfEntity> SKELETON_WOLF =
            registerSkeletonWolfEntity("skeleton_wolf",
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SkeletonWolfEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.85f)).build());

    private static EntityType<SkeletonWolfEntity> registerSkeletonWolfEntity(String name, EntityType<SkeletonWolfEntity> entity) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(PureVanillaExtract.MOD_ID, name), entity);
    }

    //Bluefish
    public static final EntityType<BluefishEntity> BLUEFISH =
            registerBluefishEntity("bluefish",
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, BluefishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.3f)).build());

    private static EntityType<BluefishEntity> registerBluefishEntity(String name, EntityType<BluefishEntity> entity) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(PureVanillaExtract.MOD_ID, name), entity);
    }

    //Wooly Cow
    public static final EntityType<WoolyCowEntity> WOOLY_COW =
            registerWoolyCowEntity("wooly_cow",
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WoolyCowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9f,1.4f)).build());

    private static EntityType<WoolyCowEntity> registerWoolyCowEntity(String name, EntityType<WoolyCowEntity> entity) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(PureVanillaExtract.MOD_ID, name), entity);
    }

    //Cluckshroom
    public static final EntityType<CluckshroomEntity> CLUCKSHROOM =
            registerCluckshroomEntity("cluckshroom",
                    FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CluckshroomEntity::new)
                            .dimensions(EntityDimensions.fixed(0.4f,0.7f)).build());

    private static EntityType<CluckshroomEntity> registerCluckshroomEntity(String name, EntityType<CluckshroomEntity> entity) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(PureVanillaExtract.MOD_ID, name), entity);
    }

    public static void registerPveEntities(){
        PureVanillaExtract.LOGGER.info("Registering Mod Entities for " + MOD_ID);

        FabricDefaultAttributeRegistry.register(SKELETON_WOLF, SkeletonWolfEntity.createWolfAttributes());
        FabricDefaultAttributeRegistry.register(BLUEFISH, BluefishEntity.createFishAttributes());
        FabricDefaultAttributeRegistry.register(WOOLY_COW, WoolyCowEntity.createCowAttributes());
        FabricDefaultAttributeRegistry.register(CLUCKSHROOM, CluckshroomEntity.createChickenAttributes());

    }
}

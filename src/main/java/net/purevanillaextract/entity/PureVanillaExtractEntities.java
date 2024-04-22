package net.purevanillaextract.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.BlueIceFeature;
import net.purevanillaextract.PureVanillaExtract;
import net.purevanillaextract.entity.passive.*;
import net.purevanillaextract.entity.projectile.thrown.IceBombEntity;

import java.util.Optional;

import static net.purevanillaextract.PureVanillaExtract.MOD_ID;

public class PureVanillaExtractEntities {
    //Skeleton Wolf
    public static final EntityType<SkeletonWolfEntity> SKELETON_WOLF =
            registerEntity("skeleton_wolf",
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SkeletonWolfEntity::new)
                    .dimensions(EntityDimensions.fixed(0.6f, 0.85f)).build());

    //Bluefish
    public static final EntityType<BluefishEntity> BLUEFISH =
            registerEntity("bluefish",
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, BluefishEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.3f)).build());

    //Wooly Cow
    public static final EntityType<WoolyCowEntity> WOOLY_COW =
            registerEntity("wooly_cow",
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WoolyCowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9f,1.4f)).build());

    //Cluckshroom
    public static final EntityType<CluckshroomEntity> CLUCKSHROOM =
            registerEntity("cluckshroom",
                    FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CluckshroomEntity::new)
                            .dimensions(EntityDimensions.fixed(0.4f,0.7f)).build());

    //Cluckshroom
    public static final EntityType<FancyChickenEntity> FANCY_CHICKEN =
            registerEntity("fancy_chicken",
                    FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FancyChickenEntity::new)
                            .dimensions(EntityDimensions.fixed(0.4f,1.0f)).build());

    //Ice Bomb
    public static final EntityType<IceBombEntity> ICE_BOMB =
            registerEntity("ice_bomb",
                    FabricEntityTypeBuilder.<IceBombEntity>create(SpawnGroup.MISC, IceBombEntity::new)
                            .dimensions(EntityDimensions.fixed(0.25f,0.25f))
                            .trackRangeBlocks(4).trackedUpdateRate(10).build());




    private static <T extends EntityType<?>> T registerEntity(String name, T entity) {
        return Registry.register(Registries.ENTITY_TYPE, new Identifier(PureVanillaExtract.MOD_ID, name), entity);
    }

    public static void registerPveEntities(){
        PureVanillaExtract.LOGGER.info("Registering Mod Entities for " + MOD_ID);

        FabricDefaultAttributeRegistry.register(SKELETON_WOLF, SkeletonWolfEntity.createWolfAttributes());
        FabricDefaultAttributeRegistry.register(BLUEFISH, BluefishEntity.createFishAttributes());
        FabricDefaultAttributeRegistry.register(WOOLY_COW, WoolyCowEntity.createCowAttributes());
        FabricDefaultAttributeRegistry.register(CLUCKSHROOM, CluckshroomEntity.createChickenAttributes());
        FabricDefaultAttributeRegistry.register(FANCY_CHICKEN, CluckshroomEntity.createChickenAttributes());

    }
}

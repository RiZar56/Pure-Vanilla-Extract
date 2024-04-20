package net.purevanillaextract.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.purevanillaextract.entity.PureVanillaExtractEntities;
import org.jetbrains.annotations.Nullable;

public class FancyChickenEntity extends ChickenEntity {
    public FancyChickenEntity(EntityType<? extends ChickenEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    public FancyChickenEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return (FancyChickenEntity) PureVanillaExtractEntities.FANCY_CHICKEN.create(serverWorld);
    }
}

package net.purevanillaextract.entity.passive;

import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.passive.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;


public class SkeletonWolfEntity extends WolfEntity {
    public SkeletonWolfEntity(EntityType<? extends WolfEntity> entityType, World world) {
        super(entityType, world);
    }

    public EntityGroup getGroup() {
        return EntityGroup.UNDEAD;
    }

    @Override
    public WolfEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.getGoals().removeIf(prioritizedGoal -> prioritizedGoal.getGoal() instanceof SwimGoal);
        this.targetSelector.getGoals().removeIf(prioritizedGoal -> prioritizedGoal.getGoal() instanceof ActiveTargetGoal);
        this.goalSelector.getGoals().removeIf(prioritizedGoal -> prioritizedGoal.getGoal() instanceof AnimalMateGoal);

    }



}

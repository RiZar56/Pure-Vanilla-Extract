package net.purevanillaextract.loot.condition;

import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionType;
import net.minecraft.loot.condition.LootConditionTypes;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameter;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.math.Vec3d;
import net.purevanillaextract.predicate.entity.PureVanillaExtractEntityPredicate;

import java.util.Optional;
import java.util.Set;

public record PureVanillaExtractEntityPropertiesLootCondition (Optional<PureVanillaExtractEntityPredicate> predicate, LootContext.EntityTarget entity) implements LootCondition {

    public PureVanillaExtractEntityPropertiesLootCondition(Optional<PureVanillaExtractEntityPredicate> predicate , LootContext.EntityTarget entity){
        this.predicate = predicate;
        this.entity = entity;
    }

    @Override
    public LootConditionType getType() {
        return LootConditionTypes.ENTITY_PROPERTIES;
    }

    public Set<LootContextParameter<?>> getRequiredParameters() {
        return ImmutableSet.of(LootContextParameters.ORIGIN, this.entity.getParameter());
    }

    @Override
    public boolean test(LootContext lootContext) {
        TameableEntity tameableEntity = (TameableEntity)lootContext.get(this.entity.getParameter());
        Vec3d vec3d = (Vec3d)lootContext.get(LootContextParameters.ORIGIN);
        return this.predicate.isEmpty() || ((PureVanillaExtractEntityPredicate)this.predicate.get()).test(lootContext.getWorld(), vec3d, tameableEntity);
    }

    public static Builder create(LootContext.EntityTarget entity) {
        return builder(entity, net.purevanillaextract.predicate.entity.PureVanillaExtractEntityPredicate.Builder.create());
    }

    public static Builder builder(LootContext.EntityTarget entity, net.purevanillaextract.predicate.entity.PureVanillaExtractEntityPredicate.Builder predicateBuilder) {
        return () -> {
            return new PureVanillaExtractEntityPropertiesLootCondition(Optional.of(predicateBuilder.build()), entity);
        };
    }

    public static Builder builder(LootContext.EntityTarget entity, PureVanillaExtractEntityPredicate predicate) {
        return () -> {
            return new PureVanillaExtractEntityPropertiesLootCondition(Optional.of(predicate), entity);
        };
    }

    public Optional<PureVanillaExtractEntityPredicate> predicate() {
        return this.predicate;
    }

    public LootContext.EntityTarget entity() {
        return this.entity;
    }
}

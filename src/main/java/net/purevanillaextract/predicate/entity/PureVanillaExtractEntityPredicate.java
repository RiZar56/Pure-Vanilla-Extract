package net.purevanillaextract.predicate.entity;

import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public record PureVanillaExtractEntityPredicate(Optional<PureVanillaExtractEntityFlagsPredicate> pveFlags) {
    public static final Codec<PureVanillaExtractEntityPredicate> CODEC = Codecs.createRecursive("PureVanillaExtractEntityPredicate", instance -> {
         return RecordCodecBuilder.create(instance2 ->{
             return instance2.group(
                     Codecs.createStrictOptionalFieldCodec(PureVanillaExtractEntityFlagsPredicate.CODEC, "pve_flags")
                    .forGetter(PureVanillaExtractEntityPredicate::pveFlags))
                        .apply(instance2, PureVanillaExtractEntityPredicate::new);
         });
    });

    public static final Codec<LootContextPredicate> LOOT_CONTEXT_PREDICATE_CODEC = Codecs.either(LootContextPredicate.CODEC, CODEC, PureVanillaExtractEntityPredicate::asLootContextPredicate);

    public PureVanillaExtractEntityPredicate(Optional<PureVanillaExtractEntityFlagsPredicate> pveFlags) {
        this.pveFlags = pveFlags;
    }

    public static LootContextPredicate contextPredicateFromPureVanillaExtractEntityPredicate(PureVanillaExtractEntityPredicate.Builder builder) {
        return asLootContextPredicate(builder.build());
    }

    public static Optional<LootContextPredicate> contextPredicateFromPureVanillaExtractEntityPredicate(Optional<PureVanillaExtractEntityPredicate> pureVanillaExtractEntityPredicate) {
        return pureVanillaExtractEntityPredicate.map(PureVanillaExtractEntityPredicate::asLootContextPredicate);
    }

    public static List<LootContextPredicate> contextPredicateFromPureVanillaExtractEntityPredicates(PureVanillaExtractEntityPredicate.Builder... builders) {
        return Stream.of(builders).map(PureVanillaExtractEntityPredicate::contextPredicateFromPureVanillaExtractEntityPredicate).toList();
    }

    public static Predicate<Entity> convertToEntityPredicate(PureVanillaExtractEntityPredicate predicate) {
        return entity -> {
            if (entity instanceof TameableEntity) {
                return predicate.pveFlags().isPresent();
            }
            return false;
        };
    }


    public static LootContextPredicate asLootContextPredicate(PureVanillaExtractEntityPredicate predicate) {
        Predicate<Entity> entityPredicate = convertToEntityPredicate(predicate);
        LootCondition lootCondition = EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, (EntityPredicate.Builder) entityPredicate).build();
        return  LootContextPredicate.create(lootCondition);
    }

    public boolean test(ServerPlayerEntity player, @Nullable TameableEntity tamableEntity) {
        return this.test(player.getServerWorld(), player.getPos(), tamableEntity);
    }

    public boolean test(ServerWorld world, @Nullable Vec3d pos, @Nullable TameableEntity tamableEntity) {
        if (tamableEntity == null) {
            return false;
        }
        return this.pveFlags.isEmpty() || ((PureVanillaExtractEntityFlagsPredicate) this.pveFlags.get()).test(tamableEntity);
    }

    public Optional<PureVanillaExtractEntityFlagsPredicate> pveFlags() {
        return this.pveFlags;
    }


    public static class Builder {
        private Optional<PureVanillaExtractEntityFlagsPredicate> pveFlags = Optional.empty();

        public Builder() {
        }

        public static PureVanillaExtractEntityPredicate.Builder create() {
            return new PureVanillaExtractEntityPredicate.Builder();
    }

        public PureVanillaExtractEntityPredicate.Builder pveFlags(net.purevanillaextract.predicate.entity.PureVanillaExtractEntityFlagsPredicate.Builder pveFlags) {
            this.pveFlags = Optional.of(pveFlags.build());
            return this;
        }

        public PureVanillaExtractEntityPredicate build() {
            return new PureVanillaExtractEntityPredicate(this.pveFlags);
        }
    }
}

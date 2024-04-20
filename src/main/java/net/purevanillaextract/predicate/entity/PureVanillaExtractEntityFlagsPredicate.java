package net.purevanillaextract.predicate.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.dynamic.Codecs;

import java.util.Optional;

public record PureVanillaExtractEntityFlagsPredicate(Optional<Boolean> isTamed) {

    public static final Codec<PureVanillaExtractEntityFlagsPredicate> CODEC = RecordCodecBuilder.create((instance) ->
        instance.group(Codecs.createStrictOptionalFieldCodec(Codec.BOOL, "is_tamed")
            .forGetter(PureVanillaExtractEntityFlagsPredicate::isTamed))
                .apply(instance, PureVanillaExtractEntityFlagsPredicate::new));

    public PureVanillaExtractEntityFlagsPredicate(Optional<Boolean> isTamed){
        this.isTamed = isTamed;
    }

    public boolean test(TameableEntity tameableEntity) {
        return this.isTamed.isEmpty() || tameableEntity.isTamed() == (Boolean) this.isTamed.get();
    }

    public Optional<Boolean> isTamed() {return this.isTamed;}

    public static class Builder {
        private Optional<Boolean> isTamed = Optional.empty();

        public Builder(){
        }

        public static PureVanillaExtractEntityFlagsPredicate.Builder create(){return new PureVanillaExtractEntityFlagsPredicate.Builder();}

        public PureVanillaExtractEntityFlagsPredicate.Builder isTamed(Boolean isTamed) {
            this.isTamed = Optional.of(isTamed);
            return this;
        }

        public  PureVanillaExtractEntityFlagsPredicate build(){
            return new PureVanillaExtractEntityFlagsPredicate((this.isTamed));
        }

    }

}

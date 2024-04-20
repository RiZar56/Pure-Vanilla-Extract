package net.purevanillaextract.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class InsomniaStatusEffect
extends StatusEffect {
    public InsomniaStatusEffect(StatusEffectCategory statusEffectCategory, int i) {
        super(statusEffectCategory, i);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.isSleeping()) {
            // Cancel sleeping if the entity has the Insomnia effect
            entity.wakeUp();
        }
    }
}

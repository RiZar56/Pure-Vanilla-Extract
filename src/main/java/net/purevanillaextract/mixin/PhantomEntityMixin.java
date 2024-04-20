package net.purevanillaextract.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.PhantomEntity;
import net.purevanillaextract.entity.effect.PureVanillaExtractStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;




@Mixin(targets = "net.minecraft.entity.mob.PhantomEntity$SwoopMovementGoal")
abstract class PhantomEntityMixin {


    @WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/PhantomEntity;tryAttack(Lnet/minecraft/entity/Entity;)Z"))
    public boolean pve_tryAttack(PhantomEntity attacker, Entity target, Operation<Boolean> original) {

        boolean result = original.call(attacker, target);

        if(result && target instanceof LivingEntity livingTarget){
            livingTarget.addStatusEffect(new StatusEffectInstance(PureVanillaExtractStatusEffects.INSOMNIA, 216000, 0));
        }
        return result;
    }
}

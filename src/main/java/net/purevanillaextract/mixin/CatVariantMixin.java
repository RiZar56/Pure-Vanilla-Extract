package net.purevanillaextract.mixin;

import net.minecraft.entity.passive.CatVariant;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.purevanillaextract.PureVanillaExtract;
import net.purevanillaextract.entity.passive.PveCatVariant;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.purevanillaextract.entity.passive.PveCatVariant.PEANUT_BUTTER;

@Mixin(CatVariant.class)
abstract class CatVariantMixin {

    @Inject(method = "registerAndGetDefault", at = @At("HEAD"), cancellable = true)
    private static void registerAndGetDefault(Registry<CatVariant> registry, CallbackInfoReturnable<CatVariant> cir) {
        registerMixin(registry, PEANUT_BUTTER, "textures/entity/cat/peanut_butter.png");
    }

    private static CatVariant registerMixin(Registry<CatVariant> registry, RegistryKey<CatVariant> key, String textureId) {
        return (CatVariant)Registry.register(registry, key, new CatVariant(new Identifier(PureVanillaExtract.MOD_ID, textureId)));
    }


}


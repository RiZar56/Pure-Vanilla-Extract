package net.purevanillaextract.entity.effect;


import net.minecraft.entity.effect.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.purevanillaextract.PureVanillaExtract;

public class PureVanillaExtractStatusEffects extends StatusEffects {

    public static StatusEffect INSOMNIA;

    private static StatusEffect registerEffect(String id, StatusEffect statusEffect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(PureVanillaExtract.MOD_ID, id), statusEffect);
    }

    public static void registerPveEffects(){
        PureVanillaExtract.LOGGER.info("Registering Mod Effects for " + PureVanillaExtract.MOD_ID);

        INSOMNIA = PureVanillaExtractStatusEffects.registerEffect("insomnia", new InsomniaStatusEffect(StatusEffectCategory.HARMFUL, 0x192841));
    }
}

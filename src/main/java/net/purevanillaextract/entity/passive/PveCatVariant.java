package net.purevanillaextract.entity.passive;

import net.minecraft.entity.passive.CatVariant;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public record PveCatVariant(Identifier texture) {
    public static final RegistryKey<CatVariant> PEANUT_BUTTER = of("peanut_butter");
    public static final RegistryKey<CatVariant> COCO = of("coco");
    public static final RegistryKey<CatVariant> ZACH = of("zach");

    private static RegistryKey<CatVariant> of(String id) {
        return RegistryKey.of(RegistryKeys.CAT_VARIANT, new Identifier(id));
    }


}

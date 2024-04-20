package net.purevanillaextract.datagen;

import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.vanilla.VanillaCatVariantTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.CatVariantTags;
import net.purevanillaextract.entity.passive.PveCatVariant;

import java.util.concurrent.CompletableFuture;

public class PureVanillaExtractCatVariantTagProvider
extends VanillaCatVariantTagProvider {
    public PureVanillaExtractCatVariantTagProvider(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(CatVariantTags.DEFAULT_SPAWNS)
                .add(PveCatVariant.PEANUT_BUTTER, PveCatVariant.COCO, PveCatVariant.ZACH);

    }
}

package net.purevanillaextract.datagen;

import net.minecraft.data.DataOutput;
import net.minecraft.data.server.tag.vanilla.VanillaEntityTypeTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.EntityTypeTags;
import net.purevanillaextract.entity.PureVanillaExtractEntities;


import java.util.concurrent.CompletableFuture;

public class PureVanillaExtractEntityTypeTagGenerator
extends VanillaEntityTypeTagProvider {
    public PureVanillaExtractEntityTypeTagGenerator(DataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup lookup) {
        this.getOrCreateTagBuilder(EntityTypeTags.SKELETONS)
                .add(PureVanillaExtractEntities.SKELETON_WOLF);

        this.getOrCreateTagBuilder(EntityTypeTags.FALL_DAMAGE_IMMUNE)
                .add(PureVanillaExtractEntities.CLUCKSHROOM);

    }


}

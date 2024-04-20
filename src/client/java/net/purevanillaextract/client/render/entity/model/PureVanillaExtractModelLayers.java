package net.purevanillaextract.client.render.entity.model;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.purevanillaextract.PureVanillaExtract;

public class PureVanillaExtractModelLayers {
    public static final EntityModelLayer SKELETON_WOLF =
            new EntityModelLayer(new Identifier(PureVanillaExtract.MOD_ID, "skeleton_wolf"), "main");

    public static final EntityModelLayer BLUEFISH =
            new EntityModelLayer(new Identifier(PureVanillaExtract.MOD_ID, "bluefish"), "main");

    public static final EntityModelLayer WOOLY_COW =
            new EntityModelLayer(new Identifier(PureVanillaExtract.MOD_ID, "wooly_cow"), "main");

    public static final EntityModelLayer CLUCKSHROOM =
            new EntityModelLayer(new Identifier(PureVanillaExtract.MOD_ID, "cluckshroom"), "main");
}

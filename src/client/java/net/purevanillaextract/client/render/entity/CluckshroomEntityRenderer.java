package net.purevanillaextract.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.purevanillaextract.PureVanillaExtract;
import net.purevanillaextract.client.render.entity.model.CluckshroomEntityModel;
import net.purevanillaextract.client.render.entity.model.PureVanillaExtractModelLayers;
import net.purevanillaextract.entity.passive.CluckshroomEntity;


@Environment(EnvType.CLIENT)
public class CluckshroomEntityRenderer extends MobEntityRenderer<CluckshroomEntity, CluckshroomEntityModel<CluckshroomEntity>> {
    public static final Identifier RED = new Identifier(PureVanillaExtract.MOD_ID,"textures/entity/cluckshroom/cluckshroom.png" );
    public static final Identifier BROWN = new Identifier(PureVanillaExtract.MOD_ID,"textures/entity/cluckshroom/cluckshroom_brown.png" );


    public CluckshroomEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CluckshroomEntityModel<>(context.getPart(PureVanillaExtractModelLayers.CLUCKSHROOM)), 0.3f);

    }

    @Override
    public Identifier getTexture(CluckshroomEntity entity) {
        return RED;
    }

    protected float getAnimationProgress(CluckshroomEntity cluckshroomEntity, float f) {
        float g = MathHelper.lerp(f, cluckshroomEntity.prevFlapProgress, cluckshroomEntity.flapProgress);
        float h = MathHelper.lerp(f, cluckshroomEntity.prevMaxWingDeviation, cluckshroomEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0F) * h;
    }


}

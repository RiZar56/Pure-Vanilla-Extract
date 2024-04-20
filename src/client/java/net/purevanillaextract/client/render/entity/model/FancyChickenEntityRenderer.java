package net.purevanillaextract.client.render.entity.model;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.purevanillaextract.PureVanillaExtract;
import net.purevanillaextract.entity.passive.FancyChickenEntity;

public class FancyChickenEntityRenderer extends MobEntityRenderer<FancyChickenEntity, FancyChickenEntityModel<FancyChickenEntity>> {
    public static final Identifier TEXTURE = new Identifier(PureVanillaExtract.MOD_ID,"textures/entity/fancy_chicken.png" );

    public FancyChickenEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new FancyChickenEntityModel<>(context.getPart(PureVanillaExtractModelLayers.FANCY_CHICKEN)), 0.3f);    }


    @Override
    public Identifier getTexture(FancyChickenEntity entity) {
        return TEXTURE;
    }

    protected float getAnimationProgress(FancyChickenEntity fancyChickenEntity, float f) {
        float g = MathHelper.lerp(f, fancyChickenEntity.prevFlapProgress, fancyChickenEntity.flapProgress);
        float h = MathHelper.lerp(f, fancyChickenEntity.prevMaxWingDeviation, fancyChickenEntity.maxWingDeviation);
        return (MathHelper.sin(g) + 1.0F) * h;
    }
}
